#ifndef CMS_OBJECTHEADER_H
#define CMS_OBJECTHEADER_H

#include <stdint.h>
#include <stdbool.h>
#include "../Log.h"
#include "../Types.h"

typedef enum {
    Object_standard = 0x0,
    Object_large = 0x1,
} ObjectType_t;

typedef enum {
    Object_free = 0x0,
    Object_allocated = 0x1,
} ObjectTag_t;

typedef struct {
    uint8_t marked : 1;
    uint8_t tag : 1;
    uint8_t type : 1;
    uint32_t size : 29;
    uint32_t replica;
} ObjectHeader;

typedef struct {
    struct {
        int32_t id;
        word_t *name;
        int8_t kind;
    } rt;
    int64_t size;
    struct {
        int32_t from;
        int32_t to;
    } range;
    struct {
        int32_t dyn_method_count;
        word_t *dyn_method_salt;
        word_t *dyn_method_keys;
        word_t *dyn_methods;
    } dynDispatchTable;
    int64_t *refMapStruct;
} Rtti;

typedef struct Object Object;

struct Object {
    ObjectHeader header;
    union {
        Rtti *rtti;
        Object *next;
    };
    word_t *fields[];
};

static inline void Object_mark(Object *object) { object->header.marked = 0x1; }

static inline void Object_unmark(Object *object) {
    object->header.marked = 0x0;
}

static inline bool Object_isMarked(Object *object) {
    return object->header.marked == 0x1;
}

static inline void Object_setStandard(Object *object) {
    object->header.type = Object_standard;
}

static inline void Object_setLarge(Object *object) {
    object->header.type = Object_large;
}

static inline bool Object_isLarge(Object *object) {
    return object->header.type == Object_large;
}

static inline bool Object_isStandard(Object *object) {
    return object->header.type == Object_standard;
}

static inline void Object_setFree(Object *object) {
    object->header.tag = Object_free;
}

static inline void Object_setAllocated(Object *object) {
    object->header.tag = Object_allocated;
}

static inline bool Object_isFree(Object *object) {
    return object->header.tag == Object_free;
}
static inline bool Object_isAllocated(Object *object) {
    return object->header.tag == Object_allocated;
}

static inline uint32_t Object_getSize(Object *object) {
    return object->header.size;
}

static inline void Object_setSize(Object *object, uint32_t size) {
    object->header.size = size;
}

#endif // CMS_OBJECTHEADER_H
