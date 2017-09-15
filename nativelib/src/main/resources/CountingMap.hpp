#ifndef CountingMap_hpp
#define CountingMap_hpp

#include <map>
#include <stdio.h>
#include <string>

using namespace std;

class CountingMap {
private:
    map<int64_t, map<int32_t, int64_t> > backing;
    bool contains(int64_t siteId);
    bool contains_pair(int64_t siteId, int32_t typeId);
    unsigned long occurrences(int64_t siteId, int32_t typeId);

public:
    void increment(int64_t siteid, int32_t typeId);
    void print(FILE* out);
};

#endif /* CountingMap_hpp */
