#ifndef CHUNK_ALLOCATOR_H
#define CHUNK_ALLOCATOR_H

#include "block.h"
#include "linked_list.h"

ChunkAllocator *chunk_allocator_create(Bitmap *bitmap);

void chunk_allocator_add_chunk(ChunkAllocator *chunk_allocator, Chunk *block,
                               size_t total_block_size);

Chunk *chunk_allocator_get_chunk(ChunkAllocator *chunk_allocator,
                                 size_t chunk_size);

void chunk_allocator_clear_lists(ChunkAllocator *chunk_allocator);

#endif // CHUNK_ALLOCATOR_H
