//
// Created by Lukas Kellenberger on 29.04.17.
//

#ifndef MARKANDSWEEP_LINKEDLIST_H
#define MARKANDSWEEP_LINKEDLIST_H

#include <stdbool.h>
#include "../GCTypes.h"
#include "../headers/ObjectHeader.h"

typedef struct {
    Object* first;
    Object* last;
} FreeList;

void freeList_init(FreeList* linkedList);
bool freeList_isEmpty(FreeList* linkedList);
Object* freeList_removeFirst(FreeList* linkedList);
void freeList_addLast(FreeList* linkedList, Object* object);
void freeList_print(FreeList* freeList);

#endif //MARKANDSWEEP_LINKEDLIST_H
