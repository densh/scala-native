//
// Created by Lukas Kellenberger on 29.04.17.
//

#ifndef MARKANDSWEEP_LARGEALLOCATOR_H
#define MARKANDSWEEP_LARGEALLOCATOR_H


#include <stddef.h>
#include "GCTypes.h"
#include "datastructures/FreeList.h"
#include "Constants.h"
#include "datastructures/Bitmap.h"

#define CHUNK_LIST_COUNT (LARGE_OBJECT_MAX_SIZE_BITS - LARGE_OBJECT_MIN_SIZE_BITS + 1)

typedef struct {
    word_t* offset;
    size_t size;
    FreeList freeLists[CHUNK_LIST_COUNT];
    Bitmap* bitmap;
    long allocCount;
} LargeAllocator;

LargeAllocator* largeAllocator_create(word_t* offset, size_t size);
Object* largeAllocator_alloc(LargeAllocator* allocator, uint32_t size);
void largeAllocator_sweep(LargeAllocator* allocator);

#endif //MARKANDSWEEP_LARGEALLOCATOR_H
