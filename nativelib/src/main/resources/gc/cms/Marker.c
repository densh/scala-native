#include <stdio.h>
#include <libunwind.h>
#include <setjmp.h>
#include "Marker.h"
#include "Block.h"
#include "Log.h"
#include "Time.h"

extern word_t *__modules;
extern int __modules_size;

bool overflow = false;

// Marks an object and adds it to the stack
void Marker_markStackObject(Stack *stack, Object *object) {
    assert(!Object_isMarked(object));
    assert(Object_isAllocated(object));

    Object_markObject(object);
    if (!overflow) {
        overflow = Stack_push(stack, object);
    }
}

void Marker_replicate(Heap *heap, Buffer *buffer, Object *object) {
    assert(Heap_isObjectInHeap(heap, object));
    if (object->rtti->rt.id == __object_array_id) {
        printf("Replicating array\n");
        // remove header and rtti from size
        uint32_t nbWords = Object_getSize(object) - 2;
        for (int i = 0; i < nbWords; i++) {
            word_t *field = object->fields[i];
            Object *fieldObject = (Object *)(field - 2);
            if (Heap_isObjectInHeap(heap, fieldObject)) {
                Buffer_append(buffer, (word_t) fieldObject);
            }
        }
    } else {
        printf("Replicating normal object\n");
        int64_t *ptr_map = object->rtti->refMapStruct;
        int i = 0;
        while (ptr_map[i] != -1) {
            word_t *field = object->fields[ptr_map[i] / sizeof(word_t) - 1];
            Object *fieldObject = (Object *)(field - 2);
            if (Heap_isObjectInHeap(heap, fieldObject)) {
                Buffer_append(buffer, (word_t) fieldObject);
            }
            ++i;
        }
    }
}

void Marker_markReplica(Heap *heap, Stack *stack, Object **replica) {
    printf("Marking replica\n");
    assert(replica != NULL);
    while (true) {
        Object *object = *replica;
        if (object != NULL) {
            assert(Heap_isObjectInHeap(heap, object));
            if (!Object_isMarked(object)) {
                Marker_markStackObject(stack, object);
            }
            replica += 1;
        } else {
            break;
        }
    }
}

// Marks all objects in the stack
void Marker_markStack(Heap *heap, Stack *stack, Buffer *buffer, Buffer *temp) {
    Object **tempStart = (Object **) Buffer_start(temp);
    while (!Stack_isEmpty(stack)) {
        Object *object = Stack_pop(stack);
        assert(Object_isLarge(object) ||
               (Block_getBlockHeader((word_t *)object)->objectSize >=
                Object_getSize(object)));
        uint32_t offset = Object_getReplicaOffset(object);
        if (offset == 0) {
            printf("Creating marker replica\n");
            Marker_replicate(heap, temp, object);
            offset = Object_getReplicaOffset(object);
            if (offset == 0) {
                printf("Using marker replica\n");
                Marker_markReplica(heap, stack, tempStart);
                continue;
            }
        }
        printf("Using mutator replica at %d\n", offset);
        Marker_markReplica(heap, stack, (Object **) (Buffer_start(buffer) + offset));
        Buffer_reset(temp);
    }
    if (overflow) {
        printf("Can't handle overflows\n");
        exit(1);
    }
}

void Marker_collectStackRoots(Heap *heap, Buffer *buffer) {
    // Dumps registers into 'regs' which is on stack.
    jmp_buf regs;
    setjmp(regs);

    // Walk the stack to find min/max rsp.
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

    // Walk the memory in min/max rsp range and append stack roots to the buffer.
    unw_word_t p = top;
    assert(p % 8 == 0);
    while (p < bottom) {
        word_t *addr = (*(word_t **)p) - 1;
        if (Heap_isWordInHeap(heap, addr)) {
            if (Heap_isWordInSmallHeap(heap, addr)) {
                addr = (word_t *) Object_getObjectConservative(heap, addr);
            } else {
                addr = (word_t *) Object_getLargeObjectConservative(heap, addr);
            }
            if (addr != NULL) {
                Buffer_append(buffer, (word_t) addr);
            }
        }
        p += 8;
    }
}

void Marker_collectModuleRoots(Heap *heap, Buffer *buffer) {
    word_t **module = &__modules;
    int nb_modules = __modules_size;
    for (int i = 0; i < nb_modules; i++) {
        word_t *addr = module[i] - 1;
        if (Heap_isWordInHeap(heap, addr)) {
            Buffer_append(buffer, (word_t) addr);
        }
    }
}

void Marker_collectRoots(Heap *heap, Buffer *buffer) {
#ifdef DEBUG_PRINT
    long long start = nano_time();
#endif
    Marker_collectStackRoots(heap, buffer);
    Marker_collectModuleRoots(heap, buffer);
#ifdef DEBUG_PRINT
    long long end = nano_time();
    printf("Collected %u roots\n", Buffer_size(buffer));
    printf("Marker_collectRoots: %lld ns\n", end - start);
#endif
}

void Marker_markBuffer(Heap *heap, Stack *stack, Buffer *buffer, Buffer *temp) {
#ifdef DEBUG_PRINT
    long long startTime = nano_time();
#endif
    // Add all buffer pointers to the marking stack
    word_t *end = Buffer_current(buffer);
    word_t *current = Buffer_start(buffer);
    while (current < end) {
        Object *obj = (Object *) *current;
        Marker_markStackObject(stack, obj);
        current += 1;
    }
    // Transitively mark the whole heap
    printf("Marking stack\n");
    Marker_markStack(heap, stack, buffer, temp);
    printf("\n");
#ifdef DEBUG_PRINT
    long long endTime = nano_time();
    printf("Marker_markBuffer: %lld ns\n", endTime - startTime);
#endif
}

