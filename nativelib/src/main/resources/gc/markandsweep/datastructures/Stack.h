//
// Created by Lukas Kellenberger on 29.04.17.
//

#ifndef MARKANDSWEEP_STACK_H
#define MARKANDSWEEP_STACK_H

#include <stddef.h>
#include "../headers/ObjectHeader.h"

#define PRINT_STACK_OVERFLOW

#define INITIAL_STACK_SIZE (256*1024)

typedef Object* Stack_Type;

typedef struct {
    Stack_Type* bottom;
    size_t nbWords;
    int current;
} Stack;

Stack* stack_alloc(size_t size);

bool stack_push(Stack* stack, Stack_Type word);

Stack_Type stack_pop(Stack* stack);

bool stack_isEmpty(Stack* stack);

void stack_doubleSize(Stack* stack);

#endif //MARKANDSWEEP_STACK_H
