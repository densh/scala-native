#ifndef LINKED_LIST_UTILS_H
#define LINKED_LIST_UTILS_H

#include <stdio.h>
#include "../linked_list.h"
#include "../bitmap.h"

void linked_list_print(LinkedList* list);
void linked_list_check(LinkedList* list, int expectedSize, Bitmap* bitmap);

#endif //LINKED_LIST_UTILS_H