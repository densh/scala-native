//
// Created by Lukas Kellenberger on 01.03.17.
//

#include "bitmap.h"


Bitmap* bitmap_alloc(size_t size, word_t* offset) {
    assert(size % sizeof(word_t) == 0);
    unsigned long nb_words = (size / sizeof(word_t) + BITS_PER_WORD - 1) / BITS_PER_WORD;
    word_t* words = calloc(nb_words, sizeof(word_t));
    Bitmap* bitmap = malloc(sizeof(Bitmap));
    bitmap->words = words;
    bitmap->size = size;
    bitmap->offset = offset;
    return bitmap;
}

void bitmap_set_bit(Bitmap* bitmap, word_t* addr) {
    assert(addr >= bitmap->offset && addr < bitmap->offset + bitmap->size/ sizeof(word_t));
    size_t inner_addr = addr - bitmap->offset;
    bitmap->words[WORD_OFFSET(inner_addr)] |= (1LLU << BIT_OFFSET(inner_addr));
}

void bitmap_clear_bit(Bitmap* bitmap, word_t* addr) {
    assert(addr >= bitmap->offset && addr < bitmap->offset + bitmap->size);

    size_t inner_addr = addr - bitmap->offset;

    bitmap->words[WORD_OFFSET(inner_addr)] &= ~(1LLU << BIT_OFFSET(inner_addr));
}

int bitmap_get_bit(Bitmap* bitmap, word_t* addr) {
    assert(addr >= bitmap->offset && addr < bitmap->offset + bitmap->size);

    size_t inner_addr = addr - bitmap->offset;
    word_t bit = bitmap->words[WORD_OFFSET(inner_addr)] & (1LLU << BIT_OFFSET(inner_addr));
    return bit != 0;
}

void bitmap_clone(Bitmap* bitmap, Bitmap* clone) {
    unsigned long nb_words = (bitmap->size / sizeof(word_t) + BITS_PER_WORD - 1) / BITS_PER_WORD;
    memcpy(clone->words, bitmap->words, nb_words * sizeof(word_t));
}

void bitmap_grow(Bitmap* bitmap, size_t nb_words) {
    size_t current_nb_words = (bitmap->size / sizeof(word_t) + BITS_PER_WORD - 1) / BITS_PER_WORD;
    size_t new_nb_words = current_nb_words + BITS_PER_WORD * nb_words;
    bitmap->words = realloc(bitmap->words, new_nb_words * sizeof(word_t));
    bitmap->size = new_nb_words * sizeof(word_t);
}
