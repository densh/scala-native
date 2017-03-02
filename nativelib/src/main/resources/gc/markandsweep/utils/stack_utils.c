#include "stack_utils.h"

void print_stack(Stack* stack, Heap* heap) {
    for(int i=0; i < stack->current; i++) {
        word_t* block = stack->bottom[i];
        Rtti* rtti = *(Rtti**)(block + 1);
        printf("%d %lu %p\n",rtti->rt.id, block - heap->heap_start, block);
    }
}