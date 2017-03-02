#include "free_list_utils.h"

MemoryStats free_list_get_stats(LinkedList** linked_list, int size) {
    MemoryStats stats;
    size_t total = 0;
    size_t largest_word = 0;
    for(int i=0; i < size; i++) {
        LinkedList* list = linked_list[i];
        Block* current = list->first;
        long count = 0;
        while(current != NULL) {
            count++;
            size_t size = current->header.size;
            if(size > largest_word) {
                largest_word = size;
            }
            total += size;
            current = current->next;
        }
        stats.list_counts[i] = count;
    }



    stats.largest_word = largest_word;
    stats.total = total;

    return stats;
}

void free_list_print_block_counts(MemoryStats stats, int size) {
    printf("counts: [");
    for(int i=0; i < size; i++) {
        printf("%zu, ", stats.list_counts[i]);
    }
    printf("%zu]\n", stats.list_counts[size - 1]);
}

int count_in_chunk(word_t* chunk, int index) {
    if(chunk == NULL) {
        return 0;
    }
    size_t block_size = index_to_block_size(index);
    int rem = SMALLEST_CHUNK_SIZE - ((uintptr_t)chunk % SMALLEST_CHUNK_SIZE);
    return rem / block_size;
}

void free_list_print_chunks(word_t** chunks, int size) {
    printf("counts: [");
    for(int i=0; i < size; i++) {
        printf("%d, ", count_in_chunk(chunks[i], i));
    }
    printf("%d]\n", count_in_chunk(chunks[size - 1], size - 1));
}

void free_list_print_stats(FreeList* list) {
    MemoryStats free_list_stats = free_list_get_stats(list->list, LINKED_LIST_NUMBER);
    printf("### STATS ###\n");
    free_list_print_block_counts(free_list_stats, LINKED_LIST_NUMBER);
    printf("total free: %zu / %zu\n", free_list_stats.total, list->size / sizeof(word_t));
    printf("max: %zu\n", free_list_stats.largest_word);
    printf("#############\n");
    free_list_print_chunks(list->chunk, LINKED_LIST_NUMBER);
    printf("#############\n");
    MemoryStats alloc_stats = free_list_get_stats(list->chunk_allocator->chunk_lists, CHUNK_LIST_NUMBER);
    free_list_print_block_counts(alloc_stats, CHUNK_LIST_NUMBER);
    printf("total free: %zu / %zu\n", alloc_stats.total, list->size / sizeof(word_t));
    printf("max: %zu\n", alloc_stats.largest_word);
    printf("#############\n");
}



void free_list_print(FreeList* list) {
    printf("linked lists:");
    for(int i=0; i < LINKED_LIST_NUMBER; i++) {
        if(list->list[i]->first != NULL) {
            printf("%d: ", i);
            linked_list_print(list->list[i]);
            linked_list_check(list->list[i], i, list->bitmap);
        }
    }
    printf("\n");

    printf("chunk_allocator: \n");
    for(int i = 0; i < CHUNK_LIST_NUMBER; i++) {
        if(list->chunk_allocator->chunk_lists[i]->first != NULL) {
            printf("%d: ", i);
            linked_list_print(list->chunk_allocator->chunk_lists[i]);
            linked_list_check(list->list[i], i, NULL);
        }
    }

    printf("Chunks: \n");
    for(int i=0; i < LINKED_LIST_NUMBER; i++) {
        if(list->chunk[i] != NULL) {
            printf("%d: %p\n", i, list->chunk[i]);
        }
    }
    printf("\n");
}

void check_chunks(FreeList* free_list) {
    for(int i=0; i < LINKED_LIST_NUMBER; i ++) {
        word_t* chunk = free_list->chunk[i];
        if(chunk != NULL) {
            word_t* chunk_end = chunk - (((uintptr_t)chunk % (SMALLEST_CHUNK_SIZE * sizeof(word_t)))/sizeof(word_t)) + SMALLEST_CHUNK_SIZE;
            word_t* current = chunk;
            while (current != chunk_end) {
                if(bitmap_get_bit(free_list->bitmap, current)) {
                    assert(header_unpack_block_size(current) == index_to_block_size(i));
                }
                current++;
            }
        }
    }
}

void check_chunk_allocator(ChunkAllocator* chunk_allocator) {
    for(int i=0; i < CHUNK_LIST_NUMBER; i++) {
        linked_list_check(chunk_allocator->chunk_lists[i], i + LINKED_LIST_NUMBER, chunk_allocator->bitmap);
    }
}

