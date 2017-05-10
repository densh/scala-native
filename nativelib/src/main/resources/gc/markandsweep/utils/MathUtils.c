//
// Created by Lukas Kellenberger on 29.04.17.
//

#include "MathUtils.h"

static const int MultiplyDeBruijnBitPosition[32] =
        {
                0, 9, 1, 10, 13, 21, 2, 29, 11, 14, 16, 18, 22, 25, 3, 30,
                8, 12, 20, 28, 15, 17, 24, 7, 19, 27, 23, 6, 26, 5, 4, 31
        };

int log2_floor(size_t v) {


    v |= v >> 1; // first round down to one less than a power of 2
    v |= v >> 2;
    v |= v >> 4;
    v |= v >> 8;
    v |= v >> 16;

    return MultiplyDeBruijnBitPosition[(uint32_t)(v * 0x07C4ACDDU) >> 27];
}

inline int log2_ceil(size_t value) {
    return log2_floor(2 * value - 1);
}