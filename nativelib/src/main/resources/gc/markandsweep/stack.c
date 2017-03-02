#include "stack.h"

Stack* stack_alloc(size_t size) {
    assert(size % sizeof(Stack_Type) == 0);
    Stack* stack = malloc(sizeof(Stack));
    stack->current = 0;
    stack->bottom = malloc(size);
    stack->nb_words = size / sizeof(Stack_Type);
    return stack;
}

int stack_push(Stack* stack, Stack_Type word) {
    if(stack->current < stack->nb_words) {
        stack->bottom[stack->current++] = word;
        return 0;
    } else {
        #ifdef STACK_OVERFLOW_PRINT
            printf("Overflow !\n");
        #endif

        return 1;
    }
}

Stack_Type stack_pop(Stack* stack) {
    assert(stack->current > 0);
    return stack->bottom[--stack->current];
}

int stack_is_empty(Stack* stack) {
    return stack->current == 0;
}


void stack_double_size(Stack* stack) {
    assert(stack->current == 0);
    int nb_words = stack->nb_words * 2;;
    stack->nb_words = nb_words;
    free(stack->bottom);
    stack->bottom = malloc(nb_words * sizeof(Stack_Type));
}