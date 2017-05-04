#ifndef TYPES_H
#define TYPES_H

#include <stddef.h>
#include <stdint.h>

#define SMALLEST_BLOCK_SIZE 2
#define LARGEST_CONST 16
#define LOG_LARGEST_CONST 4
#define LINKED_LIST_NUMBER 19
#define MIN_POWER 9
#define MAX_POWER 28
#define CHUNK_LIST_NUMBER (MAX_POWER - MIN_POWER + 1)
#define SMALLEST_CHUNK_SIZE 512
#define MAX_CHUNK_SIZE (256 * 1024 * 1024)
#define BITS_FOR_TAG 8
#define TAG_MASK 0xFF
#define LIST_END NULL

typedef uint64_t word_t;

typedef struct {
    struct {
        int32_t id;
        word_t *name;
        int8_t kind;
    } rt;
    int64_t size;
    struct {
        int32_t from;
        int32_t to;
    } range;
    struct {
        int32_t dyn_method_count;
        word_t *dyn_method_salt;
        word_t *dyn_method_keys;
        word_t *dyn_methods;
    } dynDispatchTable;
    int64_t *refMapStruct;
} Rtti;

typedef struct {
    size_t size;
    word_t *words;
    word_t *offset;
} Bitmap;

typedef enum { tag_allocated = 12, tag_free = 13 } tag_t;

typedef struct {
    uint32_t size;
    tag_t tag;
} Header;

typedef struct Block {
    Header header;
    struct Block *next;
} Block;

typedef struct {
    Block *first;
    Block *last;
} LinkedList;

typedef Block Chunk;

typedef struct {
    LinkedList *chunk_lists[CHUNK_LIST_NUMBER];
    Bitmap *bitmap;
} ChunkAllocator;

typedef struct {
    word_t *chunk[LINKED_LIST_NUMBER];
    LinkedList *list[LINKED_LIST_NUMBER];
    ChunkAllocator *chunk_allocator;
    Bitmap *bitmap;
    word_t *start;
    size_t size;
    size_t free;
} FreeList;

typedef struct {
    word_t *heap_start;
    word_t *heap_end;
    Bitmap *bitmap;
    Bitmap *bitmap_copy;
    FreeList *free_list;
    size_t nb_words;
} Heap;

typedef word_t *Stack_Type;

typedef struct {
    Stack_Type *bottom;
    int nb_words;
    int current;
} Stack;

#endif // TYPES_H
