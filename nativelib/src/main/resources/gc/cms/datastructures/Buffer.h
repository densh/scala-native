#ifndef CMS_BUFFER_H
#define CMS_BUFFER_H

#include <string.h>
#include <sys/mman.h>

#define BUFFER_MAX_SIZE 34359738368 // 2^32 * 2^8
#define BUFFER_MEM_PROT (PROT_READ | PROT_WRITE)
#define BUFFER_MEM_FLAGS (MAP_NORESERVE | MAP_PRIVATE | MAP_ANONYMOUS)
#define BUFFER_MEM_FD -1
#define BUFFER_MEM_FD_OFFSET 0

typedef struct {
    uint32_t current;
    void **data;
} Buffer;

inline Buffer *Buffer_create() {
    Buffer *buffer = malloc(sizeof(Buffer));
    buffer->current = 0;
    buffer->data = mmap(NULL, BUFFER_MAX_SIZE, BUFFER_MEM_PROT,
                        BUFFER_MEM_FLAGS, BUFFER_MEM_FD, BUFFER_MEM_FD_OFFSET);
    return buffer;
}

inline uint32_t Buffer_append(Buffer *buffer, void *value) {
    buffer->data[buffer->current] = value;
    return buffer->current++;
}

inline uint32_t Buffer_appendv(Buffer *buffer, void **appendv,
                               int32_t appendc) {
    memcpy(&buffer->data[buffer->current], appendv, appendc);
    return buffer->current += appendc;
}

#endif // CMS_BUFFER_H
