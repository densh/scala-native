#include "alloc.h"
#include "free_list.h"
#include "gc.h"
#include "heap.h"
#include "state.h"

void *alloc(size_t size) {
    size = (size + sizeof(word_t) - 1) / sizeof(word_t) * sizeof(word_t);
    size_t nb_words = size / sizeof(word_t);
    if (free_list == NULL) {
        scalanative_init();
    }
    word_t *block = free_list_get_block(free_list, nb_words + 1);

    assert(nb_words != 1 || block == NULL || (uintptr_t)block % 2 == 0);
    assert(block == NULL || header_unpack_object_size(block) == nb_words + 1);
    assert(block == NULL || header_unpack_tag(block) == tag_allocated);
    assert(block == NULL || header_unpack_block_size(block) != 2 ||
           (uintptr_t)block % 16 == 0);
    assert(block == NULL ||
           (header_unpack_block_size(block) < 2 * (nb_words + 1) &&
            header_unpack_block_size(block) >= nb_words + 1));

    if (block == NULL) {
        scalanative_collect();
        block = free_list_get_block(free_list, nb_words + 1);

        assert(block == NULL ||
               header_unpack_object_size(block) == nb_words + 1);
        assert(block == NULL || header_unpack_tag(block) == tag_allocated);
        assert(block == NULL || header_unpack_block_size(block) != 2 ||
               (uintptr_t)block % 16 == 0);
        assert(block == NULL ||
               (header_unpack_block_size(block) < 2 * (nb_words + 1) &&
                header_unpack_block_size(block) >= nb_words + 1));

        if (block == NULL) {
            grow_heap(nb_words);
            block = free_list_get_block(free_list, nb_words + 1);
            assert(block == NULL ||
                   header_unpack_object_size(block) == nb_words + 1);
            assert(block == NULL || header_unpack_block_size(block) != 2 ||
                   (uintptr_t)block % 16 == 0);
            assert(block == NULL || header_unpack_tag(block) == tag_allocated);
            assert(block == NULL || bitmap_get_bit(heap_->bitmap, block));
            memset(block + 1, 0, nb_words * sizeof(word_t));
            return block + 1;
        }
        memset(block + 1, 0, nb_words * sizeof(word_t));
        return block + 1;
    }
    assert(block + header_unpack_block_size(block) - 1 <
           free_list->start + free_list->size / sizeof(word_t));
    memset(block + 1, 0, nb_words * sizeof(word_t));

    return block + 1;
}
