#ifndef CMS_BLOCKHEADER_H
#define CMS_BLOCKHEADER_H

#include <stdint.h>
#include <stdbool.h>
#include "../Log.h"
#include "../Constants.h"
#include "../Types.h"

// Represents the headerline of an block.
// It contains the size of the objects that the block will contain in number of
// words and a mark bit

typedef struct {
    uint32_t objectSize;
    uint32_t mark;
} BlockHeader;

typedef struct FreeBlockHeader FreeBlockHeader;

struct FreeBlockHeader {
    BlockHeader header;
    FreeBlockHeader *next;
};

static inline bool Block_isMarked(BlockHeader *blockHeader) {
    return blockHeader->mark == 0x1;
}

static inline void Block_mark(BlockHeader *blockHeader) {
    blockHeader->mark = 0x1;
}

static inline void Block_unmark(BlockHeader *blockHeader) {
    blockHeader->mark = 0x0;
}

static inline void Block_setObjectSize(BlockHeader *blockHeader,
                                       uint32_t objectSize) {
    blockHeader->objectSize = objectSize;
}

static inline uint32_t Block_getObjectSize(BlockHeader *blockHeader) {
    return blockHeader->objectSize;
}

static inline BlockHeader *Block_getBlockHeader(word_t *word) {
    return (BlockHeader *)((word_t)word & BLOCK_MASK);
}

static inline word_t *Block_getFirstWord(BlockHeader *blockHeader) {
    return (word_t *)blockHeader + 1;
}

static inline word_t *Block_getBlockEnd(word_t *word) {
    return (word_t *)Block_getBlockHeader(word) + BLOCK_SIZE;
}

#endif // CMS_BLOCKHEADER_H
