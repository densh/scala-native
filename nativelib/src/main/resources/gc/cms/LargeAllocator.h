#ifndef CMS_LARGEALLOCATOR_H
#define CMS_LARGEALLOCATOR_H

#include <stddef.h>
#include "Types.h"
#include "datastructures/FreeList.h"
#include "Constants.h"
#include "datastructures/Bitmap.h"

#define CHUNK_LIST_COUNT                                                       \
    (LARGE_OBJECT_MAX_SIZE_BITS - LARGE_OBJECT_MIN_SIZE_BITS + 1)

typedef struct {
    word_t *offset;
    size_t size;
    FreeList freeLists[CHUNK_LIST_COUNT];
    Bitmap *bitmap;
    long allocCount;
} LargeAllocator;

LargeAllocator *LargeAllocator_create(word_t *offset, size_t size);
Object *LargeAllocator_alloc(LargeAllocator *allocator, uint32_t size);
void LargeAllocator_sweep(LargeAllocator *allocator);

#endif // CMS_LARGEALLOCATOR_H
