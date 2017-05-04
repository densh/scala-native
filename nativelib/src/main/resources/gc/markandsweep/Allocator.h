#ifndef MARKANDSWEEP_ALLOCATOR_H
#define MARKANDSWEEP_ALLOCATOR_H

#include <stddef.h>
#include <stdlib.h>
#include "GCTypes.h"
#include "datastructures/FreeList.h"
#include "Constants.h"
#include "utils/MathUtils.h"
#include "headers/BlockHeader.h"

#define LARGEST_CONST_BITS 4
#define LARGEST_CONST (1 << LARGEST_CONST_BITS)
#define LIST_COUNT ((LARGEST_CONST - OBJECT_MIN_SIZE + 1) + (LARGE_OBJECT_MIN_SIZE_BITS - LARGEST_CONST_BITS))

typedef struct {
    word_t* offset;
    size_t size;
    word_t* blocks[LIST_COUNT];
    FreeList freeLists[LIST_COUNT];
    FreeBlockHeader* freeBlocks;
    FreeBlockHeader* lastFreeBlock; // Used to rebuild the freelist
    long fromFreeList;
    long fromChunk;
} Allocator;

Allocator* allocator_create(word_t* offset, size_t size);
Object* allocator_alloc(Allocator* allocator, uint32_t size);
void allocator_sweep(Allocator* allocator);

inline int sizeToIndex(uint32_t object_size) {
    assert(object_size > 0 && object_size <= LARGE_OBJECT_MIN_SIZE);
    if(object_size <= OBJECT_MIN_SIZE) {
        return 0;
    } else if (object_size <= LARGEST_CONST) {
        return object_size - OBJECT_MIN_SIZE;
    } else {
        int log = log2_ceil(object_size);
        return LARGEST_CONST - OBJECT_MIN_SIZE + log - LARGEST_CONST_BITS;
    }
}

#endif //MARKANDSWEEP_ALLOCATOR_H
