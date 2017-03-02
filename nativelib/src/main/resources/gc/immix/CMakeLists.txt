cmake_minimum_required(VERSION 3.6)
project(immix)

set(CMAKE_C_STANDARD 99)

set(SOURCE_FILES headers/BlockHeader.h headers/ObjectHeader.h headers/LineHeader.h headers/gc_types.h Object.c Object.h Block.c Block.h Line.c Line.h Constants.h utils/MathUtils.h Allocator.c Allocator.h Heap.c Heap.h ImmixGC.c ProgramRoots.h Marker.c Marker.h Stack.c Stack.h BlockList/BlocList.c BlockList/BlocList.h LargeAllocator.c LargeAllocator.h bitmap/Bitmap.c bitmap/Bitmap.h)
add_library(immix ${SOURCE_FILES})