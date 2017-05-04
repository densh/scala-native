#include <stddef.h>
#include <stdint.h>
#include <stdio.h>
#include <memory.h>
#include "Heap.h"
#include "Marker.h"
#include "Log.h"


Heap* heap = NULL;
Stack* stack = NULL;

void scalanative_init() {
    heap = heap_create();
    stack = stack_alloc(INITIAL_STACK_SIZE);
}

void scalanative_collect() {
#ifdef DEBUG_PRINT
    printf("Collect\n");
    fflush(stdout);
#endif
    mark_roots(heap, stack);
    heap_collect(heap);
#ifdef DEBUG_PRINT
    printf("End collect\n");
    fflush(stdout);
#endif
}

void* allocate(size_t byteSize) {
    assert(heap != NULL);

    // Divide size by WORD_SIZE, round up and add 1 (for the header) to get the number of words
    uint32_t wordSize = (uint32_t)((byteSize + WORD_SIZE - 1) / WORD_SIZE) + 1;
    word_t* alloc = (word_t*)heap_alloc(heap, wordSize);
    if(alloc == NULL) {
        scalanative_collect();

        alloc = (word_t*)heap_alloc(heap, wordSize);
        if(alloc == NULL) {
            printf("Failed to alloc: %zu\n", byteSize);
            printf("Out of memory\n");
            fflush(stdout);
            exit(1);
        }
    }

    assert(heap_isWordInHeap(heap, alloc));

    memset(alloc + 1, 0, (wordSize - 1) * WORD_SIZE);
    return alloc + 1;
}

void* scalanative_alloc(void* info, size_t size) {
    void** alloc = (void**) allocate(size);
    *alloc = info;
    return (void*) alloc;
}

void* scalanative_alloc_raw(size_t size) {
    return allocate(size);
}

void* scalanative_alloc_raw_atomic(size_t size) {
    return allocate(size);
}



