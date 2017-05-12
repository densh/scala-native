#ifndef CMS_BLOCK_H
#define CMS_BLOCK_H

#include "headers/BlockHeader.h"
#include "Allocator.h"
#include "Heap.h"
#include "datastructures/Stack.h"

#define LAST_BLOCK_PTR ((FreeBlockHeader *)-1)

FreeBlockHeader *Block_getNextFreeBlock(FreeBlockHeader *freeBlock);
void Block_sweep(Allocator *allocator, BlockHeader *block);
bool Block_overflowHeapScan(BlockHeader *block, Heap *heap, Stack *stack,
                            word_t **currentOverflowAddress);

#endif // CMS_BLOCK_H
