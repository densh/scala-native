//
// Created by Lukas Kellenberger on 01.03.17.
//

#include <limits.h>
#include <memory.h>
#include <stdlib.h>
#include "types.h"

#ifndef MARKANDSWEEP_BITMAP_H
#define MARKANDSWEEP_BITMAP_H

typedef struct {
    size_t size;
    word_t* words;
    word_t* offset;
} Bitmap;

#define BITS_PER_WORD (sizeof(word_t) * CHAR_BIT)
#define WORD_OFFSET(b) (b / BITS_PER_WORD)
#define BIT_OFFSET(b)  (b % BITS_PER_WORD)

Bitmap* bitmap_alloc(size_t size, word_t* offset);

void bitmap_set_bit(Bitmap* bitmap, word_t* addr);

void bitmap_clear_bit(Bitmap* bitmap, word_t* addr);

int bitmap_get_bit(Bitmap* bitmap, word_t* addr);

void bitmap_print(Bitmap* bitmap);
void bitmap_print_with_rtti(Bitmap* bitmap);

void bitmap_clone(Bitmap* bitmap, Bitmap* clone);

void bitmap_grow(Bitmap* bitmap, size_t nb_words);

#endif //MARKANDSWEEP_BITMAP_H
