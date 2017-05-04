#include <stdio.h>
#include <libunwind.h>
#include <setjmp.h>
#include "Marker.h"
#include "Block.h"
#include "Log.h"


extern int __object_array_id;
extern word_t* __modules;
extern int __modules_size;

bool overflow = false;
word_t* currentOverflowAddress = NULL;

void largeHeapOverflowHeapScan(Heap* heap, Stack* stack);
bool smallHeapOverflowHeapScan(Heap* heap, Stack* stack);


// Marks an object and adds it to the stack
void markObject(Stack* stack, Object* object) {
    assert(!object_isMarked(object));
    assert(object_isAllocated(object));

    object_markObject(object);
    if(!overflow) {
        overflow = stack_push(stack, object);
    }
}

// Marks an address, tries to find an object given an address, marks it if it's not marked yet
void mark(Heap* heap, Stack* stack, word_t* address) {
    assert(heap_isWordInHeap(heap, address));
    Object* object = NULL;
    if(heap_isWordInSmallHeap(heap, address)) {
        object = object_getObjectConservative(heap, address);
    } else {
        object = object_getLargeObjectConservative(heap, address);
    }


    if(object != NULL && !object_isMarked(object)) {
        markObject(stack, object);
    }
}

// Marks all objects in the stack
void marker_mark(Heap* heap, Stack* stack) {
    while(!stack_isEmpty(stack)) {
        Object* object = stack_pop(stack);
        assert(object_isLarge(object) || (block_getBlockHeader((word_t*)object)->objectSize >= object_getSize(object)));
        if(object->rtti->rt.id == __object_array_id) {
            // remove header and rtti from size
            uint32_t nbWords = object_getSize(object) - 2;
            for(int i = 0; i < nbWords; i++) {
                word_t* field = object->fields[i];
                Object* fieldObject = (Object*)(field - 1);
                if(heap_isObjectInHeap(heap, fieldObject) && !object_isMarked(fieldObject)) {
                    markObject(stack, fieldObject);
                }
            }
        } else {
            int64_t* ptr_map = object->rtti->refMapStruct;
            int i=0;
            while(ptr_map[i] != -1) {
                word_t* field = object->fields[ptr_map[i]/sizeof(word_t) - 1];
                Object* fieldObject = (Object*)(field - 1);
                if(heap_isObjectInHeap(heap, fieldObject) && !object_isMarked(fieldObject)) {
                    markObject(stack, fieldObject);
                }
                ++i;
            }
        }
    }
    if(overflow) {
        currentOverflowAddress = heap->heapStart;
        overflow = false;
        stack_doubleSize(stack);

#ifdef PRINT_STACK_OVERFLOW
        printf("Stack grew to %zu bytes\n", stack->nbWords * sizeof(Stack_Type));
#endif

        word_t* largeHeapEnd = heap->largeHeapEnd;
        while(currentOverflowAddress != largeHeapEnd) {
            if(heap_isWordInSmallHeap(heap, currentOverflowAddress)) {
                if(!smallHeapOverflowHeapScan(heap, stack)) {
                    currentOverflowAddress = heap->largeHeapStart;
                }
            } else {
                largeHeapOverflowHeapScan(heap, stack);
            }
            marker_mark(heap, stack);
        }
    }
}

void mark_roots_stack(Heap* heap, Stack* stack) {
    unw_cursor_t cursor;
    unw_context_t context;
    unw_getcontext(&context);
    unw_init_local(&cursor, &context);
    unw_word_t top = LONG_MAX, bottom = 0;
    unw_word_t rsp;
    while (unw_step(&cursor) > 0) {
        unw_get_reg(&cursor, UNW_X86_64_RSP, &rsp);

        if(rsp < top) {
            top = rsp;
        }
        if(rsp > bottom) {
            bottom = rsp;
        }

    }
    unw_word_t p = top;
    assert(p % 8 == 0);
    while(p < bottom) {

        word_t* pp = (*(word_t**)p) - 1;
        if(heap_isWordInHeap(heap, pp)) {
            mark(heap, stack, pp);
        }
        p += 8;
    }
}

void mark_roots_modules(Heap* heap, Stack* stack) {
    word_t** module = &__modules;
    int nb_modules = __modules_size;


    for(int i=0; i < nb_modules; i++) {
        word_t* addr = module[i] - 1;
        if(heap_isWordInHeap(heap, addr)) {
            mark(heap, stack, addr);
        }
    }
}

void mark_roots(Heap* heap, Stack* stack) {
    assert(stack_isEmpty(stack));
    // Dumps registers into 'regs' which is on stack
    jmp_buf regs;
    setjmp(regs);

    mark_roots_stack(heap, stack);

    mark_roots_modules(heap, stack);

    marker_mark(heap, stack);
}

bool smallHeapOverflowHeapScan(Heap* heap, Stack* stack) {
    assert(heap_isWordInSmallHeap(heap, currentOverflowAddress));
    BlockHeader* currentBlock = block_getBlockHeader(currentOverflowAddress);
    word_t* heapEnd = heap->heapEnd;

    while((word_t*)currentBlock != heapEnd) {
        if(block_overflowHeapScan(currentBlock, heap, stack, &currentOverflowAddress)) {
            return true;
        }
        currentBlock = (BlockHeader*)((word_t*)currentBlock + BLOCK_SIZE);
        currentOverflowAddress = (word_t*)currentBlock;
    }
    return false;
}

// Scans through the large heap to find marked blocks with unmarked children.
// Updates `currentOverflowAddress` while doing so.
void largeHeapOverflowHeapScan(Heap* heap, Stack* stack) {
    assert(heap_isWordInLargeHeap(heap, currentOverflowAddress));
    void* heapEnd = heap->largeHeapEnd;

    while(currentOverflowAddress != heapEnd) {
        Object* object = (Object*) currentOverflowAddress;
        if(object_isAllocated(object) && object_isMarked(object)) {
            if(object->rtti->rt.id == __object_array_id) {
                uint32_t nbWords = object_getSize(object) - 2;
                for(int i = 0; i < nbWords; i++) {
                    word_t* field = object->fields[i];
                    Object* fieldObject = (Object*)(field - 1);
                    if(heap_isObjectInHeap(heap, fieldObject) && !object_isMarked(fieldObject)) {
                        stack_push(stack, object);
                        return;
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
                        return;
                    }
                    ++i;
                }
            }
        }
        currentOverflowAddress = (word_t*) object_nextLargeObject(object);
    }
}