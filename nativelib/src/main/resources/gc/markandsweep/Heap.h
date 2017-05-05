#ifndef MARKANDSWEEP_HEAP_H
#define MARKANDSWEEP_HEAP_H

#include <stdint.h>
#include <stddef.h>
#include "GCTypes.h"
#include "LargeAllocator.h"
#include "Allocator.h"
#include "headers/ObjectHeader.h"

#define HEAP_INITIAL_SIZE (128*1024*1024UL)

typedef struct {
    Allocator* allocator;
    word_t* heapStart;
    word_t* heapEnd;
    size_t heapSize;
    LargeAllocator* largeAllocator;
    word_t* largeHeapStart;
    word_t* largeHeapEnd;
    size_t largeHeapSize;
} Heap;

Heap* heap_create();

Object* heap_alloc(Heap* heap, uint32_t size);

inline bool heap_isWordInSmallHeap(Heap* heap, word_t* word) {
    return word != NULL && word >= heap->heapStart && word < heap->heapEnd;
}

inline bool heap_isWordInLargeHeap(Heap* heap, word_t* word) {
    return word != NULL && word >= heap->largeHeapStart && word < heap->largeHeapEnd;
}

inline bool heap_isWordInHeap(Heap* heap, word_t* word) {
    return word != NULL && (heap_isWordInSmallHeap(heap, word) || heap_isWordInLargeHeap(heap, word));
}

inline bool heap_isObjectInHeap(Heap* heap, Object* object) {
    return heap_isWordInHeap(heap, (word_t*)object);
}

void heap_collect(Heap* heap);

#endif //MARKANDSWEEP_HEAP_H
