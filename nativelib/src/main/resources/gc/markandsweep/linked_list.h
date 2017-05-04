#ifndef LINKED_LIST_H
#define LINKED_LIST_H

#include <stddef.h>
#include "block.h"
#include "types.h"
#include "bitmap.h"

/**
 * Allocates a new linked_list struct and set all fields to NULL
 */
inline LinkedList *linked_list_alloc() {
    LinkedList *list = malloc(sizeof(LinkedList));
    list->first = NULL;
    list->last = NULL;
    return list;
}

/**
 * Adds a block to the end of the list, does not merge blocks.
 * Sets the size to the given size and the tag to `tag_free`.
 */
inline void linked_list_add_block(LinkedList *list, Block *block,
                                  size_t block_size_with_header) {
    // Set the header fields
    block->header.size = block_size_with_header;
    block->header.tag = tag_free;

    assert(block_size_with_header >= 2);

    // If the list is empty, set the block as first block
    if (list->first == NULL) {
        list->first = block;
    } else {
        // Otherwise the block is the next of the last block
        list->last->next = block;
    }
    // Because the block is added at the end, there is no next and last is the
    // added block
    block->next = LIST_END;
    list->last = block;
}

/**
 * Removes the block `block` from the linked list.
 * Sets the size of the block to size and the tag to `tag_allocated`.
 * Expects previous to be NULL, or previous.next to be block.
 * If previous is NULL, block is expected to be the first block of the
 * linked_list.
 */
inline void linked_list_remove_block(LinkedList *list, Block *block,
                                     size_t block_size_with_header) {
    assert(block != NULL);

    Block *next = block->next;

    // If the block is the first element of the list
    list->first = next;

    // If the block to remove was the last block of the linked_list, update
    // last.
    if (list->last == block) {
        assert(next == LIST_END);
        list->last = NULL;
    }

    block->header.size = block_size_with_header;
    block->header.tag = tag_allocated;
}

#endif // LINKED_LIST_H
