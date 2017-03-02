#ifndef HEAP_H
#define HEAP_H

#include "types.h"
#include "block.h"
#include "bitmap.h"
#include "free_list.h"

typedef struct {
    word_t* heap_start;
    word_t* heap_end;
    Bitmap* bitmap;
    Bitmap* bitmap_copy;
    FreeList* free_list;
    size_t nb_words;
} Heap;

Heap* heap_alloc(size_t size);

int heap_in_heap(Heap* heap, word_t* block);
int heap_cannot_be_const(Heap* heap, word_t* block);

word_t* heap_next_block(Heap* heap, word_t* block);

void heap_grow(Heap* heap, size_t nb_words);


#endif // HEAP_H