//
// Created by Lukas Kellenberger on 29.04.17.
//

#ifndef MARKANDSWEEP_BLOCK_H
#define MARKANDSWEEP_BLOCK_H

#include "headers/BlockHeader.h"
#include "Allocator.h"
#include "Heap.h"
#include "datastructures/Stack.h"

#define LAST_BLOCK_PTR ((FreeBlockHeader*)-1)

FreeBlockHeader* block_getNextFreeBlock(FreeBlockHeader* freeBlock);
void block_sweep(Allocator* allocator, BlockHeader* block);
bool block_overflowHeapScan(BlockHeader* block, Heap* heap, Stack* stack, word_t** currentOverflowAddress);

#endif //MARKANDSWEEP_BLOCK_H
