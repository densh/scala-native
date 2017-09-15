#include <map>
#include <stdio.h>
#include <string>
#include "CountingMap.hpp"

using namespace std;

bool CountingMap::contains(int64_t siteId) {
    return this->backing.find(siteId) != this->backing.end();
}

bool CountingMap::contains_pair(int64_t siteId, int32_t typeId) {
    if (this->contains(siteId)) {
        std::map<int32_t, int64_t> values = this->backing[siteId];
        return values.find(typeId) != values.end();
    } else {
        return false;
    }
}

void CountingMap::increment(int64_t siteId, int32_t typeId) {
    if (!this->contains(siteId)) {
        this->backing[siteId] = *new map<int32_t, int64_t>();
    }

    if (!this->contains_pair(siteId, typeId)) {
        this->backing[siteId][typeId] = 0L;
    }

    this->backing[siteId][typeId] += 1L;
}

void CountingMap::print(FILE* out) {
    typedef map<int64_t, map<int32_t, int64_t> >::iterator outer_it;
    typedef map<int32_t, int64_t>::iterator inner_it;

    for (outer_it m = this->backing.begin(); m != this->backing.end(); ++m) {
        fprintf(out, "= `%llu`:\n", m->first);
        for (inner_it n = m->second.begin(); n != m->second.end(); ++n) {
            fprintf(out, "\t%d (%llu)\n", n->first, n->second);
        }
    }
}
