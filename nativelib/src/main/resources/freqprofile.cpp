#include <stdio.h>
#include <map>

using namespace std;

map<int64_t, int64_t> freqcounters;

bool freqprofile_contains(int64_t siteId) {
    return freqcounters.find(siteId) != freqcounters.end();
}

void freqprofile_increment(int64_t siteId) {
    if (!freqprofile_contains(siteId)) {
        freqcounters[siteId] = 0L;
    }

    freqcounters[siteId] += 1L;
}

void freqprofile_print(FILE* out) {
    typedef map<int64_t, int64_t>::iterator it;

    for (it m = freqcounters.begin(); m != freqcounters.end(); ++m) {
        fprintf(out, "%ld=%ld\n", m->first, m->second);
    }
}

extern "C" {
    void freqprofile_log(int64_t siteId) {
        freqprofile_increment(siteId);
    }

    void freqprofile_dump(char* file_name) {
        if (!freqcounters.empty()) {
            FILE* file = fopen(file_name, "w");
            if (file == NULL) {
                fprintf(stderr, "Couldn't open file %s for writing.\n", file_name);
                exit(1);
            } else {
                fprintf(stdout, "Dumping frequency profile to %s.\n", file_name);
                freqprofile_print(file);
                fclose(file);
            }
        }
    }
}
