#include "timer.h"


Timer* gc_timer_create() {
    Timer* timer = malloc(sizeof(Timer));
    timer->time = 0;
    timer->current_start = 0;
    timer->interval_index = 0;
    timer->nb_intervals = 0;
    for(int i = 0; i < NB_INTERVALS; i++) {
        timer->intervals[i] = 0;
    }
    return timer;
}

void gc_timer_start(Timer* timer) {
    timer->current_start = clock();
}

void gc_timer_stop(Timer* timer) {
    clock_t diff = clock() - timer->current_start;
    long interval_time = diff * 1000 / CLOCKS_PER_SEC;
    timer->time += interval_time - timer->intervals[timer->interval_index];
    timer->intervals[timer->interval_index] = interval_time;
    timer->interval_index = (timer->interval_index + 1) % NB_INTERVALS;
    timer->nb_intervals++;
}

void gc_timer_reset(Timer* timer) {
    timer->time = 0;
    timer->current_start = 0;
    timer->interval_index = 0;
    for(int i = 0; i < NB_INTERVALS; i++) {
        timer->intervals[i] = 0;
    }
    timer->nb_intervals = 0;
}