//
// Created by Lukas Kellenberger on 01.05.17.
//

#ifndef MARKANDSWEEP_PROGRAMROOTS_H
#define MARKANDSWEEP_PROGRAMROOTS_H

#include <libunwind.h>
#include <stdlib.h>
#include <setjmp.h>
#include "GCTypes.h"

extern word_t* __modules;
extern int __modules_size;

typedef struct {
    word_t* stackTop;
    word_t* stackBottom;
    word_t** moduleStart;
    int moduleCount;
} Roots;


static Roots* programRoots_create() {
    Roots* roots = malloc(sizeof(Roots));


    unw_cursor_t cursor;
    unw_context_t context;
    unw_getcontext(&context);
    unw_init_local(&cursor, &context);
    unw_word_t bottom = 0;
    unw_word_t rsp;

    while (unw_step(&cursor) > 0) {
        unw_get_reg(&cursor, UNW_X86_64_RSP, &rsp);

        if(rsp > bottom) {
            bottom = rsp;
        }
    }

    roots->stackBottom = (word_t*)bottom;
    roots->stackTop = NULL;
    roots->moduleCount = __modules_size;
    roots->moduleStart = &__modules;

    return roots;
}

static void programRoots_getRoots(Roots* roots) {
    assert(roots != NULL);

    jmp_buf regs = {0};
    setjmp(regs);
    int dummy = 0;
    word_t top = (word_t) &dummy;
    // round down
    top = top / WORD_SIZE * WORD_SIZE;

    roots->stackTop = (word_t*) top;
}

#endif //MARKANDSWEEP_PROGRAMROOTS_H
