//
// Created by Lukas Kellenberger on 01.03.17.
//

#include <stddef.h>
#include "block.h"
#include "types.h"
#include "bitmap.h"


#ifndef LINKED_LIST_H
#define LINKED_LIST_H


typedef struct {
    Block* first;
    Block* last;
} LinkedList;

#define LIST_END NULL


/**
 *
 * Allocates a new linked_list struct and set all fields to NULL
 */
LinkedList* linked_list_alloc();

/**
 * Adds a block to the end of the list, does not merge blocks.
 * Sets the size to the given size and the tag to `tag_free`.
 */
void linked_list_add_block(LinkedList* list, Block* block, size_t block_size);

/**
 * Removes the block `block` from the linked list.
 * Sets the size of the block to size and the tag to `tag_allocated`.
 * Expects previous to be NULL, or previous.next to be block.
 * If previous is NULL, block is expected to be the first block of the linked_list.
 */
void linked_list_remove_block(LinkedList* list, Block* block, size_t size);




#endif //LINKED_LIST_H
