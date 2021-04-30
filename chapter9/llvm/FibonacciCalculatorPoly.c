#include <stdio.h>
#include <stdlib.h>
#include <sys/time.h>

long fib(int i) {
    int fib1 = 0;
    int fib2 = 1;
    int currentFib, index;
    long total = 0;
    for (index = 2; index < i; ++index)
    {
        currentFib = fib1 + fib2;
        fib1 = fib2;
        fib2 = currentFib;
        total += currentFib;
    }
    printf("Inside C code: %ld \n", total);
    return total;
}

int main(int argc, char const *argv[])
{
    for (int i = 1000000000; i < 1000000010; i++)
    {
        struct timeval tv_start;
        struct timeval tv_end;
        long time;
        gettimeofday(&tv_start, NULL);
        fib(i);
        gettimeofday(&tv_end, NULL);
        time = (tv_end.tv_sec*1000000 + tv_end.tv_usec) - (tv_start.tv_sec*1000000 + tv_start.tv_usec);
        printf("i=%d time: %10ld\n", i, time);
    }
    return 0;
}