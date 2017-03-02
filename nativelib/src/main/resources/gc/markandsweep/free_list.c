//
// Created by Lukas Kellenberger on 01.03.17.
//

#include "free_list.h"


int object_size_to_index(size_t object_size) {
    if(object_size <= SMALLEST_BLOCK_SIZE) {
        return 0;
    } else if (object_size <= LARGEST_CONST) {
        return object_size - SMALLEST_BLOCK_SIZE;
    } else {
        int log = log2_ceil(object_size);
        return LARGEST_CONST - SMALLEST_BLOCK_SIZE + log - LOG_LARGEST_CONST;
    }
}

size_t index_to_block_size(int index) {
    if(index <= LARGEST_CONST - SMALLEST_BLOCK_SIZE) {
        return index + SMALLEST_BLOCK_SIZE;
    } else {
        return 1L << (index - LARGEST_CONST + SMALLEST_BLOCK_SIZE + LOG_LARGEST_CONST);
    }
}

size_t object_size_to_block_size(size_t object_size) {
    if(object_size <= SMALLEST_BLOCK_SIZE) {
        return SMALLEST_BLOCK_SIZE;
    } else if (object_size <= LARGEST_CONST) {
        return object_size;
    } else if (object_size <= SMALLEST_CHUNK_SIZE/2) {
        return 1L << log2_ceil(object_size);
    } else {
        return (object_size + SMALLEST_CHUNK_SIZE - 1) / SMALLEST_CHUNK_SIZE * SMALLEST_CHUNK_SIZE;
    }
}

FreeList* free_list_create(size_t nb_words, word_t* heap_start, Bitmap* bitmap) {
    FreeList* free_list = malloc(sizeof(FreeList));

    word_t* words = heap_start;

    free_list->bitmap = bitmap;
    free_list->size = nb_words * sizeof(word_t);
    free_list->start = words;
    free_list->chunk_allocator = chunk_allocator_create(bitmap);



    for(int i=0; i < LINKED_LIST_NUMBER; i++) {
        free_list->list[i] = linked_list_alloc();
        free_list->chunk[i] = NULL;
    }

    chunk_allocator_add_chunk(free_list->chunk_allocator, (Block*) words, nb_words);
    free_list->free = nb_words;

    return free_list;
}

void check_block(FreeList* free_list, word_t* block, size_t size) {
    assert(bitmap_get_bit(free_list->bitmap, block));
    for(int i=1; i < object_size_to_block_size(size); i++) {
        assert(!bitmap_get_bit(free_list->bitmap, block + i));
    }
}

void free_list_add_chunk(FreeList* list, word_t* chunk, size_t chunk_size) {
    //memset(chunk, 0, chunk_size * sizeof(word_t));
    chunk_allocator_add_chunk(list->chunk_allocator, (Chunk*)chunk, chunk_size);
    list->free += chunk_size;
}

// Adds a block to one of the linkedlist.
void free_list_add_block(FreeList* list, word_t* block, size_t block_size_with_header) {
    //memset(block, 0, block_size_with_header * sizeof(word_t));
    assert(block_size_with_header != 2 || (uintptr_t)block % 16 == 0);
    const int list_index = object_size_to_index(block_size_with_header);
    linked_list_add_block(list->list[list_index], (Block*) block, block_size_with_header);
    list->free += block_size_with_header;
    bitmap_set_bit(list->bitmap, block);
    /*for(int i=1; i < block_size_with_header; i++) {
        assert(!bitmap_get_bit(list->bitmap, block + i));
    }*/
}


word_t* free_list_get_block(FreeList* list, size_t object_size) {
    Block* block = NULL;
    if(object_size > SMALLEST_CHUNK_SIZE / 2) {
        size_t block_size = object_size_to_block_size(object_size);
        //printf("Large chunk! %lu %lu\n", object_size, block_size);
        block = (Block*)chunk_allocator_get_chunk(list->chunk_allocator, block_size);
        if (block == NULL) {
            return NULL;
        }
        block->header.size = object_size;
        block->header.tag = tag_allocated;
        bitmap_set_bit(list->bitmap, (word_t*)block);
        //check_block(list, (word_t*) block, object_size);
        return (word_t*)block;
    }


    int list_index = object_size_to_index(object_size);
    size_t block_size = index_to_block_size(list_index);

    block = (Block*) list->chunk[list_index];

    if(block != NULL) {
        list->chunk[list_index] += block_size;
        block->header.size = object_size;
        block->header.tag = tag_allocated;
        bitmap_set_bit(list->bitmap, (word_t*)block);
        int remaining = SMALLEST_CHUNK_SIZE - ((uintptr_t)list->chunk[list_index] % (SMALLEST_CHUNK_SIZE * sizeof(word_t)))/sizeof(word_t);
        list->chunk[list_index] = remaining < block_size || remaining == SMALLEST_CHUNK_SIZE ? NULL : list->chunk[list_index];
        //check_block(list, (word_t*) block, object_size);
        return (word_t*)block;
    }

    block = list->list[list_index]->first;
    if(block != NULL) {
        linked_list_remove_block(list->list[list_index], block, object_size);
        bitmap_set_bit(list->bitmap, (word_t*) block);
        //check_block(list, (word_t*) block, object_size);
        return (word_t*)block;
    }

    block = (Block*)chunk_allocator_get_chunk(list->chunk_allocator, SMALLEST_CHUNK_SIZE);
    if(block == NULL) {
        return NULL;
    }

    list->chunk[list_index] = (word_t*)block;
    list->chunk[list_index] += block_size;
    block->header.size = object_size;
    block->header.tag = tag_allocated;
    bitmap_set_bit(list->bitmap, (word_t*)block);
    int remaining = SMALLEST_CHUNK_SIZE - ((uintptr_t)list->chunk[list_index] % (SMALLEST_CHUNK_SIZE * sizeof(word_t)))/sizeof(word_t);
    list->chunk[list_index] = remaining < block_size || remaining == SMALLEST_CHUNK_SIZE ? NULL : list->chunk[list_index];
    //check_block(list, (word_t*) block, object_size);
    return (word_t*)block;

}

void free_list_clear_lists(FreeList* list) {
    list->free = 0;
    for(int i = 0; i < LINKED_LIST_NUMBER; i++) {
        list->list[i]->first = NULL;
        list->list[i]->last = NULL;
        list->chunk[i] = NULL;
    }
    chunk_allocator_clear_lists(list->chunk_allocator);
}
