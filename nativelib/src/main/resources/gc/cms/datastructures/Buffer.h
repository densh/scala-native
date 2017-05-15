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
    void **start;
    void **current;
} Buffer;

inline Buffer *Buffer_create() {
    Buffer *buffer = malloc(sizeof(Buffer));
    buffer->current = 0;
    buffer->start = mmap(NULL, BUFFER_MAX_SIZE, BUFFER_MEM_PROT,
                         BUFFER_MEM_FLAGS, BUFFER_MEM_FD, BUFFER_MEM_FD_OFFSET);
    return buffer;
}

inline void **Buffer_start(Buffer *buffer) { return buffer->start; }

inline void **Buffer_current(Buffer *buffer) { return buffer->current; }

inline void Buffer_commit(Buffer *buffer, void **current) {
    buffer->current = current;
}

inline void Buffer_append(Buffer *buffer, void *value) {
    buffer->current = value;
    buffer->current++;
}

inline void Buffer_reset(Buffer *buffer) { buffer->current = buffer->start; }

#endif // CMS_BUFFER_H
