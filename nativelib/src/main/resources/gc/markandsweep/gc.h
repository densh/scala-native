#ifndef UNTITLED_GC_H
#define UNTITLED_GC_H

#include <stddef.h>
#include <stdint.h>
#include "types.h"

void scalanative_init();
void *scalanative_alloc(void *info, size_t size);
void *scalanative_alloc_raw(size_t size);
void *scalanative_alloc_raw_atomic(size_t size);
void scalanative_collect();

#endif // UNTITLED_GC_H
