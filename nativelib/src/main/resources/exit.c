#include <stdlib.h>

void scalanative_persistprofile(char* file_name);

void scalanative_exit(int code) {
    scalanative_persistprofile("profile.data");
    exit(code);
}
