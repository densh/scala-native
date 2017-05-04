//
// Created by Lukas Kellenberger on 29.04.17.
//

#include <stddef.h>
#include <stdio.h>
#include "FreeList.h"
#include "../headers/ObjectHeader.h"


void freeList_init(FreeList* linkedList) {
    linkedList->first = NULL;
    linkedList->last = NULL;
}
bool freeList_isEmpty(FreeList* linkedList) {
    return linkedList->first == NULL;
}

Object* freeList_removeFirst(FreeList* linkedList) {
    assert(!freeList_isEmpty(linkedList));

    Object* object = linkedList->first;
    Object* next = object->next;
    // If the block is the first element of the list
    linkedList->first = next;


    // If the block to remove was the last block of the linked_list, update last.
    if(linkedList->last == object) {
        assert(next == NULL);
        linkedList->last = NULL;
    }
    return object;
}

void freeList_addLast(FreeList* linkedList, Object* object) {
    if(linkedList->first == NULL) {
        linkedList->first = object;
    } else {
        // Otherwise the block is the next of the last block
        linkedList->last->next = object;
    }
    // Because the block is added at the end, there is no next and last is the added block
    object->next = NULL;
    linkedList->last = object;
}

void freeList_print(FreeList* freeList) {
    Object* object = freeList->first;
    while(object != NULL) {
        printf("[%p (%u)] -> ", object, object_getSize(object));
        object = object->next;
    }
    printf("END\n");
    fflush(stdout);
}