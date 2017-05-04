#ifndef STATE_H
#define STATE_H

#include "free_list.h"
#include "heap.h"
#include "stack.h"
#include "types.h"

extern FreeList *free_list;
extern Heap *heap;
extern Stack *stack;
extern word_t *overflow_current_addr;
extern int overflow;

#endif // STATE_H
