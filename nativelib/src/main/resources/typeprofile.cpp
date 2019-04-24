#include <stdio.h>
#include <map>

using namespace std;

map<int64_t, char*> typenames;

map<int64_t, map<int32_t, int64_t> > typecounters;

typedef cstring char*;

bool typeprofile_contains(int64_t siteId) {
    return typecounters.find(siteId) != typecounters.end();
}

bool typeprofile_contains_pair(int64_t siteId, int32_t typeId) {
    if (typeprofile_contains(siteId)) {
        std::map<int32_t, int64_t> values = typecounters[siteId];
        return values.find(typeId) != values.end();
    } else {
        return false;
    }
}

void typeprofile_increment(int64_t siteId, int32_t typeId) {
    if (!typeprofile_contains(siteId)) {
        typecounters[siteId] = *new map<int32_t, int64_t>();
    }

    if (!typeprofile_contains_pair(siteId, typeId)) {
        typecounters[siteId][typeId] = 0L;
    }

    typecounters[siteId][typeId] += 1L;
}

void typeprofile_print(FILE* out) {
    typedef map<int64_t, map<int32_t, int64_t> >::iterator outer_it;
    typedef map<int32_t, int64_t>::iterator inner_it;

    for (outer_it m = typecounters.begin(); m != typecounters.end(); ++m) {
        fprintf(out, "= `%lld`:\n", m->first);
        for (inner_it n = m->second.begin(); n != m->second.end(); ++n) {
            fprintf(out, "\t%`%s` (%lld)\n", typenames[n->first], n->second);
        }
    }
}

extern "C" {
    void typeprofile_log(int64_t siteId, void* obj) {
        if (obj != NULL) {
            typeprofile_increment(siteId, **((int32_t **) obj));
        }
    }

    void typeprofile_dump(char* file_name) {
        if (!typecounters.empty()) {
            FILE* file = fopen(file_name, "w");
            if (file == NULL) {
                fprintf(stderr, "Couldn't open file %s for writing.\n", file_name);
                exit(1);
            } else {
                fprintf(stdout, "Dumping type profile to %s.\n", file_name);
                typeprofile_print(file);
                fclose(file);
            }
        }
    }
}
