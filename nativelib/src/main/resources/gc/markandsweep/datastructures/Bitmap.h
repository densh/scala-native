#ifndef MARKANDSWEEP_BITMAP_H
#define MARKANDSWEEP_BITMAP_H

#include <stddef.h>
#include <stdlib.h>
#include <limits.h>
#include "../Log.h"
#include "../GCTypes.h"
#include "../Constants.h"

typedef struct {
    size_t size;
    word_t* words;
    word_t* offset;
} Bitmap;

#define BITS_PER_WORD (sizeof(word_t) * CHAR_BIT)
#define WORD_OFFSET(b) (b / BITS_PER_WORD)
#define BIT_OFFSET(b)  (b % BITS_PER_WORD)

#define BITMAP_GRANULARITY LARGE_OBJECT_MIN_SIZE

// Allocates a bitmap that can contain `size` bits
inline Bitmap* bitmap_alloc(size_t size, word_t* offset) {
    assert(size % sizeof(word_t) == 0);
    assert(size % LARGE_OBJECT_MIN_SIZE == 0);
    size_t nbBlocks = size / LARGE_OBJECT_MIN_SIZE;
    unsigned long nbWords = (nbBlocks + BITS_PER_WORD - 1) / BITS_PER_WORD;
    void* words = calloc(nbWords, sizeof(word_t));
    Bitmap* bitmap = malloc(sizeof(Bitmap));
    bitmap->words = words;
    bitmap->size = size;
    bitmap->offset = offset;
    return bitmap;
}

// Used to map an address to an index
inline size_t addressToIndex(word_t* offset, word_t* addr) {
    return (addr - offset) / BITMAP_GRANULARITY;
}

// Set the bit of the given address to `1`
inline void bitmap_setBit(Bitmap* bitmap, word_t* addr) {
    assert(addr >= bitmap->offset && addr < bitmap->offset + (bitmap->size * LARGE_OBJECT_MIN_SIZE / WORD_SIZE));
    size_t index = addressToIndex(bitmap->offset, addr);
    bitmap->words[WORD_OFFSET(index)] |= (1LLU << BIT_OFFSET(index));
}

// Set the bit of the given address to `0`
inline void bitmap_clearBit(Bitmap* bitmap, word_t* addr) {
    assert(addr >= bitmap->offset && addr < bitmap->offset + (bitmap->size * LARGE_OBJECT_MIN_SIZE / WORD_SIZE));

    size_t index = addressToIndex(bitmap->offset, addr);

    bitmap->words[WORD_OFFSET(index)] &= ~(1LLU << BIT_OFFSET(index));
}
// Returns the bit of the given address
inline int bitmap_getBit(Bitmap* bitmap, word_t* addr) {
    assert(addr >= bitmap->offset && addr < bitmap->offset + (bitmap->size * LARGE_OBJECT_MIN_SIZE / WORD_SIZE));

    size_t index = addressToIndex(bitmap->offset, addr);
    word_t bit = bitmap->words[WORD_OFFSET(index)] & (1LLU << BIT_OFFSET(index));
    return bit != 0;
}

// Grows the bitmap to contain `nbWords` more words
inline void bitmap_grow(Bitmap* bitmap, size_t nbWords) {
    size_t currentNbWords = (bitmap->size / sizeof(word_t) + BITS_PER_WORD - 1) / BITS_PER_WORD;
    size_t newNbWords = currentNbWords + BITS_PER_WORD * nbWords;
    bitmap->words = realloc(bitmap->words, newNbWords * sizeof(word_t));
    bitmap->size = newNbWords * sizeof(word_t);
}

#endif //MARKANDSWEEP_BITMAP_H
