#ifndef TIMER_H
#define TIMER_H

#include <time.h>
#include <stdlib.h>

#define NB_INTERVALS 20

typedef struct {
    long time;
    clock_t current_start;
    long intervals[NB_INTERVALS];
    int interval_index;
    int nb_intervals;
} Timer;

Timer* gc_timer_create();
void gc_timer_start(Timer* timer);
void gc_timer_stop(Timer* timer);
void gc_timer_reset(Timer* timer);


#endif //TIMER_H