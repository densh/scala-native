//
// Created by Lukas Kellenberger on 01.03.17.
//

#include "gc.h"
#include "free_list.h"
#include <stdio.h>
#include <stdlib.h>
#include "bitmap.h"
#include "linked_list.h"
#include "mark.h"
#include "utils/free_list_utils.h"
#include <time.h>
#include "timer.h"
#include "utils/heap_sanity.h"
#include "utils/heap_printer.h"


#define UNW_LOCAL_ONLY

#include <libunwind.h>
#include <setjmp.h>

FreeList* free_list = NULL;
Heap* heap_ = NULL;

#define CHUNK 256*1024
Timer* in_gc = NULL;
Timer* outside_gc = NULL;

// Sweeps one chunk.
// Return the size of memory swept if it's one contiguous block, 0 otherwise.
int sweep_chunk(word_t* chunk) {
    assert((uintptr_t)chunk % SMALLEST_CHUNK_SIZE == 0);
    // block size of the first block of the chunk.
    size_t block_size = header_unpack_block_size(chunk);
    //printf("sweep chunk %ld size: %zu alloc: %d\n", (chunk - heap_->heap_start)/ SMALLEST_CHUNK_SIZE, block_size/SMALLEST_CHUNK_SIZE, header_unpack_tag(chunk) == tag_allocated);

    // If the size is larger than CHUNK_SIZE / 2 it was directly allocated by chunk allocator
    if(block_size > SMALLEST_CHUNK_SIZE / 2) {
        if(!bitmap_get_bit(free_list->bitmap, chunk)) {
            bitmap_set_bit(free_list->bitmap, chunk);
            // !!!! NEEDS TO SKIP MORE THAN CHUNK_SIZE
            return 0;
        } else {
            // If the block is free
            /*for(int i=1; i < block_size; i++) {
                assert(!bitmap_get_bit(free_list->bitmap, chunk + i));
            }*/
            return block_size;
        }
    }
    // Otherwise memory was allocated using bump allocator, with blocks of size `block_size`
    word_t* current = chunk;
    size_t current_size = 0;

    int full_chunk = 1;

    // While we don't hit the end of the chunk.
    while(current <= chunk + (SMALLEST_CHUNK_SIZE - block_size)) {
        current_size = block_size;
        // Current block is alive, set bit to 1 and go to next block
        // !!! DIFF BETWEEN UNALLOCATED CHUNK SPACE AND MARKED DATA
        if(!bitmap_get_bit(heap_->bitmap, current) && header_unpack_tag(current) == tag_allocated) {
            bitmap_set_bit(heap_->bitmap, current);
            current += block_size;
            full_chunk = 0;
        } else {
            // Block is not alive, merge with next while possible
            word_t* next = current + block_size;
            // CHECK BITMAP
            while(next <= chunk + (SMALLEST_CHUNK_SIZE - block_size) && header_unpack_tag(next) != tag_allocated) {
                current_size = current_size + block_size;
                bitmap_clear_bit(heap_->bitmap, next);
                next = next + block_size;
            }
            // If accumulated size is smaller than a full chunk, add all block to linked_list.
            if(current_size <= SMALLEST_CHUNK_SIZE - block_size) {
                while(current != next) {
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

//FIX THIS, SWEEP_CHUNK RETURN
void sweep() {
    word_t* current = heap_->heap_start;
    size_t current_size = 0;
    size_t chunk_size;

    free_list_clear_lists(free_list);

    // While we dont hit the end of the heap
    while(current != heap_->heap_end && current != heap_->heap_end + SMALLEST_CHUNK_SIZE) {
        current_size = SMALLEST_CHUNK_SIZE;

        if((chunk_size = sweep_chunk(current))) {
            word_t* next = current + chunk_size;
            current_size = chunk_size;
            while(next < heap_->heap_end && (chunk_size = sweep_chunk(next))) {
                current_size += chunk_size;
                bitmap_clear_bit(free_list->bitmap, next);
                next += chunk_size;
            }
            free_list_add_chunk(free_list, current, current_size);

            current = next + ((header_unpack_block_size(next) < SMALLEST_CHUNK_SIZE) ? SMALLEST_CHUNK_SIZE : header_unpack_block_size(next));
            assert((uintptr_t)current % 512 == 0);
        } else {
            size_t increment = ((header_unpack_block_size(current) < SMALLEST_CHUNK_SIZE) ? SMALLEST_CHUNK_SIZE : header_unpack_block_size(current));
            assert(increment % 512 == 0);
            current += increment;
            assert((uintptr_t)current % 512 == 0);
        }

        /*if(current > heap_->heap_end) {
            printf("over!\n");
        }*/
    }
}


/*void sweep() {
    word_t* current = heap_->heap_start;
    size_t current_size = 0;

    free_list_clear_lists(free_list);

    while(current != NULL) {
        current_size = header_unpack_size(current);
        // Current block is alive, set bit to 1 and go to next block
        if(!bitmap_get_bit(heap_->bitmap, current)) {
            bitmap_set_bit(heap_->bitmap, current);
            current = heap_next_block(heap_, current);

        } else {
            // Block is not alive, merge with next while possible
            word_t* next = heap_next_block(heap_, current);
            while(next != NULL && bitmap_get_bit(heap_->bitmap, next)) {
                current_size = current_size + header_unpack_size(next);
                bitmap_clear_bit(heap_->bitmap, next);
                next = heap_next_block(heap_, next);
            }
            free_list_add_block(free_list, current, current_size);
            current = next;
        }
    }
}*/

void scalanative_init() {
    heap_ = heap_alloc(CHUNK);
    free_list = heap_->free_list;
    in_gc = gc_timer_create();
    outside_gc = gc_timer_create();
    gc_timer_start(outside_gc);
}

void grow_heap(size_t nb_words) {
    size_t current_size = heap_->nb_words;
    size_t increment = current_size/4;
    size_t chunk_size = 1L << log2_ceil(nb_words);
    if(nb_words > 0 && chunk_size > increment) {
        increment = chunk_size;
    }
    printf("growing heap current: %zu, increment %zu\n", current_size*sizeof(word_t), increment*sizeof(word_t));
    fflush(stdout);
    heap_grow(heap_, increment);
}

void* scalanative_alloc_raw(size_t size) {
    size = (size + sizeof(word_t) - 1 ) / sizeof(word_t) * sizeof(word_t);
    size_t nb_words = size / sizeof(word_t);
    if(free_list == NULL) {
        scalanative_init();
    }
    //free_list_print(free_list);
    //printf("alloc %zu words\n", nb_words + 1);
    word_t* block = free_list_get_block(free_list, nb_words + 1);
    /*if(block != NULL) {
        size_t inner_addr = block - heap_->heap_start;
        int block_id = inner_addr / 512;
        int word_id = inner_addr % 512;
        printf("allocated at: %d, %d\n", block_id, word_id);
    }*/
    //head_printer_print(heap_);

    //check_chunks(free_list);
    //heap_sanity_full_check(heap_);
    //check_chunk_allocator(free_list->chunk_allocator);
    assert(nb_words != 1 || block == NULL || (uintptr_t)block % 2 == 0);
    /*if(block != NULL) {
        assert(bitmap_get_bit(free_list->bitmap, block));
        for(int i = 1; i < header_unpack_block_size(block); i++) {
            assert(!bitmap_get_bit(free_list->bitmap, block + i));
        }
    }*/
    assert(block == NULL || header_unpack_object_size(block) == nb_words + 1);
    assert(block == NULL || header_unpack_tag(block) == tag_allocated);
    assert(block == NULL || header_unpack_block_size(block) != 2 || (uintptr_t)block % 16 == 0);
    assert(block == NULL || (header_unpack_block_size(block) < 2*(nb_words +1) && header_unpack_block_size(block) >= nb_words + 1));

    if(block == NULL) {
        //printf("FAIL alloc %zu words\n", nb_words + 1);

        scalanative_collect();
        block = free_list_get_block(free_list, nb_words + 1);
        /*if(block != NULL) {
            size_t inner_addr = block - heap_->heap_start;
            int block_id = inner_addr / 512;
            int word_id = inner_addr % 512;
            printf("allocated at: %d, %d\n", block_id, word_id);
        }*/
        //head_printer_print(heap_);

        /*if(block != NULL) {
            assert(bitmap_get_bit(free_list->bitmap, block));
            for(int i = 1; i < header_unpack_block_size(block); i++) {
                assert(!bitmap_get_bit(free_list->bitmap, block + i));
            }
        }*/
        assert(block == NULL || header_unpack_object_size(block) == nb_words + 1);
        assert(block == NULL || header_unpack_tag(block) == tag_allocated);
        assert(block == NULL || header_unpack_block_size(block) != 2 || (uintptr_t)block % 16 == 0);
        assert(block == NULL || (header_unpack_block_size(block) < 2*(nb_words +1) && header_unpack_block_size(block) >= nb_words + 1));

        if(block == NULL) {
            //free_list_print_stats(free_list);

            //printf("alloc size: %lu\n", nb_words + 1);
            grow_heap(nb_words);
            block = free_list_get_block(free_list, nb_words + 1);
            assert(block == NULL || header_unpack_object_size(block) == nb_words + 1);
            assert(block == NULL || header_unpack_block_size(block) != 2 || (uintptr_t)block % 16 == 0);
            assert(block == NULL || header_unpack_tag(block) == tag_allocated);
            assert(block == NULL || bitmap_get_bit(heap_->bitmap, block));
            memset(block + 1, 0, nb_words * sizeof(word_t));
            //block[1] = 0;
            return block + 1;
            /*printf("No more memory\n");
            exit(1);*/
        }
        /*assert(bitmap_get_bit(free_list->bitmap, block));
        for(int i=1; i < object_size_to_block_size(nb_words + 1); i++) {
            assert(!bitmap_get_bit(free_list->bitmap, block + i));
        }*/
        memset(block + 1, 0, nb_words * sizeof(word_t));
        //block[1] = 0;
        return block + 1;
    }
    /*assert(bitmap_get_bit(free_list->bitmap, block));
    for(int i=1; i < object_size_to_block_size(nb_words + 1); i++) {
        assert(!bitmap_get_bit(free_list->bitmap, block + i));
    }*/
    assert(block + header_unpack_block_size(block) - 1 < free_list->start + free_list->size / sizeof(word_t));
    memset(block+1, 0, nb_words * sizeof(word_t));
    //block[1] = 0;

    return block + 1;
}

void* scalanative_alloc_raw_atomic(size_t size) {
    return scalanative_alloc_raw(size);
}

void* scalanative_alloc(void* info, size_t size) {
    void** alloc = (void**) scalanative_alloc_raw(size);
    *alloc = info;
    return (void*) alloc;
}

void* alloc(size_t size) {
    return scalanative_alloc_raw(size);
}

void scalanative_collect() {
    //free_list_print(free_list);
    //free_list_print_stats(free_list);
    gc_timer_stop(outside_gc);
    gc_timer_start(in_gc);
    #ifdef TIMING_PRINT
        printf("\n\n### START GC ###\n");
        fflush(stdout);

        clock_t start = clock(), diff;
    #endif


    mark_roots(heap_);

    #ifdef TIMING_PRINT
        diff = clock() - start;
        int msec = diff * 1000 / CLOCKS_PER_SEC;
        printf("Time taken %d seconds %d milliseconds\n", msec/1000, msec%1000);

        start = clock();
    #endif

    sweep();

    #ifdef TIMING_PRINT
        diff = clock() - start;
        msec = diff * 1000 / CLOCKS_PER_SEC;
        printf("Time taken %d seconds %d milliseconds\n", msec/1000, msec%1000);
        printf("Heap size: %ld\n", heap_->nb_words * sizeof(word_t));
        printf("### END GC ###\n");
        //free_list_print(free_list);
        fflush(stdout);
    #endif

    gc_timer_stop(in_gc);
    int free_percent = free_list->free * 100 * sizeof(word_t) / free_list->size;
    printf("free: %d\n", free_percent);
    printf("ingc: %ld outgc: %ld\n", in_gc->time, outside_gc->time);
    fflush(stdout);
    if(/*free_percent < 25 ||*/ (in_gc->time > outside_gc->time && in_gc->nb_intervals > 10)) {
        //grow_heap(0);
        gc_timer_reset(in_gc);
        gc_timer_reset(outside_gc);
    }
    gc_timer_start(outside_gc);
    //free_list_print_stats(free_list);
    //free_list_print(free_list);
    //head_printer_print(heap_);

}
