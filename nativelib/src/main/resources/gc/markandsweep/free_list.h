//
// Created by Lukas Kellenberger on 01.03.17.
//

#include "bitmap.h"
#include "types.h"
#include "linked_list.h"
#include "chunk_allocator.h"

#ifndef MARKANDSWEEP_FREE_LIST_H
#define MARKANDSWEEP_FREE_LIST_H

#define SMALLEST_BLOCK_SIZE 2
#define LARGEST_CONST 16
#define LOG_LARGEST_CONST 4
#define LINKED_LIST_NUMBER 19



typedef struct {
    word_t* chunk[LINKED_LIST_NUMBER];
    LinkedList* list[LINKED_LIST_NUMBER];
    ChunkAllocator* chunk_allocator;
    Bitmap* bitmap;
    word_t* start;
    size_t size;
    size_t free;
} FreeList;

/**
 * Allocates a FreeList struct, creates the linked lists and
 * adds a block of size `size` starting at `heap_start` to the free_list.
 * Size in number of words.
 */
FreeList* free_list_create(size_t size, word_t* heap_start, Bitmap* bitmap);

void free_list_add_chunk(FreeList* list, word_t* chunk, size_t chunk_size);

/**
 * Adds a block to one of the linked_lists, depending on the size of the block.
 * Size in number of words.
 */
void free_list_add_block(FreeList* list, word_t* block, size_t block_size);

/**
 * Returns a block of size `size` or `size + 1`. Where the size is in number of words (header included)
 * If there is no block of size `size` or `size + q` split a larger block.
 * If there is no larger block, return NULL
 */
word_t* free_list_get_block(FreeList* list, size_t size);

/**
 * Empties all linked_list. Does not free the memory.
 */
void free_list_clear_lists(FreeList* list);

size_t object_size_to_block_size(size_t object_size);

size_t index_to_block_size(int index);

int object_size_to_index(size_t object_size);



#endif //MARKANDSWEEP_FREE_LIST_H
