//
// Created by Lukas Kellenberger on 01.03.17.
//

#include "block.h"
#include "free_list.h"

int log2_floor(size_t v) {
    static const int MultiplyDeBruijnBitPosition[32] =
            {
                    0, 9, 1, 10, 13, 21, 2, 29, 11, 14, 16, 18, 22, 25, 3, 30,
                    8, 12, 20, 28, 15, 17, 24, 7, 19, 27, 23, 6, 26, 5, 4, 31
            };

    v |= v >> 1; // first round down to one less than a power of 2
    v |= v >> 2;
    v |= v >> 4;
    v |= v >> 8;
    v |= v >> 16;

    return MultiplyDeBruijnBitPosition[(uint32_t)(v * 0x07C4ACDDU) >> 27];
}

int log2_ceil(size_t value) {
    return log2_floor(2 * value - 1);
}

Block* block_add_offset(Block* block, size_t offset) {
    return (Block*) ((word_t*)block + offset);
}

size_t header_unpack_object_size(word_t* block) {

    return ((Header*)block)->size;
}

size_t header_unpack_block_size(word_t* block) {
    size_t object_size = ((Header*)block)->size;
    return object_size_to_block_size(object_size);
}

tag_t header_unpack_tag(word_t* block) {
    return ((Header*)block)->tag;
}

void header_pack_size(word_t* block, size_t size) {
    Header* header = (Header*) block;
    header->size = size;
}

void header_pack_tag(word_t* block, tag_t tag) {
    Header* header = (Header*) block;
    header->tag = tag;
}

void header_pack(word_t* block, size_t size, tag_t tag) {
    Header* header = (Header*) block;
    header->size = size;
    header->tag = tag;
}