#ifndef MARKANDSWEEP_BLOCKHEADER_H
#define MARKANDSWEEP_BLOCKHEADER_H

#include <stdint.h>
#include <stdbool.h>
#include "../Log.h"
#include "../Constants.h"
#include "../GCTypes.h"


// Represents the headerline of an block.
// It contains the size of the objects that the block will contain in number of words
// and a mark bit

typedef struct {
    uint32_t objectSize;
    uint32_t mark;
} BlockHeader;


typedef struct FreeBlockHeader FreeBlockHeader;

struct FreeBlockHeader {
    BlockHeader header;
    FreeBlockHeader* next;
};

static inline bool block_isMarked(BlockHeader* blockHeader) {
    return blockHeader->mark == 0x1;
}

static inline void block_mark(BlockHeader* blockHeader) {
    blockHeader->mark = 0x1;
}

static inline void block_unmark(BlockHeader* blockHeader) {
    blockHeader->mark = 0x0;
}

static inline void block_setObjectSize(BlockHeader* blockHeader, uint32_t objectSize) {
    blockHeader->objectSize = objectSize;
}

static inline uint32_t block_getObjectSize(BlockHeader* blockHeader) {
    return blockHeader->objectSize;
}

static inline BlockHeader* block_getBlockHeader(word_t* word) {
    return (BlockHeader*) ((word_t)word & BLOCK_MASK);
}

static inline word_t* block_getFirstWord(BlockHeader* blockHeader) {
    return (word_t*)blockHeader + 1;
}

static inline word_t* block_getBlockEnd(word_t* word) {
    return (word_t*)block_getBlockHeader(word) + BLOCK_SIZE;
}


#endif //MARKANDSWEEP_BLOCKHEADER_H
