#ifndef SAFEPOINT_H
#define SAFEPOINT_H

void scalanative_safepoint();
void scalanative_safepoint_on();
void scalanative_safepoint_off();
void scalanative_safepoint_handler(int sig, siginfo_t *info, void *ucontext);
void scalanative_safepoint_init();
extern bool scalanative_safepoint_status;

#endif // SAFEPOINT_H
