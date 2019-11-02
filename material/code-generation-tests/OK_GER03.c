#define FALTAIMPLEMENTAR 0

#include <string.h>
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

char * concat( char * str1, char * str2){
    char * newStr = malloc(strlen(str1) + strlen(str2) + 1);
    if(newStr != NULL){
        strcpy(newStr, str1);
        strcat(newStr, str2);
        }
    return newStr;
}
typedef void (*Func)();
// Codigo da classe _class_A
typedef struct _St_A {
    Func* vt;
}_class_A;

_class_A* new_A(void);

void _A_m( _class_A* self) {
    printf("%d", 6);
    printf("%s", " ");
    if (true && true ) {
        printf("%d", 1);
        printf("%s", " ");
    }
    if (( false && true ) ) {
        printf("%d", 1000);
        printf("%s", " ");
    }
    if (true && false ) {
        printf("%d", 1000);
        printf("%s", " ");
    }
    if (( false && false ) ) {
        printf("%d", 1000);
        printf("%s", " ");
    }
    if (true || true ) {
        printf("%d", 2);
        printf("%s", " ");
    }
    if (( true || false ) ) {
        printf("%d", 3);
        printf("%s", " ");
    }
    if (false || true ) {
        printf("%d", 4);
        printf("%s", " ");
    }
    if (( false || false ) ) {
        printf("%d", 1000);
        printf("%s", " ");
    }
    if (( ! false ) ) {
        printf("%d", 5);
        printf("%s", " ");
    }
    if (! true ) {
        printf("%d", 1000);
        printf("%s", " ");
    }
    if (( true || ( true && false ) ) ) {
        printf("%d", 6);
        printf("%s", " ");
    }
}

Func VT_class_A[] = {
    (Func) _A_m
};

_class_A* new_A(){
    _class_A* t;
    if ( (t = malloc(sizeof(_class_A))) != NULL )
        t->vt = VT_class_A;
    return t;
}


// Codigo da classe _class_Program
typedef struct _St_Program {
    Func* vt;
}_class_Program;

_class_Program* new_Program(void);

void _Program_run( _class_Program* self) {
    _class_A* _a;
    printf("%s\n", "6 1 2 3 4 5 6");
    _a = new_A();
    _A_m(_a);
}

Func VT_class_Program[] = {
    (Func) _Program_run
};

_class_Program* new_Program(){
    _class_Program* t;
    if ( (t = malloc(sizeof(_class_Program))) != NULL )
        t->vt = VT_class_Program;
    return t;
}


int main(void) {
    _class_Program* program;
    program = new_Program();
    _Program_run(program);
    return 0;
}

