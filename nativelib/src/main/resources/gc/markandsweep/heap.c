#include "heap.h"
#include <sys/mman.h>

#define MAX_SIZE 64*1024*1024*1024L
// Allow read and write
#define HEAP_MEM_PROT (PROT_READ | PROT_WRITE)
// Map private anonymous memory, and prevent from reserving swap
#define HEAP_MEM_FLAGS (MAP_NORESERVE | MAP_PRIVATE | MAP_ANONYMOUS)
// Map anonymous memory (not a file)
#define HEAP_MEM_FD -1
#define HEAP_MEM_FD_OFFSET 0

Heap* heap_alloc(size_t size) {
    Heap* heap = malloc(sizeof(Heap));
    size_t nb_words = size / sizeof(word_t);

    word_t* heap_start = mmap(NULL, MAX_SIZE, HEAP_MEM_PROT, HEAP_MEM_FLAGS, HEAP_MEM_FD, HEAP_MEM_FD_OFFSET);

    heap->nb_words = nb_words;
    heap->heap_start = heap_start;
    heap->heap_end = heap_start + nb_words;

    Bitmap* bitmap = bitmap_alloc(size, heap_start);
    Bitmap* bitmap_copy = bitmap_alloc(size, heap_start);
    heap->bitmap = bitmap;
    heap->bitmap_copy = bitmap_copy;

    heap->free_list = free_list_create(nb_words, heap_start, bitmap);

    return heap;
}

int heap_in_heap(Heap* heap, word_t* block) {
    return block >= heap->heap_start && block < heap->heap_end;
}

int heap_cannot_be_const(Heap* heap, word_t* block) {
    return block >= heap->heap_start;
}

word_t* heap_next_block(Heap* heap, word_t* block) {
    assert(heap_in_heap(heap, block));
    size_t block_size_with_header = header_unpack_block_size(block);
    word_t* next = block + block_size_with_header;

    return next == heap->heap_end ? NULL : next;
}

void heap_grow(Heap* heap, size_t nb_words) {

    nb_words = (nb_words + SMALLEST_CHUNK_SIZE - 1) / SMALLEST_CHUNK_SIZE * SMALLEST_CHUNK_SIZE;

    assert(nb_words % SMALLEST_CHUNK_SIZE == 0);

    bitmap_grow(heap->bitmap, nb_words);
    bitmap_grow(heap->bitmap_copy, nb_words);
    word_t* new_block = heap->heap_end;

    heap->heap_end += nb_words;
    heap->nb_words += nb_words;

    heap->free_list->size += nb_words * sizeof(word_t);

    free_list_add_chunk(heap->free_list, new_block, nb_words);
}
