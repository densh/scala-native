#ifndef MARKANDSWEEP_STACK_H
#define MARKANDSWEEP_STACK_H

#include <stddef.h>
#include "../Log.h"
#include "../headers/ObjectHeader.h"

#define INITIAL_STACK_SIZE (256*1024)

typedef Object* Stack_Type;

typedef struct {
    Stack_Type* bottom;
    size_t nbWords;
    int current;
} Stack;

inline Stack* stack_alloc(size_t size) {
    assert(size % sizeof(Stack_Type) == 0);
    Stack* stack = malloc(sizeof(Stack));
    stack->current = 0;
    stack->bottom = malloc(size);
    stack->nbWords = size / sizeof(Stack_Type);
    return stack;
}

inline bool stack_push(Stack* stack, Stack_Type word) {
    if(stack->current < stack->nbWords) {
        stack->bottom[stack->current++] = word;
        return false;
    } else {
#ifdef PRINT_STACK_OVERFLOW
        printf("Overflow !\n");
#endif
        return true;
    }
}

inline Stack_Type stack_pop(Stack* stack) {
    assert(stack->current > 0);
    return stack->bottom[--stack->current];
}

inline bool stack_isEmpty(Stack* stack) {
    return stack->current == 0;
}


inline void stack_doubleSize(Stack* stack) {
    assert(stack->current == 0);
    size_t nb_words = stack->nbWords * 2;
    stack->nbWords = nb_words;
    free(stack->bottom);
    stack->bottom = malloc(nb_words * sizeof(Stack_Type));
}

#endif //MARKANDSWEEP_STACK_H
