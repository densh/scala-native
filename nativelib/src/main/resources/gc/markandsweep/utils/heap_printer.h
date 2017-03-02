#ifndef HEAP_PRINTER_H
#define HEAP_PRINTER_H

#include <stdio.h>
#include "../block.h"
#include "../bitmap.h"
#include "../heap.h"

#define ANSI_COLOR_RED     "\x1b[31m"
#define ANSI_COLOR_GREEN   "\x1b[32m"
#define ANSI_COLOR_YELLOW  "\x1b[33m"
#define ANSI_COLOR_BLUE    "\x1b[34m"
#define ANSI_COLOR_MAGENTA "\x1b[35m"
#define ANSI_COLOR_CYAN    "\x1b[36m"
#define ANSI_COLOR_RESET   "\x1b[0m"

typedef enum {
    allocated_cell = 1,
    free_cell = 2,
    allocated_header = 3,
    free_header = 4,
    unknown_header = 5,
    unknown_cell = 6
} cell_type_t;

void head_printer_print(Heap* heap);


#endif //HEAP_PRINTER_H