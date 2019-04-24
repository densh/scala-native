#include <stdio.h>
#include <string.h>
#include <map>
#include <inttypes.h>

struct ProfileStorage { public:
        int64_t callCount;
        int64_t edgeCount;
        int64_t callSiteCount;
        void*   slots[0];
};

extern int32_t __profile_storage_size;
extern ProfileStorage* __profile_storage[0];

#define NOINLINE __attribute__((noinline))
#define INLINE __attribute__((always_inline))

extern "C" {
    NOINLINE ProfileStorage* scalanative_initprofilestorage(int64_t methodId, int64_t edgeCount, int64_t callSiteCount) {
        int64_t slotCount = edgeCount + callSiteCount;
        size_t storageSize = sizeof(ProfileStorage) + sizeof(int64_t) * slotCount;
        ProfileStorage* storage = (ProfileStorage*) malloc(storageSize);
        memset(storage, 0, storageSize);
        __profile_storage[methodId] = storage;
        storage->callCount = 1;
        storage->edgeCount = edgeCount;
        storage->callSiteCount = callSiteCount;
        return storage;
    }

    INLINE ProfileStorage* scalanative_recordcall(int64_t methodId, int64_t edgeCount, int64_t callSiteCount) {
        ProfileStorage* storage = __profile_storage[methodId];

        if (storage == nullptr) {
            return scalanative_initprofilestorage(methodId, edgeCount, callSiteCount);
        } else {
            storage->callCount += 1;
            return storage;
        }
    }

    INLINE void scalanative_recordedge(ProfileStorage* storage, int64_t edgeId) {
        int64_t edgeCount = (int64_t) storage->slots[edgeId];
        storage->slots[edgeId] = (void*) (edgeCount + 1);
    }

    INLINE void scalanative_recordtype(ProfileStorage* storage, int64_t callSiteId, void* object) {
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

    NOINLINE void scalanative_persistprofile(char* fileName) {
        if (__profile_storage_size > 0) {
            FILE* file = fopen(fileName, "w");

            for (int64_t methodId = 0; methodId < __profile_storage_size; methodId++) {
                auto profileStorage = __profile_storage[methodId];

                if (profileStorage != nullptr) {
                    fprintf(file, "method%" PRId64 ".callCount = %" PRId64 "\n", methodId, profileStorage->callCount);
                    fprintf(file, "method%" PRId64 ".edgeCount = %" PRId64 "\n", methodId, profileStorage->edgeCount);
                    fprintf(file, "method%" PRId64 ".callSiteCount = %" PRId64 "\n", methodId, profileStorage->callSiteCount);

                    for (int64_t edgeId = 0; edgeId < profileStorage->edgeCount; ++edgeId) {
                        auto edgeCount = (int64_t) profileStorage->slots[edgeId];
                        fprintf(file, "method%" PRId64 ".edge%" PRId64 " = %" PRId64 "\n", methodId, edgeId, edgeCount);
                    }

                    for (int64_t callSiteId = 0; callSiteId < profileStorage->callSiteCount; ++callSiteId) {
                        auto typeMap = (std::map<int64_t, int64_t>*) profileStorage->slots[profileStorage->edgeCount + callSiteId];

                        if (typeMap != nullptr) {
                            for (auto it = typeMap->begin(); it != typeMap->end(); ++it) {
                                auto typeId = it->first;
                                auto typeCount = it->second;

                                fprintf(file, "method%" PRId64 ".callSite%" PRId64 ".type%" PRId64 " = %" PRId64 "\n", methodId, callSiteId, typeId, typeCount);
                            }
                        }
                    }
                }
            }

            fclose(file);
        }
    }
}
