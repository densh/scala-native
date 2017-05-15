#include <stddef.h>
#include <stdint.h>
#include <stdio.h>
#include <memory.h>
#include "CMS.h"
#include "Heap.h"
#include "Marker.h"
#include "Log.h"
#include "Time.h"
#include "pthread.h"
#include "../../safepoint.h"

#define PHASE_NONE 0
#define PHASE_SNOOPON 1
#define PHASE_TRACEON 2
#define PHASE_GETROOTS 3
#define PHASE_GETSNOOPED 4
#define PHASE_TRACEOFF 5

Heap *CMS_heap = NULL;
Stack *CMS_stack = NULL;

//#define CONCURRENT
#ifdef CONCURRENT
volatile int CMS_collectorPhase = PHASE_NONE;
volatile int CMS_mutatorPhase = PHASE_NONE;
volatile bool CMS_snoopOn = false;
volatile bool CMS_traceOn = false;
#endif

void *CMS_allocate(size_t byteSize) {
    assert(CMS_heap != NULL);

    // Divide size by WORD_SIZE, round up and add 1 (for the header) to get the
    // number of words
    uint32_t wordSize = (uint32_t)((byteSize + WORD_SIZE - 1) / WORD_SIZE) + 1;
    word_t *alloc = (word_t *)Heap_alloc(CMS_heap, wordSize);
    if (alloc == NULL) {
        assert(false);
#ifndef CONCURRENT
        CMS_collect();
#endif

        alloc = (word_t *)Heap_alloc(CMS_heap, wordSize);
        if (alloc == NULL) {
            printf("Failed to alloc: %zu\n", byteSize);
            printf("Out of memory\n");
            fflush(stdout);
            exit(1);
        }
    }

    assert(Heap_isWordInHeap(CMS_heap, alloc));

    memset(alloc + 1, 0, (wordSize - 1) * WORD_SIZE);
    // TODO: set object collector to be current allocation color
    return alloc + 1;
}

void CMS_safepoint() {
#ifdef CONCURRENT
    int phase = CMS_collectorPhase;
    switch (phase) {
    case PHASE_SNOOPON:
        CMS_snoopOn = true;
        break;
    case PHASE_TRACEON:
        CMS_traceOn = true;
        break;
    case PHASE_GETROOTS:
        // TODO: swap allocation color
        CMS_snoopOn = false;
        // TODO: get roots
        break;
    case PHASE_GETSNOOPED:
        // TODO: add snooped to roots
        // TODO: clear snooped
        break;
    case PHASE_TRACEOFF:
        CMS_traceOn = false;
        break;
    default:
        assert(false);
        break;
    }
    CMS_mutatorPhase = phase;
    scalanative_safepoint_off();
#endif
}

void CMS_startPhase(int phase) {
#ifdef CONCURRENT
    assert(!scalanative_safepoint_status);
    CMS_collectorPhase = phase;
    scalanative_safepoint_on();
    // busy wait for mutator to reach the safepoint
    while (CMS_mutatorPhase != phase) {
    }
#endif
}

void CMS_initiateCollectionCycle() {
#ifdef CONCURRENT
    CMS_startPhase(PHASE_SNOOPON);
    CMS_startPhase(PHASE_TRACEON);
#endif
}

void CMS_getRoots() {
#ifdef CONCURRENT
    CMS_startPhase(PHASE_GETROOTS);
    CMS_startPhase(PHASE_GETSNOOPED);
#endif
}

void CMS_traceHeap() {
#ifdef CONCURRENT
// TODO: put all objects from roots to mark stack
// TODO: mark stack transitively
#endif
}

void CMS_sweep() {
#ifdef CONCURRENT
    CMS_startPhase(PHASE_TRACEOFF);
// TODO: perform sweep
#endif
}

void CMS_cleanup() {
#ifdef CONCURRENT
// TODO: reset log pointers
// TODO: reset buffer
#endif
}

void *CMS_backgroundThreadEntry(void *unused) {
#ifdef CONCURRENT
    while (true) {
        CMS_initiateCollectionCycle();
        CMS_getRoots();
        CMS_traceHeap();
        CMS_sweep();
        CMS_cleanup();
    }
#endif
    return NULL;
}

void CMS_startBackgroundThread() {
#ifdef CONCURRENT
    pthread_t thread;
    pthread_create(&thread, NULL, CMS_backgroundThreadEntry, NULL);
#endif
}

void CMS_init() {
#ifdef CONCURRENT
    scalanative_safepoint_init();
#endif
    CMS_heap = Heap_create();
    CMS_stack = Stack_alloc(INITIAL_STACK_SIZE);
#ifdef CONCURRENT
    CMS_startBackgroundThread();
#endif
}

void CMS_collect() {
#ifndef CONCURRENT
    printf("GC collection!\n");
    Marker_markRoots(CMS_heap, CMS_stack);
    Heap_collect(CMS_heap);
#endif
}
