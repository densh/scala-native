#ifndef CMS_H
#define CMS_H

#include <stddef.h>

void CMS_init();
void *CMS_allocate(size_t byteSize);
void CMS_safepoint();

#endif // CMS_H
