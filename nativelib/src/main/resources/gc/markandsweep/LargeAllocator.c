//
// Created by Lukas Kellenberger on 29.04.17.
//

#include "LargeAllocator.h"
#include <stdlib.h>
#include <memory.h>
#include "utils/MathUtils.h"
#include "Object.h"
#include <stdio.h>

inline static int size_to_linked_list(uint32_t size) {
    assert(size >= LARGE_OBJECT_MIN_SIZE);
    return log2_floor(size) - LARGE_OBJECT_MIN_SIZE_BITS;
}

void print(LargeAllocator* alloc);

void addChunk(LargeAllocator* allocator, word_t* chunk, size_t size) {
    assert(size >= LARGE_OBJECT_MIN_SIZE);
    assert(size % LARGE_OBJECT_MIN_SIZE == 0);
    assert((word_t)chunk % (LARGE_OBJECT_MIN_SIZE * WORD_SIZE) == 0);
    memset(chunk + 1, 0, (size - 1) * WORD_SIZE);

    size_t remaining_size = size;
    word_t* current = chunk;
    while(remaining_size > 0) {
        assert((word_t)current % (LARGE_OBJECT_MIN_SIZE * WORD_SIZE) == 0);
        int log2_f = log2_floor(remaining_size);
        log2_f = log2_f > LARGE_OBJECT_MAX_SIZE_BITS ? LARGE_OBJECT_MAX_SIZE_BITS : log2_f;
        uint32_t chunkSize = 1U << log2_f;
        assert(chunkSize >= LARGE_OBJECT_MIN_SIZE && chunkSize <= LARGE_OBJECT_MAX_SIZE);
        int listIndex = size_to_linked_list(chunkSize);

        Object* currentObject = (Object*) current;
        freeList_addLast(&allocator->freeLists[listIndex], currentObject);
        object_setSize(currentObject, chunkSize);
        object_setType(currentObject, object_large);
        object_setTag(currentObject, object_free);
        bitmap_setBit(allocator->bitmap, current);

        current += chunkSize;
        remaining_size -= chunkSize;
    }
}

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

Object* largeAllocator_alloc(LargeAllocator* allocator, uint32_t requestedBlockSize) {
    uint32_t actualBlockSize = (requestedBlockSize + LARGE_OBJECT_MIN_SIZE - 1) / LARGE_OBJECT_MIN_SIZE * LARGE_OBJECT_MIN_SIZE;
    uint32_t requiredChunkSize = 1U << log2_ceil(actualBlockSize);
    //printf("alloc large: %u %u ", requestedBlockSize, actualBlockSize);

    int listIndex = size_to_linked_list(requiredChunkSize);
    while(listIndex <= CHUNK_LIST_COUNT - 1 && freeList_isEmpty(&allocator->freeLists[listIndex])) {
        ++listIndex;
    }
    //print(allocator);
    if(listIndex == CHUNK_LIST_COUNT) {
        return NULL;
    }
    Object* object = freeList_removeFirst(&allocator->freeLists[listIndex]);

    uint32_t objectSize = object_getSize(object);
    assert(objectSize >= LARGE_OBJECT_MIN_SIZE);

    if(objectSize - LARGE_OBJECT_MIN_SIZE >= actualBlockSize) {
        word_t* remainingChunk = (word_t*)object + actualBlockSize;
        size_t remainingChunkSize = objectSize - actualBlockSize;
        addChunk(allocator, remainingChunk, remainingChunkSize);
    }

    assert(bitmap_getBit(allocator->bitmap, (word_t*)object));

    //printf("%p \n", object);
    return object;

}

void clearFreeLists(LargeAllocator* allocator) {
    for(int i = 0; i < CHUNK_LIST_COUNT; i++) {
        allocator->freeLists[i].first = NULL;
        allocator->freeLists[i].last = NULL;
    }
}

void largeAllocator_sweep(LargeAllocator* allocator) {
    printf("Largeblock: %ld\n", allocator->allocCount);
    allocator->allocCount = 0;

    clearFreeLists(allocator);

    Object* current = (Object*) allocator->offset;
    void* heapEnd = allocator->offset + allocator->size;

    while(current != heapEnd) {
        assert(bitmap_getBit(allocator->bitmap, (word_t*)current));
        if(object_isMarked(current)) {
            object_unmark(current);

            current = object_nextLargeObject(current);
        } else {
            size_t currentSize = object_getLargeObjectSize(current);
            Object* next = object_nextLargeObject(current);
            while(next != heapEnd && !object_isMarked(next)) {
                size_t size = object_getLargeObjectSize(next);
                currentSize += size;
                bitmap_clearBit(allocator->bitmap, (word_t*)next);
                next = object_nextLargeObject(next);
            }
            addChunk(allocator, (word_t*)current, currentSize);
            current = next;
        }
    }
    //print(allocator);
}


void print(LargeAllocator* alloc) {
    for(int i = 0; i < LIST_COUNT; i++) {
        if(alloc->freeLists[i].first != NULL) {
            printf("list %d: ", i);
            freeList_print(&alloc->freeLists[i]);
        }
    }
}
