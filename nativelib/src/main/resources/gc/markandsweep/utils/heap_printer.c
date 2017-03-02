#include "heap_printer.h"

void head_printer_print(Heap* heap) {
    size_t size = heap->nb_words;
    cell_type_t* cell_types = calloc(size, sizeof(cell_type_t));

    int i = 0;
    while (i < size) {
        if(bitmap_get_bit(heap->bitmap, heap->heap_start + i)) {
            cell_type_t tag;
            if(header_unpack_tag(heap->heap_start + i) == tag_allocated) {
                cell_types[i] = allocated_header;
                tag = allocated_cell;
            } else if(header_unpack_tag(heap->heap_start + i) == tag_free) {
                cell_types[i] = free_header;
                tag = free_cell;
            } else {
                cell_types[i] = unknown_header;
            }

            size_t block_size = header_unpack_block_size(heap->heap_start + i);
            for(int j = 1; j < block_size; j++) {
                cell_types[i+j] = tag;
                assert(!bitmap_get_bit(heap->bitmap, heap->heap_start + i + j));
            }

            i += block_size;
        } else {
            cell_types[i] = unknown_cell;
            i++;
        }
    }

    for(int i=0; i < size; i++) {
        if(cell_types[i] == allocated_cell) {
            printf(ANSI_COLOR_BLUE "o" ANSI_COLOR_RESET);
        } else if(cell_types[i] == free_cell) {
            printf(ANSI_COLOR_GREEN "o" ANSI_COLOR_RESET);
        } else if(cell_types[i] == free_header) {
            printf(ANSI_COLOR_RED "o" ANSI_COLOR_RESET);
        } else if(cell_types[i] == allocated_header) {
            printf(ANSI_COLOR_RED "o" ANSI_COLOR_RESET);
        } else if (cell_types[i] == unknown_header) {
            printf(ANSI_COLOR_CYAN "o" ANSI_COLOR_RESET);
        } else if(cell_types[i] == unknown_cell) {
            printf(ANSI_COLOR_MAGENTA "o" ANSI_COLOR_RESET);
        } else {
            printf("o");
        }
        if((i+1) % 512 == 0) {
            printf("\n");
        }
    }
    printf("\n");
    fflush(stdout);
    free(cell_types);
}