#include "CMS.h"

void scalanative_init() { CMS_init(); }

void *scalanative_alloc(void *info, size_t size) {
    void **alloc = (void **)CMS_allocate(size);
    *alloc = info;
    return (void *)alloc;
}

void *scalanative_alloc_raw(size_t size) { return CMS_allocate(size); }

void *scalanative_alloc_raw_atomic(size_t size) { return CMS_allocate(size); }

void scalanative_safepoint() { CMS_safepoint(); }

void scalanative_collect() {}
