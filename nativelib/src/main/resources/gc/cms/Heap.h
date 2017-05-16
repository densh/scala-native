#ifndef CMS_HEAP_H
#define CMS_HEAP_H

#include <stdint.h>
#include <stddef.h>
#include "Types.h"
#include "LargeAllocator.h"
#include "Allocator.h"
#include "headers/ObjectHeader.h"

#define HEAP_INITIAL_SIZE (4096 * 1024 * 1024UL)

typedef struct {
    Allocator *allocator;
    word_t *heapStart;
    word_t *heapEnd;
    size_t heapSize;
    LargeAllocator *largeAllocator;
    word_t *largeHeapStart;
    word_t *largeHeapEnd;
    size_t largeHeapSize;
} Heap;

Heap *Heap_create();

Object *Heap_alloc(Heap *heap, uint32_t size);

inline bool Heap_isWordInSmallHeap(Heap *heap, word_t *word) {
    return word != NULL && word >= heap->heapStart && word < heap->heapEnd;
}

inline bool Heap_isWordInLargeHeap(Heap *heap, word_t *word) {
    return word != NULL && word >= heap->largeHeapStart &&
           word < heap->largeHeapEnd;
}

inline bool Heap_isWordInHeap(Heap *heap, word_t *word) {
    return word != NULL && (Heap_isWordInSmallHeap(heap, word) ||
                            Heap_isWordInLargeHeap(heap, word));
}

inline bool Heap_isObjectInHeap(Heap *heap, Object *object) {
    return Heap_isWordInHeap(heap, (word_t *)object);
}

void Heap_collect(Heap *heap);

#endif // CMS_HEAP_H
