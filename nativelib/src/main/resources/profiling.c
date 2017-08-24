#include <errno.h>
#include <limits.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <zlib.h>
#include <sys/stat.h>

int64_t scalanative_cycleclock();

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
    }
    dst[length] = '\0';

    return dst;
}

/** The current batch number. */
int current_batch = 1;

int64_t last;

int64_t count = 0;

/** The buffer where events are written */
unsigned char* buffer;

/** Current position in the buffer */
unsigned char* buffer_cursor;

/** The directory where dumps will be written */
char* dump_directory;

/** Sets `dst` to the path to the next profiling file output */
void next_file(char* dst) {
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
void block_dump() {
    char filename[PATH_MAX] = { 0 };
    unsigned char compressed_buffer[CHUNK_SIZE];
    unsigned long compressed_size = CHUNK_SIZE;

    next_file(filename);
    FILE* out = fopen(filename, "wb");

    if (out == NULL) {
        printf("Couldn't open '%s'. Errno %d", filename, errno);
        perror(filename);
        exit(1);
    }

    compress(compressed_buffer, &compressed_size, buffer, buffer_cursor - buffer);
    fwrite(compressed_buffer, compressed_size, 1, out);
    buffer_cursor = buffer;
    fclose(out);
    printf("Recorded %lld events\n", count);
}

/** Init the profiling data structures. */
void profiling_init(jstring* target_directory) {
    char* target = calloc(target_directory->count + 1, sizeof(char));
    buffer = calloc(CHUNK_SIZE, sizeof(unsigned char));
    to_string(target, target_directory->count, target_directory);
    dump_directory = target;
    mkdir(dump_directory, 0755);
    buffer_cursor = buffer;
    last = scalanative_cycleclock();
}

int timedelta() {
    int64_t now = scalanative_cycleclock();
    int delta = (int) (now - last);
    last = now;
    return delta;
}

void log_block(int id) {
    count += 1;
    int *current = (int *) buffer_cursor;
    *current = id;
    *(current + 1) = timedelta();
    buffer_cursor += 8;
    if (buffer_cursor == buffer + CHUNK_SIZE) {
        block_dump();
    }
}
