#include <stdio.h>
#include "Object.h"
#include "Log.h"



Object* object_getObjectConservative(Heap* heap, word_t* word) {
    BlockHeader* blockHeader = block_getBlockHeader(word);

    if(word == (word_t*)blockHeader) {
        return NULL;
    }

    size_t size = block_getObjectSize(blockHeader);
    word_t addressAsInteger = (word_t)word;
    word_t diff = addressAsInteger - (word_t)blockHeader;
    // Align to the multiple of the size, add one word because of the chunk header
    Object* object = (Object*)(addressAsInteger - (diff % (size * WORD_SIZE)) + WORD_SIZE);
    if((word_t*)object + size <= block_getBlockEnd(word) && object_isAllocated(object)) {
        return object;
    }
#ifdef DEBUG_PRINT
    else {
        printf("Not allocated (Small) %p %p\n", word, object);
    }
#endif
    return NULL;
}

Object* getLargeInnerPointer(LargeAllocator* allocator, word_t* word) {
    word_t* current = (word_t*)((word_t)word & LARGE_OBJECT_MIN_SIZE_MASK);

    while(!bitmap_getBit(allocator->bitmap, current)) {
        current -= LARGE_OBJECT_MIN_SIZE;
    }
    Object* object = (Object*) current;

    if(word < (word_t*)object + object_getSize(object)) {
        return object;
    } else {
        return NULL;
    }
}

Object* object_getLargeObjectConservative(Heap* heap, word_t* word) {
    if(bitmap_getBit(heap->largeAllocator->bitmap, word)) {
        Object* object = (Object*) word;
        if(object_isAllocated(object)) {
            return object;
        } else {
#ifdef DEBUG_PRINT
            printf("Not allocated (Large) %p\n", word);
#endif
            return NULL;
        }
    } else {
        Object* object = getLargeInnerPointer(heap->largeAllocator, word);
        if(object != NULL && object_isAllocated(object)) {
            return object;
        } else {
#ifdef DEBUG_PRINT
            printf("Not allocated (Large) %p %p\n", word, object);
#endif
            return NULL;
        }
    }
}

void object_markObject(Object* object) {
    if(object_isStandard(object)) {
        BlockHeader *blockHeader = block_getBlockHeader((word_t *) object);
        block_mark(blockHeader);
    }
    object_mark(object);
}

Object* object_nextObject(Object* object) {
    assert(object_getSize(object) != 0);
    return (Object*)((word_t*)object + object_getSize(object));
}

uint32_t object_getLargeObjectSize(Object* object) {
    uint32_t size = object_getSize(object);
    return (size + LARGE_OBJECT_MIN_SIZE - 1) / LARGE_OBJECT_MIN_SIZE * LARGE_OBJECT_MIN_SIZE;
}

Object* object_nextLargeObject(Object* object) {
    return (Object*)((word_t*)object + object_getLargeObjectSize(object));

}