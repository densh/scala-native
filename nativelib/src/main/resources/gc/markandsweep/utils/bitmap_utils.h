#ifndef BITMAP_UTILS_H
#define BITMAP_UTILS_H

#include <stdio.h>
#include "../bitmap.h"
#include "../block.h"

void bitmap_print(Bitmap* bitmap);
void bitmap_print_with_rtti(Bitmap* bitmap);
void bitmap_check(Bitmap* bitmap);

#endif //BITMAP_UTILS_H