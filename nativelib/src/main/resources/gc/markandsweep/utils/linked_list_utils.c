#include "linked_list_utils.h"
#include "../free_list.h"

void linked_list_print(LinkedList* list) {
    Block* current = list->first;
    printf("list: ");
    while(current != NULL) {
        size_t size = current->header.size;
        printf("[%p (%zu | %zu)] -> ", current, header_unpack_block_size((word_t*)current), header_unpack_object_size((word_t*)current));
        current = current->next;
    }
    printf("\n");
}

void linked_list_check(LinkedList* list, int index, Bitmap* bitmap) {
    Block* current = list->first;
    while(current != NULL) {
        size_t size = current->header.size;
        assert(object_size_to_block_size(size) == index_to_block_size(index));
        for(word_t* c= (word_t*)current + 1; c < (word_t*)current + size; c +=1) {
            assert(bitmap == NULL || !bitmap_get_bit(bitmap, c));
        }
        current = current->next;
    }
}