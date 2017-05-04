#ifndef STACK_H
#define STACK_H

#include <stdlib.h>
#include <stddef.h>
#include <stdio.h>
#include "types.h"

Stack *stack_alloc(size_t size);

int stack_push(Stack *stack, Stack_Type word);

Stack_Type stack_pop(Stack *stack);

int stack_is_empty(Stack *stack);

void stack_double_size(Stack *stack);

#endif // STACK_H
