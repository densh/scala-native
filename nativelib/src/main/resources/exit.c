#include <stdlib.h>

void typeprofile_dump(char* file_name);

void freqprofile_dump(char* file_name);

void scalanative_exit(int code) {
    typeprofile_dump("profile.data");
    freqprofile_dump("profile.data.freq");
    exit(code);
}
