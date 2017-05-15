#ifndef CMS_OBJECT_H
#define CMS_OBJECT_H

#include "headers/ObjectHeader.h"
#include "Heap.h"
#include "LargeAllocator.h"

extern int __object_array_id;

Object *Object_getObjectConservative(Heap *heap, word_t *word);
Object *Object_getLargeObjectConservative(Heap *heap, word_t *word);
void Object_markObject(Object *object);
Object *Object_nextObject(Object *object);
uint32_t Object_getLargeObjectSize(Object *object);
Object *Object_nextLargeObject(Object *object);

#endif // CMS_OBJECT_H
