//
// Created by Lukas Kellenberger on 01.03.17.
//

#ifndef BLOCK_H
#define BLOCK_H

#include <stddef.h>
#include <stdint.h>
#include "types.h"

typedef enum {
    tag_allocated = 12,
    tag_free = 13
} tag_t;

#define BITS_FOR_TAG 8
#define TAG_MASK 0xFF

typedef struct {
    uint32_t size;
    tag_t tag;
} Header;

typedef struct Block Block;
struct Block {
    Header header;
    Block* next;
};

int log2_floor(size_t value);

int log2_ceil(size_t value);

Block* block_add_offset(Block* block, size_t offset);

size_t header_unpack_object_size(word_t* block);
size_t header_unpack_block_size(word_t* block);

tag_t header_unpack_tag(word_t* block);

void header_pack_size(word_t* block, size_t size);

void header_pack_tag(word_t* block, tag_t tag);

void header_pack(word_t* block, size_t size, tag_t tag);

#endif //BLOCK_H
