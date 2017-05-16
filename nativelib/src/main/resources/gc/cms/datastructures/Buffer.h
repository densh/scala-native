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
    word_t *start;
    word_t *current;
} Buffer;

inline Buffer *Buffer_create() {
    Buffer *buffer = malloc(sizeof(Buffer));
    buffer->start = mmap(NULL, BUFFER_MAX_SIZE, BUFFER_MEM_PROT,
                         BUFFER_MEM_FLAGS, BUFFER_MEM_FD, BUFFER_MEM_FD_OFFSET);
    buffer->current = buffer->start;
    return buffer;
}

inline word_t *Buffer_start(Buffer *buffer) {
    return buffer->start;
}

inline word_t *Buffer_current(Buffer *buffer) {
    return buffer->current;
}

inline uint32_t Buffer_size(Buffer *buffer) {
    return buffer->current - buffer->start;
}

inline void Buffer_append(Buffer *buffer, word_t value) {
    word_t *current = buffer->current;
    *current = value;
    current += 1;
    buffer->current = current;
}

inline void Buffer_concat(Buffer *to, Buffer *from) {
    word_t *current = to->current;
    size_t size = Buffer_size(from);
    memcpy(current, from->start, size * sizeof(word_t));
    to->current = current + size;
}

inline void Buffer_reset(Buffer *buffer) { buffer->current = buffer->start; }

#endif // CMS_BUFFER_H
