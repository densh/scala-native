#ifndef CMS_MARKER_H
#define CMS_MARKER_H

#include <limits.h>
#include "Heap.h"
#include "datastructures/Stack.h"
#include "Object.h"

void Marker_mark(Heap *heap, Stack *);
void Marker_markRoots(Heap *heap, Stack *stack);

#endif // CMS_MARKER_H
