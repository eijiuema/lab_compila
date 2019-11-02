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
    private int n;
    Func* vt;
}_class_A;

_class_A* new_A(void);

void _A_set( _class_A* selfint _n) {
    FALTAIMPLEMENTAR = _n;
}

int _A_get( _class_A* self) {
    return FALTAIMPLEMENTAR;
}

Func VT_class_A[] = {
    (Func) _A_set,
    (Func) _A_get
};

_class_A* new_A(){
    _class_A* t;
    if ( (t = malloc(sizeof(_class_A))) != NULL )
        t->vt = VT_class_A;
    return t;
}


// Codigo da classe _class_Program
typedef struct _St_Program {
    private A a;
    Func* vt;
}_class_Program;

_class_Program* new_Program(void);

void _Program_print( _class_Program* self) {
    printf("%d", FALTAIMPLEMENTAR);
}

_class_A _Program_get( _class_Program* self) {
    return FALTAIMPLEMENTAR;
}

void _Program_run( _class_Program* self) {
    printf("%s\n", "0");
    printf("%s\n", "0");
}

void _Program_set( _class_Program* self_class_A _a) {
    FALTAIMPLEMENTAR = _a;
}

Func VT_class_Program[] = {
    (Func) _Program_print,
    (Func) _Program_get,
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

