#include <stddef.h>
#include <stdint.h>
#include <stdio.h>
#include <memory.h>
#include "CMS.h"
#include "Heap.h"
#include "Marker.h"
#include "Log.h"
#include "Time.h"

Heap *heap = NULL;
Stack *stack = NULL;

void CMS_init() {
    heap = Heap_create();
    stack = Stack_alloc(INITIAL_STACK_SIZE);
}

void *CMS_allocate(size_t byteSize) {
    assert(heap != NULL);

    // Divide size by WORD_SIZE, round up and add 1 (for the header) to get the
    // number of words
    uint32_t wordSize = (uint32_t)((byteSize + WORD_SIZE - 1) / WORD_SIZE) + 1;
    word_t *alloc = (word_t *)Heap_alloc(heap, wordSize);
    if (alloc == NULL) {
        CMS_collect();

        alloc = (word_t *)Heap_alloc(heap, wordSize);
        if (alloc == NULL) {
            printf("Failed to alloc: %zu\n", byteSize);
            printf("Out of memory\n");
            fflush(stdout);
            exit(1);
        }
    }

    assert(Heap_isWordInHeap(heap, alloc));

    memset(alloc + 1, 0, (wordSize - 1) * WORD_SIZE);
    return alloc + 1;
}

void CMS_collect() {
#ifdef DEBUG_PRINT
    printf("Collect\n");
    fflush(stdout);
#endif
    Marker_markRoots(heap, stack);
    Heap_collect(heap);
#ifdef DEBUG_PRINT
    printf("End collect\n");
    fflush(stdout);
#endif
}
