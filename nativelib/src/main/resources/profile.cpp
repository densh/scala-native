#include <stdio.h>
#include <map>

struct ProfileStorage { public:
        int64_t callCount;
        int64_t edgeCount;
        int64_t callSiteCount;
        void*   slots[0];
};

std::map<int64_t, ProfileStorage*> methodProfileStorage;

extern "C" {
    ProfileStorage* scalanative_recordcall(int64_t methodId, int64_t edgeCount, int64_t callSiteCount) {
        if (methodProfileStorage.count(methodId) != 0) {
            ProfileStorage* storage = methodProfileStorage[methodId];
            storage->callCount += 1;
            return storage;
        } else {
            int64_t slotCount = edgeCount + callSiteCount;
            size_t storageSize = sizeof(ProfileStorage) + sizeof(int64_t) * slotCount;
            ProfileStorage* storage = (ProfileStorage*) malloc(storageSize);
            memset(storage, 0, storageSize);
            methodProfileStorage[methodId] = storage;
            storage->callCount = 1;
            storage->edgeCount = edgeCount;
            storage->callSiteCount = callSiteCount;
            return storage;
        }
    }

    void scalanative_recordedge(ProfileStorage* storage, int64_t edgeId) {
        int64_t edgeCount = (int64_t) storage->slots[edgeId];
        storage->slots[edgeId] = (void*) (edgeCount + 1);
    }

    void scalanative_recordtype(ProfileStorage* storage, int64_t callSiteId, void* object) {
        auto typeIdPtr = (int32_t**) object;
        auto typeId    = (int64_t) **typeIdPtr;

        std::map<int64_t, int64_t>* typeMap = nullptr;

        void** slotptr = &storage->slots[storage->edgeCount + callSiteId];
        if (*slotptr == nullptr) {
            typeMap = new std::map<int64_t, int64_t>();
            *slotptr = typeMap;
        } else {
            typeMap = (std::map<int64_t, int64_t>*) *slotptr;
        }

        if (typeMap->count(typeId) == 0) {
            typeMap->operator[](typeId) = 1LL;
        } else {
            typeMap->operator[](typeId) = typeMap->operator[](typeId) + 1LL;
        }
    }

    void scalanative_persistprofile(char* fileName) {
        if (!methodProfileStorage.empty()) {
            FILE* file = fopen(fileName, "w");

            for (auto it = methodProfileStorage.begin(); it != methodProfileStorage.end(); ++it) {
                auto methId = it->first;
                auto profileStorage = it->second;

                fprintf(file, "method%lld.callCount = %lld\n", methId, profileStorage->callCount);
                fprintf(file, "method%lld.edgeCount = %lld\n", methId, profileStorage->edgeCount);
                fprintf(file, "method%lld.callSiteCount = %lld\n", methId, profileStorage->callSiteCount);

                for (auto edgeId = 0LL; edgeId < profileStorage->edgeCount; ++edgeId) {
                    auto edgeCount = (int64_t) profileStorage->slots[edgeId];
                    fprintf(file, "method%lld.edge%lld = %lld\n", methId, edgeId, edgeCount);
                }

                for (auto callSiteId = 0LL; callSiteId < profileStorage->callSiteCount; ++callSiteId) {
                    auto typeMap = (std::map<int64_t, int64_t>*) profileStorage->slots[profileStorage->edgeCount + callSiteId];

                    if (typeMap != nullptr) {
                        for (auto it = typeMap->begin(); it != typeMap->end(); ++it) {
                            auto typeId = it->first;
                            auto typeCount = it->second;

                            fprintf(file, "method%lld.callSite%lld.type%lld = %lld\n", methId, callSiteId, typeId, typeCount);
                        }
                    }
                }
            }

            fclose(file);
        }
    }
}
