#include "LargeAllocator.h"
#include <memory.h>
#include "utils/MathUtils.h"
#include "Object.h"
#include "Log.h"

inline int size_to_linked_list(uint32_t size) {
    assert(size >= LARGE_OBJECT_MIN_SIZE);
    return log2_floor(size) - LARGE_OBJECT_MIN_SIZE_BITS;
}

// Adds a chunk to the allocator. The chunk is split in chunk of powers of 2.
void addChunk(LargeAllocator* allocator, word_t* chunk, size_t size) {
    assert(size >= LARGE_OBJECT_MIN_SIZE);
    assert(size % LARGE_OBJECT_MIN_SIZE == 0);
    assert((word_t)chunk % (LARGE_OBJECT_MIN_SIZE * WORD_SIZE) == 0);
    memset(chunk + 1, 0, (size - 1) * WORD_SIZE);

    size_t remainingSize = size;
    word_t* current = chunk;
    while(remainingSize > 0) {
        assert((word_t)current % (LARGE_OBJECT_MIN_SIZE * WORD_SIZE) == 0);
        int log2 = log2_floor(remainingSize);
        log2 = log2 > LARGE_OBJECT_MAX_SIZE_BITS ? LARGE_OBJECT_MAX_SIZE_BITS : log2;
        uint32_t chunkSize = 1U << log2;
        assert(chunkSize >= LARGE_OBJECT_MIN_SIZE && chunkSize <= LARGE_OBJECT_MAX_SIZE);
        int listIndex = size_to_linked_list(chunkSize);

        Object* currentObject = (Object*) current;
        freeList_addLast(&allocator->freeLists[listIndex], currentObject);
        object_setSize(currentObject, chunkSize);
        object_setType(currentObject, object_large);
        object_setTag(currentObject, object_free);
        bitmap_setBit(allocator->bitmap, current);

        current += chunkSize;
        remainingSize -= chunkSize;
    }
}

// Allocates the LargeAllocator and creates the bitmap need for it.
LargeAllocator* largeAllocator_create(word_t* offset, size_t size) {

    assert((word_t)offset % (LARGE_OBJECT_MIN_SIZE * WORD_SIZE) == 0);

    LargeAllocator* allocator = malloc(sizeof(LargeAllocator));
    allocator->offset = offset;
    allocator->size = size;
    allocator->bitmap = bitmap_alloc(size, offset);
    allocator->allocCount = 0;

    for(int i=0; i < CHUNK_LIST_COUNT; i++) {
        freeList_init(&allocator->freeLists[i]);
    }

    addChunk(allocator, offset, size);


    return allocator;
}

// Allocates a large block
Object* largeAllocator_alloc(LargeAllocator* allocator, uint32_t requestedBlockSize) {
    uint32_t actualBlockSize = (requestedBlockSize + LARGE_OBJECT_MIN_SIZE - 1) / LARGE_OBJECT_MIN_SIZE * LARGE_OBJECT_MIN_SIZE;
    uint32_t requiredChunkSize = 1U << log2_ceil(actualBlockSize);

    // Start with the smallest fitting block size
    int listIndex = size_to_linked_list(requiredChunkSize);
    // While there is no block, try with a larger block
    while(listIndex <= CHUNK_LIST_COUNT - 1 && freeList_isEmpty(&allocator->freeLists[listIndex])) {
        ++listIndex;
    }
    // No block available
    if(listIndex == CHUNK_LIST_COUNT) {
        return NULL;
    }
    //Remove the first block of the linkedlist found.
    Object* object = freeList_removeFirst(&allocator->freeLists[listIndex]);

    uint32_t objectSize = object_getSize(object);
    assert(objectSize >= LARGE_OBJECT_MIN_SIZE);

    // If the block is large enough, split it and add the remaining part back to the allocator.
    if(objectSize - LARGE_OBJECT_MIN_SIZE >= actualBlockSize) {
        word_t* remainingChunk = (word_t*)object + actualBlockSize;
        size_t remainingChunkSize = objectSize - actualBlockSize;
        addChunk(allocator, remainingChunk, remainingChunkSize);
    }

    assert(bitmap_getBit(allocator->bitmap, (word_t*)object));
    return object;

}

void clearFreeLists(LargeAllocator* allocator) {
    for(int i = 0; i < CHUNK_LIST_COUNT; i++) {
        allocator->freeLists[i].first = NULL;
        allocator->freeLists[i].last = NULL;
    }
}

// Sweeps through the whole large heap.
void largeAllocator_sweep(LargeAllocator* allocator) {
    allocator->allocCount = 0;

    // Clears all linkedlists
    clearFreeLists(allocator);

    Object* current = (Object*) allocator->offset;
    void* heapEnd = allocator->offset + allocator->size;

    while(current != heapEnd) {
        assert(bitmap_getBit(allocator->bitmap, (word_t*)current));
        // If the block is marked, unmark it and move to the next block
        if(object_isMarked(current)) {
            object_unmark(current);

            current = object_nextLargeObject(current);
        } else {
            // If the block is not marked, try to merge blocks while they are not marked.
            size_t currentSize = object_getLargeObjectSize(current);
            Object* next = object_nextLargeObject(current);
            while(next != heapEnd && !object_isMarked(next)) {
                size_t size = object_getLargeObjectSize(next);
                currentSize += size;
                bitmap_clearBit(allocator->bitmap, (word_t*)next);
                next = object_nextLargeObject(next);
            }
            // If the next block is the end of the heap or if it is marked, add the current block to the allocator.
            addChunk(allocator, (word_t*)current, currentSize);
            current = next;
        }
    }
}
