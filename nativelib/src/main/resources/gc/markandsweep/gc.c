#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "log.h"
#include "gc.h"
#include "free_list.h"
#include "bitmap.h"
#include "linked_list.h"
#include "alloc.h"
#include "mark.h"
#include "sweep.h"

#define UNW_LOCAL_ONLY

#include <libunwind.h>
#include <setjmp.h>
#include "sweep.h"
#include "state.h"

#define CHUNK 256 * 1024

void scalanative_init() {
    heap = heap_alloc(CHUNK);
    free_list = heap->free_list;
}

void *scalanative_alloc_raw(size_t size) { return alloc(size); }

void *scalanative_alloc_raw_atomic(size_t size) { return alloc(size); }

void *scalanative_alloc(void *info, size_t size) {
    void **alloc = (void **)scalanative_alloc_raw(size);
    *alloc = info;
    return (void *)alloc;
}

void scalanative_collect() {
    mark_roots(heap);
    sweep();
}
