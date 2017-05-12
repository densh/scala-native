//
// Created by Lukas Kellenberger on 29.04.17.
//

#ifndef CMS_LINKEDLIST_H
#define CMS_LINKEDLIST_H

#include <stdbool.h>
#include <stdio.h>
#include "../Log.h"
#include "../Types.h"
#include "../headers/ObjectHeader.h"

typedef struct {
    Object *first;
    Object *last;
} FreeList;

inline void FreeList_init(FreeList *linkedList) {
    linkedList->first = NULL;
    linkedList->last = NULL;
}

inline bool FreeList_isEmpty(FreeList *linkedList) {
    return linkedList->first == NULL;
}

inline Object *FreeList_removeFirst(FreeList *linkedList) {
    assert(!FreeList_isEmpty(linkedList));

    Object *object = linkedList->first;
    Object *next = object->next;
    // If the block is the first element of the list
    linkedList->first = next;

    // If the block to remove was the last block of the linked_list, update
    // last.
    if (linkedList->last == object) {
        assert(next == NULL);
        linkedList->last = NULL;
    }
    return object;
}

inline void FreeList_addLast(FreeList *linkedList, Object *object) {
    if (linkedList->first == NULL) {
        linkedList->first = object;
    } else {
        // Otherwise the block is the next of the last block
        linkedList->last->next = object;
    }
    // Because the block is added at the end, there is no next and last is the
    // added block
    object->next = NULL;
    linkedList->last = object;
}

inline void FreeList_print(FreeList *freeList) {
    Object *object = freeList->first;
    while (object != NULL) {
        printf("[%p (%u)] -> ", object, Object_getSize(object));
        object = object->next;
    }
    printf("END\n");
    fflush(stdout);
}

#endif // CMS_LINKEDLIST_H
