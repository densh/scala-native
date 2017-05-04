#ifndef MARKANDSWEEP_OBJECTHEADER_H
#define MARKANDSWEEP_OBJECTHEADER_H

#include <stdint.h>
#include <stdbool.h>
#include "../Log.h"
#include "../GCTypes.h"

typedef enum {
    object_large = 0x1,
    object_standard = 0x2,
} ObjectType_t;

typedef enum {
    object_free = 0x0,
    object_allocated = 0x1,
} ObjectTag_t;

typedef struct {
    uint32_t size;
    uint8_t tag;
    uint8_t type;
    uint8_t marked;
} ObjectHeader;

typedef struct {
    struct {
        int32_t id;
        word_t* name;
        int8_t kind;
    } rt;
    int64_t size;
    struct {
        int32_t from;
        int32_t to;
    } range;
    struct {
        int32_t dyn_method_count;
        word_t* dyn_method_salt;
        word_t* dyn_method_keys;
        word_t* dyn_methods;
    } dynDispatchTable;
    int64_t* refMapStruct;
} Rtti;

typedef struct Object Object;

struct Object {
    ObjectHeader header;
    union {
        Rtti *rtti;
        Object* next;
    };
    word_t* fields[];
};

static inline void object_mark(Object* object) {
    object->header.marked = 0x1;
}

static inline void object_unmark(Object* object) {
    object->header.marked = 0x0;
}

static inline bool object_isMarked(Object* object) {
    return object->header.marked == 0x1;
}

static inline void object_setType(Object* object, ObjectType_t type) {
    object->header.type = type;
}

static inline bool object_isLarge(Object* object) {
    return object->header.type == object_large;
}

static inline bool object_isStandard(Object* object) {
    return object->header.type == object_standard;
}

static inline void object_setTag(Object* object, ObjectTag_t objectTag) {
    object->header.tag = objectTag;
}

static inline bool object_isFree(Object* object) {
    return object->header.tag == object_free;
}
static inline bool object_isAllocated(Object* object) {
    return object->header.tag == object_allocated;
}

static inline uint32_t object_getSize(Object* object) {
    return object->header.size;
}

static inline void object_setSize(Object* object, uint32_t size) {
    object->header.size = size;
}

#endif //MARKANDSWEEP_OBJECTHEADER_H
