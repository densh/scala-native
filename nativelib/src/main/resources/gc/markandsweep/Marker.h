//
// Created by Lukas Kellenberger on 29.04.17.
//

#ifndef MARKANDSWEEP_MARKER_H
#define MARKANDSWEEP_MARKER_H

#include <limits.h>
#include "Heap.h"
#include "ProgramRoots.h"
#include "datastructures/Stack.h"
#include "Object.h"

typedef struct {
    word_t* currentAddress;
} OverflowHandler;

void marker_markRoots(Heap*, Roots*, Stack*);
void marker_mark(Heap* heap, Stack*);
void mark_roots(Heap* heap, Stack* stack);

#endif //MARKANDSWEEP_MARKER_H
