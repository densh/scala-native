//
// Created by Lukas Kellenberger on 29.04.17.
//

#include <stddef.h>
#include <memory.h>
#include <printf.h>
#include "Block.h"
#include "datastructures/Stack.h"
#include "Heap.h"

extern int __object_array_id;

FreeBlockHeader* block_getNextFreeBlock(FreeBlockHeader* freeBlock) {

    FreeBlockHeader* next = freeBlock->next;

    // NULL means next block
    if(next == NULL) {
        next = (FreeBlockHeader*)((word_t*)freeBlock + BLOCK_SIZE);
    } else if(next == LAST_BLOCK_PTR) {
        next = NULL;
    }


    return next;
}

void block_freeBlockAddLast(Allocator* allocator, FreeBlockHeader* freeBlockHeader) {
    freeBlockHeader->next = LAST_BLOCK_PTR;
    if(allocator->lastFreeBlock == NULL) {
        allocator->lastFreeBlock = freeBlockHeader;
        allocator->freeBlocks = freeBlockHeader;
    } else {
        allocator->lastFreeBlock->next = freeBlockHeader;
        allocator->lastFreeBlock = freeBlockHeader;
    }
}

void block_sweep(Allocator* allocator, BlockHeader* block) {
    if(!block_isMarked(block)) {
        // Block is not marked it is all free
        memset(block_getFirstWord(block), 0, (BLOCK_SIZE - 1) * WORD_SIZE);
        block_freeBlockAddLast(allocator, (FreeBlockHeader*) block);
    } else {
        block_unmark(block);
        uint32_t size = block_getObjectSize(block);
        word_t* blockEnd = block_getBlockEnd((word_t*)block);
        word_t* current = block_getFirstWord(block);
        while(current + size <= blockEnd) {
            Object* object = (Object*) current;
            assert(object_isFree(object) || object_getSize(object) <= size);
            if(object_isMarked(object)) {
                object_unmark(object);
            } else {
                memset(object, 0, size);
                object_setTag(object, object_free);
                object_setSize(object, size);
                freeList_addLast(&allocator->freeLists[sizeToIndex(size)], object);
            }
            current += size;
        }
    }
}

bool block_overflowHeapScan(BlockHeader* block, Heap* heap, Stack* stack, word_t** currentOverflowAddress) {
    uint32_t size = block_getObjectSize(block);
    word_t* blockEnd = block_getBlockEnd((word_t*)block);

    if(*currentOverflowAddress == (word_t*)block) {
        *currentOverflowAddress = block_getFirstWord(block);
    }

    while(*currentOverflowAddress + size <= blockEnd) {
        Object* object = (Object*) *currentOverflowAddress;
        if(object_isAllocated(object) && object_isMarked(object)) {
            if(object->rtti->rt.id == __object_array_id) {
                uint32_t nbWords = object_getSize(object) - 2;
                for(int i = 0; i < nbWords; i++) {
                    word_t* field = object->fields[i];
                    Object* fieldObject = (Object*)(field - 1);
                    if(heap_isObjectInHeap(heap, fieldObject) && !object_isMarked(fieldObject)) {
                        stack_push(stack, object);
                        return true;
                    }
                }
            } else {
                int64_t* ptr_map = object->rtti->refMapStruct;
                int i=0;
                while(ptr_map[i] != -1) {
                    word_t* field = object->fields[ptr_map[i]/sizeof(word_t) - 1];
                    Object* fieldObject = (Object*)(field - 1);
                    if(heap_isObjectInHeap(heap, fieldObject) && !object_isMarked(fieldObject)) {
                        stack_push(stack, object);
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