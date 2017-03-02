#ifndef CHUNK_ALLOCATOR_H
#define CHUNK_ALLOCATOR_H


#include "block.h"
#include "linked_list.h"

#define MIN_POWER 9
#define MAX_POWER 28

#define CHUNK_LIST_NUMBER (MAX_POWER - MIN_POWER + 1)

#define SMALLEST_CHUNK_SIZE 512
#define MAX_CHUNK_SIZE (256*1024*1024)

typedef Block Chunk;

typedef struct {
    LinkedList* chunk_lists[CHUNK_LIST_NUMBER];
    Bitmap* bitmap;
} ChunkAllocator;

ChunkAllocator* chunk_allocator_create(Bitmap* bitmap);

void chunk_allocator_add_chunk(ChunkAllocator* chunk_allocator, Chunk* block, size_t total_block_size);

Chunk* chunk_allocator_get_chunk(ChunkAllocator* chunk_allocator, size_t chunk_size);

void chunk_allocator_clear_lists(ChunkAllocator* chunk_allocator);


#endif //CHUNK_ALLOCATOR_H