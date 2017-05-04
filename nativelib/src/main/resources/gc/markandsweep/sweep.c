#include "sweep.h"
#include "chunk_allocator.h"
#include "free_list.h"
#include "heap.h"
#include "sweep.h"
#include "state.h"

// Sweeps one chunk.
// Return the size of memory swept if it's one contiguous block, 0 otherwise.
int sweep_chunk(word_t *chunk) {
    assert((uintptr_t)chunk % SMALLEST_CHUNK_SIZE == 0);
    // block size of the first block of the chunk.
    size_t block_size = header_unpack_block_size(chunk);

    // If the size is larger than CHUNK_SIZE / 2 it was directly allocated by
    // chunk allocator
    if (block_size > SMALLEST_CHUNK_SIZE / 2) {
        if (!bitmap_get_bit(free_list->bitmap, chunk)) {
            bitmap_set_bit(free_list->bitmap, chunk);
            // !!!! NEEDS TO SKIP MORE THAN CHUNK_SIZE
            return 0;
        } else {
            // If the block is free
            return block_size;
        }
    }
    // Otherwise memory was allocated using bump allocator, with blocks of size
    // `block_size`
    word_t *current = chunk;
    size_t current_size = 0;

    int full_chunk = 1;
    // While we don't hit the end of the chunk.
    while (current <= chunk + (SMALLEST_CHUNK_SIZE - block_size)) {
        current_size = block_size;
        // Current block is alive, set bit to 1 and go to next block
        // !!! DIFF BETWEEN UNALLOCATED CHUNK SPACE AND MARKED DATA
        if (!bitmap_get_bit(heap->bitmap, current) &&
            header_unpack_tag(current) == tag_allocated) {
            bitmap_set_bit(heap->bitmap, current);
            current += block_size;
            full_chunk = 0;
        } else {
            // Block is not alive, merge with next while possible
            word_t *next = current + block_size;
            // CHECK BITMAP
            while (next <= chunk + (SMALLEST_CHUNK_SIZE - block_size) &&
                   header_unpack_tag(next) != tag_allocated) {
                current_size = current_size + block_size;
                bitmap_clear_bit(heap->bitmap, next);
                next = next + block_size;
            }
            // If accumulated size is smaller than a full chunk, add all block
            // to linked_list.
            if (current_size <= SMALLEST_CHUNK_SIZE - block_size) {
                while (current != next) {
                    free_list_add_block(free_list, current, block_size);
                    current += block_size;
                }
                full_chunk = 0;
            }
            current = next;
        }
    }

    return full_chunk ? SMALLEST_CHUNK_SIZE : 0;
}

// FIX THIS, SWEEP_CHUNK RETURN
void sweep() {
    word_t *current = heap->heap_start;
    size_t current_size = 0;
    size_t chunk_size;

    free_list_clear_lists(free_list);

    // While we dont hit the end of the heap
    while (current != heap->heap_end &&
           current != heap->heap_end + SMALLEST_CHUNK_SIZE) {
        current_size = SMALLEST_CHUNK_SIZE;

        if ((chunk_size = sweep_chunk(current))) {
            word_t *next = current + chunk_size;
            current_size = chunk_size;
            while (next < heap->heap_end && (chunk_size = sweep_chunk(next))) {
                current_size += chunk_size;
                bitmap_clear_bit(free_list->bitmap, next);
                next += chunk_size;
            }
            free_list_add_chunk(free_list, current, current_size);

            current =
                next + ((header_unpack_block_size(next) < SMALLEST_CHUNK_SIZE)
                            ? SMALLEST_CHUNK_SIZE
                            : header_unpack_block_size(next));
            assert((uintptr_t)current % 512 == 0);
        } else {
            size_t increment =
                ((header_unpack_block_size(current) < SMALLEST_CHUNK_SIZE)
                     ? SMALLEST_CHUNK_SIZE
                     : header_unpack_block_size(current));
            assert(increment % 512 == 0);
            current += increment;
            assert((uintptr_t)current % 512 == 0);
        }
    }
}
