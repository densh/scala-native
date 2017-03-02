#ifndef HEAP_SANITY_H
#define HEAP_SANITY_H

#include <stdio.h>
#include "../free_list.h"
#include "../linked_list.h"
#include "../bitmap.h"
#include "../block.h"
#include "../types.h"
#include "../heap.h"

void heap_sanity_full_check(Heap* heap);
void memory_check(FreeList* free_list, int print);

#endif //HEAP_SANITY_H