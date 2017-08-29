#include <errno.h>
#include <limits.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <zlib.h>
#include <sys/stat.h>
#include <stddef.h>
#include <inttypes.h>

#define CHUNK_SIZE (1024 * 1024 * 4) // 4 MB

typedef struct chararray
{
    void* u;
    int length;
    int unused;
    short chars[];
} chararray;

typedef struct jstring
{
    void* u;
    int cachedHashCode;
    int count;
    int offset;
    chararray* value;
} jstring;

char* to_string(char* dst, size_t max_len, jstring* str) {
    size_t length = max_len > str->count ? str->count : max_len;

    for (int i = 0; i < length; ++i) {
        dst[i] = (char) str->value->chars[i];
    } dst[length] = '\0';

    return dst;
}

typedef struct {
    void *start;
    void *end;
    void *cursor;
    void *next;
} profiling_chunk;

/** The current batch number. */
int current_batch = 1;

/** First chunk in the list of chunks. */
profiling_chunk *head;

/** Current event log chunk. */
profiling_chunk *chunk;

/** Number of chunks allocated. */
int64_t chunks;

/** The directory where dumps will be written */
char* dump_directory;

void profiling_put(int value) {
    int *current = (int *) chunk->cursor;
    *current = value;
    chunk->cursor += 4;
}

/** Sets `dst` to the path to the next profiling file output */
void profiling_next_file(char* dst) {
    if (0 == strcmp(dump_directory, "/dev/null")) {
       strcpy(dst, dump_directory);
    } else {
        strncat(dst, dump_directory, PATH_MAX);
        strncat(dst, "/profile.", PATH_MAX);
        char* n = calloc(20, sizeof(char));
        snprintf(n, 20, "%d", current_batch);
        strncat(dst, n, PATH_MAX);
        free(n);
        current_batch += 1;
    }
}

/** Dumps all the blocks in `dump_location`. */
void profiling_dump() {
    profiling_chunk *current = head;
    int64_t count = 0;

    printf("Dumping profiling batches\n");

    while (current != NULL) {
        char filename[PATH_MAX] = { 0 };
        unsigned char compressed_buffer[CHUNK_SIZE];
        unsigned long compressed_size = CHUNK_SIZE;

        profiling_next_file(filename);
        FILE* out = fopen(filename, "wb");

        if (out == NULL) {
            printf("Couldn't open '%s'. Errno %d", filename, errno);
            perror(filename);
            exit(1);
        }

        compress(compressed_buffer, &compressed_size, current->start, current->cursor - current->start);
        fwrite(compressed_buffer, compressed_size, 1, out);
        fclose(out);
        count += 1;
        if (count % 100 == 0) {
            printf("Dumped %" PRId64 " batches\n", count);
        }

        current = current->next;
    }

    printf("Finished dumping %" PRId64 " batches\n", count);
}

void profiling_new_chunk() {
    profiling_chunk *next = calloc(1, sizeof(profiling_chunk));
    next->start = calloc(CHUNK_SIZE, sizeof(unsigned char));
    next->end = next->start + CHUNK_SIZE;
    next->cursor = next->start;
    chunk->next = next;
    chunk = next;
    chunks += 1;
    if (chunks % 100 == 0) {
        printf("Allocated %" PRId64 " chunks\n", chunks);
    }
}

/** Init the profiling data structures. */
void profiling_init(jstring* target_directory) {
    char* target = calloc(target_directory->count + 1, sizeof(char));
    head = chunk = calloc(1, sizeof(profiling_chunk));
    chunk->start = calloc(CHUNK_SIZE, sizeof(unsigned char));
    chunk->end = chunk->start + CHUNK_SIZE;
    chunk->next = NULL;
    to_string(target, target_directory->count, target_directory);
    dump_directory = target;
    mkdir(dump_directory, 0755);
    chunk->cursor = chunk->start;
    profiling_put(-1); // main
}

void profiling_log(int id) {
    profiling_put(id);
    if (chunk->cursor == chunk->end) {
        profiling_new_chunk();
    }
}
