//
// Created by Lukas Kellenberger on 29.04.17.
//

#include "Heap.h"
#include <stdio.h>
#include <sys/mman.h>

#define MAX_SIZE 64*1024*1024*1024L
// Allow read and write
#define HEAP_MEM_PROT (PROT_READ | PROT_WRITE)
// Map private anonymous memory, and prevent from reserving swap
#define HEAP_MEM_FLAGS (MAP_NORESERVE | MAP_PRIVATE | MAP_ANONYMOUS)
// Map anonymous memory (not a file)
#define HEAP_MEM_FD -1
#define HEAP_MEM_FD_OFFSET 0

word_t* mapAndAlign(unsigned long long alignmentMask, int blockSize) {
    word_t* heapStart = mmap(NULL, MAX_SIZE, HEAP_MEM_PROT, HEAP_MEM_FLAGS, HEAP_MEM_FD, HEAP_MEM_FD_OFFSET);

    // Heap start not aligned on
    if(((word_t)heapStart & alignmentMask) != (word_t)heapStart) {
        printf("Not aligned %p\n", (word_t*)alignmentMask);
        word_t* previousBlock = (word_t*)((word_t)heapStart & alignmentMask);
        heapStart = previousBlock + blockSize;
        printf("aligned: %p\n", heapStart);
    }
    printf("Map addr: %p\n", heapStart);
    return heapStart;
}

Heap* heap_create() {
    Heap* heap = malloc(sizeof(Heap));

    word_t* heapStart = mapAndAlign(BLOCK_MASK, BLOCK_SIZE);

    size_t heapSize = HEAP_INITIAL_SIZE / WORD_SIZE;
    heap->allocator = allocator_init(heapStart, heapSize);
    heap->heapStart = heapStart;
    heap->heapEnd = heapStart + heapSize;
    heap->heapSize = heapSize;

    word_t* largeHeapStart = mapAndAlign(LARGE_OBJECT_MIN_SIZE_MASK, LARGE_OBJECT_MIN_SIZE);
    size_t largeHeapSize = HEAP_INITIAL_SIZE / WORD_SIZE;
    heap->largeAllocator = largeAllocator_create(largeHeapStart, largeHeapSize);
    heap->largeHeapStart = largeHeapStart;
    heap->largeHeapEnd = largeHeapStart + largeHeapSize;
    heap->largeHeapSize = largeHeapSize;

    return heap;
}

Object* heap_alloc(Heap* heap, uint32_t size) {
    if(size >= LARGE_OBJECT_MIN_SIZE) {
        Object* object = largeAllocator_alloc(heap->largeAllocator, size);
        if(object != NULL) {
            object_setSize(object, size);
            object_setTag(object, object_allocated);
            object_setType(object, object_large);
            assert((word_t)object % (LARGE_OBJECT_MIN_SIZE * WORD_SIZE) == 0);
        }
        return object;
    } else {
        Object* object = allocator_alloc(heap->allocator, size);
        if(object != NULL) {
            object_setSize(object, size);
            object_setTag(object, object_allocated);
            object_setType(object, object_standard);
        }
        return object;
    }
}

bool heap_isWordInHeap(Heap* heap, word_t* word) {
    return word != NULL && (heap_isWordInSmallHeap(heap, word) || heap_isWordInLargeHeap(heap, word));
}
bool heap_isWordInSmallHeap(Heap* heap, word_t* word) {
    return word != NULL && word >= heap->heapStart && word < heap->heapEnd;
}
bool heap_isWordInLargeHeap(Heap* heap, word_t* word) {
    return word != NULL && word >= heap->largeHeapStart && word < heap->largeHeapEnd;
}
bool heap_isObjectInHeap(Heap* heap, Object* object) {
    return heap_isWordInHeap(heap, (word_t*)object);
}

void heap_collect(Heap* heap) {
    allocator_sweep(heap->allocator);
    largeAllocator_sweep(heap->largeAllocator);
}