#include <signal.h>
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <sys/mman.h>
#include <libunwind.h>

extern unsigned char scalanative_safepoint_trigger[4096] __attribute__((aligned(4096)));
bool scalanative_safepoint_status = false;

void scalanative_safepoint();

void scalanative_safepoint_on() {
    scalanative_safepoint_status = true;
    mprotect((void*) &scalanative_safepoint_trigger, 4096, PROT_NONE);
}

void scalanative_safepoint_off() {
    scalanative_safepoint_status = false;
    mprotect((void*) &scalanative_safepoint_trigger, 4096, PROT_READ);
}

void scalanative_print_stack_trace() {
	char buffer[256];
	unw_word_t offp;
	unw_cursor_t cursor;
	unw_context_t uc;
	unw_getcontext(&uc);
	unw_init_local(&cursor, &uc);
	while (unw_step(&cursor) > 0) {
		unw_get_proc_name(&cursor, &buffer[0], 256, &offp);
		printf("\tat %s\n", buffer);
	}
}

void scalanative_safepoint_handler(int sig, siginfo_t *info, void *ucontext) {
    if (info->si_addr == (void*) &scalanative_safepoint_trigger) {
        scalanative_safepoint();
    } else {
        printf("Segfault %d at %p\n", sig, info->si_addr);
	    scalanative_print_stack_trace();
        exit(1);
    }
}

void scalanative_safepoint_init() {
    struct sigaction action;
    action.sa_sigaction = scalanative_safepoint_handler;
    action.sa_flags = SA_RESTART | SA_SIGINFO;
    sigemptyset(&action.sa_mask);
    sigaction(SIGSEGV, &action, NULL);
    sigaction(SIGBUS, &action, NULL);
}
