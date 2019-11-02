#include <malloc.h>
#include <stdlib.h>
#include <stdio.h>

typedef int boolean;
#define true 1
#define false 0

int readInt() {
    int _n;
    char __s[512];
    gets(__s);
    sscanf(__s, "%d", &_n);
    return _n;
}
char *readString() {
    char s[512];
    gets(s);
    char *ret = malloc(strlen(s) + 1);
    strcpy(ret, s);
    return ret;
}

//Gerar codigo de A

//Gerar codigo de Program

typedef void (*Func)();
int main() {
    //_class_Program *program;
    //program = new_Program();
    //_Program_run(program);
    return 0;
}

