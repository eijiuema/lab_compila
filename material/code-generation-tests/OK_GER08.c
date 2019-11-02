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

void _A_m1( _class_A* selfint _n) {
    printf("%d", 1);
    printf("%s", " ");
    printf("%d", _n);
    printf("%s", " ");
}

void _A_m2( _class_A* selfint _n) {
    printf("%d", 2);
    printf("%s", " ");
    printf("%d", _n);
    printf("%s", " ");
}

void _A_m3( _class_A* self, int _n, int _p, char * _q, int _rboolean _falseBool) {
    printf("%d", 3);
    printf("%s", " ");
    printf("%d", _n);
    printf("%s", " ");
    printf("%d", _p);
    printf("%s", " ");
    printf("%s", _q);
    printf("%s", " ");
    printf("%d", _r);
    printf("%s", " ");
    if (_falseBool ) {
        printf("%s",  concat( 8, " "));
    } else {
        printf("%s",  concat( 7, " "));
    }
}

Func VT_class_A[] = {
    (Func) _A_m1,
    (Func) _A_m2,
    (Func) _A_m3
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
    printf("%s\n", "1 1 2 2 3 3 4 5 6 7");
    _a = new_A();
    _A_m1(_a, 1);
    _A_m2(_a, 2);
    _A_m3(_a, 3, 4, "5", 6, false);
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

