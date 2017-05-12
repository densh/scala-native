#include <stddef.h>
#include <memory.h>
#include "Block.h"
#include "Log.h"

extern int __object_array_id;

// Returns the next block of the linkedlist of `FreeBlockHeader`,
// `NULL` if `freeBlock` is the last block.
FreeBlockHeader *Block_getNextFreeBlock(FreeBlockHeader *freeBlock) {
    FreeBlockHeader *next = freeBlock->next;

    // NULL means next block
    if (next == NULL) {
        next = (FreeBlockHeader *)((word_t *)freeBlock + BLOCK_SIZE);
    } else if (next == LAST_BLOCK_PTR) {
        next = NULL;
    }

    return next;
}

// Adds a block to the end of the linkedlist of free blocks
void Block_freeBlockAddLast(Allocator *allocator,
                            FreeBlockHeader *freeBlockHeader) {
    freeBlockHeader->next = LAST_BLOCK_PTR;
    if (allocator->lastFreeBlock == NULL) {
        allocator->lastFreeBlock = freeBlockHeader;
        allocator->freeBlocks = freeBlockHeader;
    } else {
        allocator->lastFreeBlock->next = freeBlockHeader;
        allocator->lastFreeBlock = freeBlockHeader;
    }
}

// Sweeps through a block. If the full block is not marked, free it completely.
// Otherwise go through the block object by object. Add the marked objects to
// the linkedlist corresponding to their size
void Block_sweep(Allocator *allocator, BlockHeader *block) {
    if (!Block_isMarked(block)) {
        // Block is not marked it is all free
        memset(Block_getFirstWord(block), 0, (BLOCK_SIZE - 1) * WORD_SIZE);
        Block_freeBlockAddLast(allocator, (FreeBlockHeader *)block);
    } else {
        Block_unmark(block);
        uint32_t size = Block_getObjectSize(block);
        word_t *blockEnd = Block_getBlockEnd((word_t *)block);
        word_t *current = Block_getFirstWord(block);
        while (current + size <= blockEnd) {
            Object *object = (Object *)current;
            assert(Object_isFree(object) || Object_getSize(object) <= size);
            if (Object_isMarked(object)) {
                Object_unmark(object);
            } else {
                memset(object, 0, size);
                Object_setTag(object, Object_free);
                Object_setSize(object, size);
                FreeList_addLast(&allocator->freeLists[sizeToIndex(size)],
                                 object);
            }
            current += size;
        }
    }
}

// This method is used in case of overflow during the marking phase.
// It sweeps through the block starting at `currentOverflowAddress` until if
// finds a marked block with unmarked children. It updates the value of
// `currentOverflowAddress` while sweeping through the block Once a block is
// found it adds it to the stack and returns `true`. If no block is found it
// returns `false`.
bool Block_overflowHeapScan(BlockHeader *block, Heap *heap, Stack *stack,
                            word_t **currentOverflowAddress) {
    uint32_t size = Block_getObjectSize(block);
    word_t *blockEnd = Block_getBlockEnd((word_t *)block);

    if (*currentOverflowAddress == (word_t *)block) {
        *currentOverflowAddress = Block_getFirstWord(block);
    }

    while (*currentOverflowAddress + size <= blockEnd) {
        Object *object = (Object *)*currentOverflowAddress;
        if (Object_isAllocated(object) && Object_isMarked(object)) {
            if (object->rtti->rt.id == __object_array_id) {
                uint32_t nbWords = Object_getSize(object) - 2;
                for (int i = 0; i < nbWords; i++) {
                    word_t *field = object->fields[i];
                    Object *fieldObject = (Object *)(field - 1);
                    if (Heap_isObjectInHeap(heap, fieldObject) &&
                        !Object_isMarked(fieldObject)) {
                        Stack_push(stack, object);
                        return true;
                    }
                }
            } else {
                int64_t *ptr_map = object->rtti->refMapStruct;
                int i = 0;
                while (ptr_map[i] != -1) {
                    word_t *field =
                        object->fields[ptr_map[i] / sizeof(word_t) - 1];
                    Object *fieldObject = (Object *)(field - 1);
                    if (Heap_isObjectInHeap(heap, fieldObject) &&
                        !Object_isMarked(fieldObject)) {
                        Stack_push(stack, object);
                        return true;
                    }
                    ++i;
                }
            }
        }

        *currentOverflowAddress += size;
    }

    return false;
}
