#ifndef MARKANDSWEEP_MARKER_H
#define MARKANDSWEEP_MARKER_H

#include <limits.h>
#include "Heap.h"
#include "datastructures/Stack.h"
#include "Object.h"

void marker_mark(Heap* heap, Stack*);
void mark_roots(Heap* heap, Stack* stack);

#endif //MARKANDSWEEP_MARKER_H
