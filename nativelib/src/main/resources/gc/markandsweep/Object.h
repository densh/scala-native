//
// Created by Lukas Kellenberger on 01.05.17.
//

#ifndef MARKANDSWEEP_OBJECT_H
#define MARKANDSWEEP_OBJECT_H

#include "headers/ObjectHeader.h"
#include "Heap.h"
#include "LargeAllocator.h"

Object* object_getObjectConservative(Heap* heap, word_t* word);
Object* object_getLargeObjectConservative(Heap* heap, word_t* word);
void object_markObject(Object* object);
Object* object_nextObject(Object* object);
uint32_t object_getLargeObjectSize(Object* object);
Object* object_nextLargeObject(Object* object);

#endif //MARKANDSWEEP_OBJECT_H
