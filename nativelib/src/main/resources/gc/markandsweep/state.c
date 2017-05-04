#include "state.h"

FreeList *free_list = NULL;
Heap *heap = NULL;
Stack *stack = NULL;
word_t *overflow_current_addr = NULL;
int overflow = 0;
