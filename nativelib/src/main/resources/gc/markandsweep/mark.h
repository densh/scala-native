//
// Created by Lukas Kellenberger on 01.03.17.
//

#ifndef MARK_H
#define MARK_H

#include <stdio.h>
#include <limits.h>
#include <setjmp.h>
#include <libunwind.h>

#include "block.h"
#include "types.h"
#include "bitmap.h"
#include "free_list.h"
#include "stack.h"
#include "heap.h"

#define UNW_LOCAL_ONLY


extern word_t* __modules;
extern int __modules_size;
extern int __object_array_id;

void mark_roots();
void mark_init(FreeList* free_list);

#endif // MARK_H