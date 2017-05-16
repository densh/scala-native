#ifndef CMS_MARKER_H
#define CMS_MARKER_H

#include <limits.h>
#include "Object.h"
#include "Heap.h"
#include "datastructures/Stack.h"
#include "datastructures/Buffer.h"

void Marker_replicate(Heap *heap, Buffer *buffer, Object *object);
void Marker_collectRoots(Heap *heap, Buffer *buffer);
void Marker_markBuffer(Heap *heap, Stack *stack, Buffer *buffer, Buffer *temp);

#endif // CMS_MARKER_H
