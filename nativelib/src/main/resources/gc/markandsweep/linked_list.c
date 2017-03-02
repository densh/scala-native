//
// Created by Lukas Kellenberger on 01.03.17.
//

#include <stdlib.h>
#include <printf.h>
#include "linked_list.h"

LinkedList* linked_list_alloc() {
    LinkedList* list = malloc(sizeof(LinkedList));
    list->first = NULL;
    list->last = NULL;
    return list;
}


void linked_list_add_block(LinkedList* list, Block* block, size_t block_size_with_header) {
    // Set the header fields
    block->header.size = block_size_with_header;
    block->header.tag = tag_free;

    assert(block_size_with_header >= 2);

    // If the list is empty, set the block as first block
    if(list->first == NULL) {
        list->first = block;
    } else {
        // Otherwise the block is the next of the last block
        list->last->next = block;
    }
    // Because the block is added at the end, there is no next and last is the added block
    block->next = LIST_END;
    list->last = block;
}

void linked_list_remove_block(LinkedList* list, Block* block, size_t block_size_with_header) {
    assert(block != NULL);

    Block* next = block->next;

    // If the block is the first element of the list
    list->first = next;


    // If the block to remove was the last block of the linked_list, update last.
    if(list->last == block) {
        assert(next == LIST_END);
        list->last = NULL;
    }

    block->header.size = block_size_with_header;
    block->header.tag = tag_allocated;
}
