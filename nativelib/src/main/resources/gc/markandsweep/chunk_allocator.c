#include "chunk_allocator.h"


#include "free_list.h"
#include <stdio.h>


inline static int size_to_linked_list(size_t size) {
    assert(size >= SMALLEST_CHUNK_SIZE);
    return log2_floor(size) - MIN_POWER;
}



ChunkAllocator* chunk_allocator_create(Bitmap* bitmap) {
    ChunkAllocator* chunk_allocator = malloc(sizeof(ChunkAllocator));
    chunk_allocator->bitmap = bitmap;

    for(int i=0; i < CHUNK_LIST_NUMBER; i++) {
        chunk_allocator->chunk_lists[i] = linked_list_alloc();
    }

    return chunk_allocator;
}

void check_blockk(Bitmap* bitmap, word_t* block, size_t size) {
    assert(bitmap_get_bit(bitmap, block));
    for(int i=1; i < size; i++) {
        assert(!bitmap_get_bit(bitmap, block + i));
    }
}

void chunk_allocator_add_chunk(ChunkAllocator* chunk_allocator, Chunk* block, size_t total_block_size) {
    assert(total_block_size >= SMALLEST_CHUNK_SIZE);
    assert(total_block_size % 2 == 0);
    size_t remaining_size = total_block_size;
    word_t* current = (word_t*) block;
    while(remaining_size > 0) {
        int log2_f = log2_floor(remaining_size);
        size_t chunk_size = 1L << log2_f;
        chunk_size = chunk_size > MAX_CHUNK_SIZE ? MAX_CHUNK_SIZE : chunk_size;
        assert(chunk_size >= SMALLEST_CHUNK_SIZE && chunk_size <= MAX_CHUNK_SIZE);
        int list_index = size_to_linked_list(chunk_size);
        linked_list_add_block(chunk_allocator->chunk_lists[list_index], (Block*) current, chunk_size);
        bitmap_set_bit(chunk_allocator->bitmap, current);
        //check_blockk(chunk_allocator->bitmap, current, chunk_size);
        current += chunk_size;
        remaining_size -= chunk_size;
    }
}

Chunk* chunk_allocator_get_chunk(ChunkAllocator* chunk_allocator, size_t requested_chunk_size) {
    size_t chunk_size = 1L << log2_ceil(requested_chunk_size);

    int list_index = size_to_linked_list(chunk_size);
    Chunk* chunk = NULL;

    while(list_index <= CHUNK_LIST_NUMBER - 1 && (chunk = chunk_allocator->chunk_lists[list_index]->first) == NULL) {
        ++list_index;
    }
    if(chunk == NULL) {
        return NULL;
    }

    size_t received_chunk_size = chunk->header.size;

    if(received_chunk_size - SMALLEST_CHUNK_SIZE >= requested_chunk_size) {
        Block* remaining_chunk = block_add_offset(chunk, requested_chunk_size);
        linked_list_remove_block(chunk_allocator->chunk_lists[list_index], chunk, requested_chunk_size);
        size_t remaining_chunk_size = received_chunk_size - requested_chunk_size;
        chunk_allocator_add_chunk(chunk_allocator, (Chunk*)remaining_chunk, remaining_chunk_size);
    } else {
        linked_list_remove_block(chunk_allocator->chunk_lists[list_index], chunk, chunk_size);
    }
    assert((uintptr_t)chunk % (SMALLEST_CHUNK_SIZE * sizeof(word_t)) == 0);
    return chunk;

}

void chunk_allocator_clear_lists(ChunkAllocator* chunk_allocator) {
    for(int i = 0; i < CHUNK_LIST_NUMBER; i++) {
        chunk_allocator->chunk_lists[i]->first = NULL;
        chunk_allocator->chunk_lists[i]->last = NULL;
    }
}

