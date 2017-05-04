//
// Created by Lukas Kellenberger on 29.04.17.
//

#ifndef MARKANDSWEEP_BITMAP_H
#define MARKANDSWEEP_BITMAP_H

#include <stddef.h>
#include "../GCTypes.h"

typedef struct {
    size_t size;
    word_t* words;
    word_t* offset;
} Bitmap;

#define BITS_PER_WORD (sizeof(word_t) * CHAR_BIT)
#define WORD_OFFSET(b) (b / BITS_PER_WORD)
#define BIT_OFFSET(b)  (b % BITS_PER_WORD)

#define BITMAP_GRANULARITY LARGE_OBJECT_MIN_SIZE

Bitmap* bitmap_alloc(size_t size, word_t* offset);

void bitmap_setBit(Bitmap* bitmap, word_t* addr);

void bitmap_clearBit(Bitmap* bitmap, word_t* addr);

int bitmap_getBit(Bitmap* bitmap, word_t* addr);

void bitmap_grow(Bitmap* bitmap, size_t nb_words);

#endif //MARKANDSWEEP_BITMAP_H
