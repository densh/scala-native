cmake_minimum_required(VERSION 3.6)
project(markandsweep)

set(CMAKE_CXX_STANDARD 11)

set(SOURCE_FILES
        datastructures/Bitmap.h
        datastructures/FreeList.h
        datastructures/Stack.h
        headers/BlockHeader.h
        headers/ObjectHeader.h
        utils/MathUtils.h
        Allocator.c
        Allocator.h
        Block.c
        Block.h
        Constants.h
        GCTypes.h
        Heap.c
        Heap.h
        LargeAllocator.c
        LargeAllocator.h
        MarkAndSweepGC.c
        Marker.c
        Marker.h
        Object.c
        Object.h
        Log.h)

add_executable(markandsweep ${SOURCE_FILES})