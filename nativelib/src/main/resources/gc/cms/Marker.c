#include <stdio.h>
#include <libunwind.h>
#include <setjmp.h>
#include "Marker.h"
#include "Block.h"
#include "Log.h"
#include "Time.h"

extern int __object_array_id;
extern word_t *__modules;
extern int __modules_size;

bool overflow = false;
word_t *currentOverflowAddress = NULL;

void Marker_largeHeapOverflowHeapScan(Heap *heap, Stack *stack);
bool Marker_smallHeapOverflowHeapScan(Heap *heap, Stack *stack);

// Marks an object and adds it to the stack
void Marker_markStackObject(Stack *stack, Object *object) {
    assert(!Object_isMarked(object));
    assert(Object_isAllocated(object));

    Object_markObject(object);
    if (!overflow) {
        overflow = Stack_push(stack, object);
    }
}

// Marks an address, tries to find an object given an address, marks it if it's
// not marked yet
void Marker_markAddress(Heap *heap, Stack *stack, word_t *address) {
    assert(Heap_isWordInHeap(heap, address));
    Object *object = NULL;
    if (Heap_isWordInSmallHeap(heap, address)) {
        object = Object_getObjectConservative(heap, address);
    } else {
        object = Object_getLargeObjectConservative(heap, address);
    }

    if (object != NULL && !Object_isMarked(object)) {
        Marker_markStackObject(stack, object);
    }
}

// Marks all objects in the stack
void Marker_markStack(Heap *heap, Stack *stack) {
    while (!Stack_isEmpty(stack)) {
        Object *object = Stack_pop(stack);
        assert(Object_isLarge(object) ||
               (Block_getBlockHeader((word_t *)object)->objectSize >=
                Object_getSize(object)));
        if (object->rtti->rt.id == __object_array_id) {
            // remove header and rtti from size
            uint32_t nbWords = Object_getSize(object) - 2;
            for (int i = 0; i < nbWords; i++) {
                word_t *field = object->fields[i];
                Object *fieldObject = (Object *)(field - 1);
                if (Heap_isObjectInHeap(heap, fieldObject) &&
                    !Object_isMarked(fieldObject)) {
                    Marker_markStackObject(stack, fieldObject);
                }
            }
        } else {
            int64_t *ptr_map = object->rtti->refMapStruct;
            int i = 0;
            while (ptr_map[i] != -1) {
                word_t *field = object->fields[ptr_map[i] / sizeof(word_t) - 1];
                Object *fieldObject = (Object *)(field - 1);
                if (Heap_isObjectInHeap(heap, fieldObject) &&
                    !Object_isMarked(fieldObject)) {
                    Marker_markStackObject(stack, fieldObject);
                }
                ++i;
            }
        }
    }
    if (overflow) {
        currentOverflowAddress = heap->heapStart;
        overflow = false;
        Stack_doubleSize(stack);

#ifdef PRINT_STACK_OVERFLOW
        printf("Stack grew to %zu bytes\n",
               stack->nbWords * sizeof(Stack_Type));
#endif

        word_t *largeHeapEnd = heap->largeHeapEnd;
        while (currentOverflowAddress != largeHeapEnd) {
            if (Heap_isWordInSmallHeap(heap, currentOverflowAddress)) {
                if (!Marker_smallHeapOverflowHeapScan(heap, stack)) {
                    currentOverflowAddress = heap->largeHeapStart;
                }
            } else {
                Marker_largeHeapOverflowHeapScan(heap, stack);
            }
            Marker_markStack(heap, stack);
        }
    }
}

void Marker_markStackRoots(Heap *heap, Stack *stack) {
#ifdef DEBUG_PRINT
    long long start = nano_time();
#endif
    unw_cursor_t cursor;
    unw_context_t context;
    unw_getcontext(&context);
    unw_init_local(&cursor, &context);
    unw_word_t top = LONG_MAX, bottom = 0;
    unw_word_t rsp;
    while (unw_step(&cursor) > 0) {
        unw_get_reg(&cursor, UNW_X86_64_RSP, &rsp);

        if (rsp < top) {
            top = rsp;
        }
        if (rsp > bottom) {
            bottom = rsp;
        }
    }
    unw_word_t p = top;
    assert(p % 8 == 0);
    while (p < bottom) {

        word_t *pp = (*(word_t **)p) - 1;
        if (Heap_isWordInHeap(heap, pp)) {
            Marker_markAddress(heap, stack, pp);
        }
        p += 8;
    }
#ifdef DEBUG_PRINT
    long long end = nano_time();
    printf("Marker_markStackRoots: %lld ns\n", end - start);
#endif
}

void Marker_markModuleRoots(Heap *heap, Stack *stack) {
    word_t **module = &__modules;
    int nb_modules = __modules_size;

    for (int i = 0; i < nb_modules; i++) {
        word_t *addr = module[i] - 1;
        if (Heap_isWordInHeap(heap, addr)) {
            Marker_markAddress(heap, stack, addr);
        }
    }
}

void Marker_markRoots(Heap *heap, Stack *stack) {
    assert(Stack_isEmpty(stack));
    // Dumps registers into 'regs' which is on stack
    jmp_buf regs;
    setjmp(regs);

    Marker_markStackRoots(heap, stack);
    Marker_markModuleRoots(heap, stack);
    Marker_markStack(heap, stack);
}

bool Marker_smallHeapOverflowHeapScan(Heap *heap, Stack *stack) {
    assert(Heap_isWordInSmallHeap(heap, currentOverflowAddress));
    BlockHeader *currentBlock = Block_getBlockHeader(currentOverflowAddress);
    word_t *heapEnd = heap->heapEnd;

    while ((word_t *)currentBlock != heapEnd) {
        if (Block_overflowHeapScan(currentBlock, heap, stack,
                                   &currentOverflowAddress)) {
            return true;
        }
        currentBlock = (BlockHeader *)((word_t *)currentBlock + BLOCK_SIZE);
        currentOverflowAddress = (word_t *)currentBlock;
    }
    return false;
}

// Scans through the large heap to find marked blocks with unmarked children.
// Updates `currentOverflowAddress` while doing so.
void Marker_largeHeapOverflowHeapScan(Heap *heap, Stack *stack) {
    assert(Heap_isWordInLargeHeap(heap, currentOverflowAddress));
    void *heapEnd = heap->largeHeapEnd;

    while (currentOverflowAddress != heapEnd) {
        Object *object = (Object *)currentOverflowAddress;
        if (Object_isAllocated(object) && Object_isMarked(object)) {
            if (object->rtti->rt.id == __object_array_id) {
                uint32_t nbWords = Object_getSize(object) - 2;
                for (int i = 0; i < nbWords; i++) {
                    word_t *field = object->fields[i];
                    Object *fieldObject = (Object *)(field - 1);
                    if (Heap_isObjectInHeap(heap, fieldObject) &&
                        !Object_isMarked(fieldObject)) {
                        Stack_push(stack, object);
                        return;
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
                        return;
                    }
                    ++i;
                }
            }
        }
        currentOverflowAddress = (word_t *)Object_nextLargeObject(object);
    }
}
