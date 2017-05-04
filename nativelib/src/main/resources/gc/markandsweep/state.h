#ifndef STATE_H
#define STATE_H

#include "types.h"

extern FreeList *free_list;
extern Heap *heap;
extern Stack *stack;
extern word_t *overflow_current_addr;
extern int overflow;

#endif // STATE_H
