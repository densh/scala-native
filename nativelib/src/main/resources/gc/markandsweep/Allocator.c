#include <stdio.h>
#include "Allocator.h"
#include "Block.h"
#include "Log.h"

uint32_t sizeToAllocatableSize(uint32_t size) {
    assert(size < LARGE_OBJECT_MIN_SIZE);
    assert(size >= OBJECT_MIN_SIZE);

    if (size <= LARGEST_CONST) {
        return size;
    } else {
        return 1U << log2_ceil(size);
    }
}

// Allocates the `Allocator` struct and initialises it
Allocator* allocator_create(word_t* offset, size_t size) {

    assert((word_t)offset % (BLOCK_SIZE * WORD_SIZE)  == 0);
    assert(size % BLOCK_SIZE == 0);

    Allocator* allocator = malloc(sizeof(Allocator));
    allocator->offset = offset;
    allocator->size = size;
    allocator->freeBlocks = (FreeBlockHeader*)offset;
    allocator->fromFreeList = 0;
    allocator->fromChunk = 0;

    FreeBlockHeader* lastBlock = (FreeBlockHeader*)(offset + (size - BLOCK_SIZE));
    lastBlock->next = LAST_BLOCK_PTR;

    for(int i = 0; i < LIST_COUNT; i++) {
        allocator->blocks[i] = NULL;
        freeList_init(&allocator->freeLists[i]);
    }

    return allocator;
}

// Removes and returns the first freeblock from the list of freeblocks
// Returns NULL if the there is not freeblock left
BlockHeader* getFreeBlock(Allocator* allocator) {
    if(allocator->freeBlocks == NULL) {
        return NULL;
    }

    BlockHeader* blockHeader = &allocator->freeBlocks->header;
    allocator->freeBlocks = block_getNextFreeBlock(allocator->freeBlocks);

    return blockHeader;
}

// Allocates an object of `size` words.
Object* allocator_alloc(Allocator* allocator, uint32_t size) {
    int listIndex = sizeToIndex(size);
    // Round the size up to the allocatable size
    uint32_t allocatedSize = sizeToAllocatableSize(size);

    // Try to allocate from block
    word_t* start = allocator->blocks[listIndex];
    word_t* end = start + allocatedSize;

    // Check if bump allocation worked
    if(start != NULL && end < block_getBlockEnd(start)) {
        allocator->blocks[listIndex] = end;
        return (Object*)start;
    }

    // if bump allocation failed, try linkedlist
    if(!freeList_isEmpty(&allocator->freeLists[listIndex])) {
        allocator->fromFreeList++;
        return freeList_removeFirst(&allocator->freeLists[listIndex]);
    }

    // if linkedlist is empty, try to get a new block
    BlockHeader* blockHeader = getFreeBlock(allocator);
    if(blockHeader == NULL) {
        return NULL;
    }

    // If we get a block, set the corresponding size and alloc
    block_setObjectSize(blockHeader, allocatedSize);
    word_t* object = block_getFirstWord(blockHeader);
    allocator->blocks[listIndex] = object + allocatedSize;
    allocator->fromChunk++;

    return (Object*)object;

}

// Sweeps the small heap. Resets the allocator and sweeps the blocks one by one.
void allocator_sweep(Allocator* allocator) {
#ifdef DEBUG_PRINT
    printf("from free list: %ld\nfrom chunk: %ld\n", allocator->fromFreeList, allocator->fromChunk);
    fflush(stdout);
#endif

    allocator->fromFreeList = 0;
    allocator->fromChunk = 0;
    for(int i = 0; i < LIST_COUNT; i++) {
        allocator->freeLists[i].first = NULL;
        allocator->freeLists[i].last = NULL;

        allocator->blocks[i] = NULL;
    }
    allocator->freeBlocks = NULL;
    allocator->lastFreeBlock = NULL;

    word_t* heapEnd = allocator->offset + allocator->size;
    word_t* current = allocator->offset;

    while(current != heapEnd) {
        block_sweep(allocator, (BlockHeader*) current);
        current += BLOCK_SIZE;
    }
}
