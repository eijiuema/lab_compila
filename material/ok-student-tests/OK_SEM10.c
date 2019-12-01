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
    fgets(__s,512,stdin);
    sscanf(__s, "%d", &_n);
    return _n;
}
char *readString() {
    char s[512];
    fgets(s,512,stdin);
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
char * intToStr(int i){
    char * str = malloc(sizeof(char)*12);
    sprintf(str, "%d", i);
    return str;
}
typedef void (*Func)();

// Codigo da classe _class_A
typedef struct _St_A {
    Func* vt;
}_class_A;

_class_A* new_A(void);

void _A_first( _class_A *self, int _pn);

void _A_first( _class_A *self, int _pn) {
}

Func VT_class_A[] = {
    (void (*) () ) _A_first
};

_class_A* new_A(){
    _class_A* t;
    if ( (t = malloc(sizeof(_class_A))) != NULL )
        t->vt = VT_class_A;
    return t;
}

// Codigo da classe _class_B
typedef struct _St_B {
    Func* vt;
}_class_B;

_class_B* new_B(void);

void _B_second( _class_B *self);

void _B_second( _class_B *self) {
}

Func VT_class_B[] = {
    (void (*) () ) _A_first,
    (void (*) () ) _B_second
};

_class_B* new_B(){
    _class_B* t;
    if ( (t = malloc(sizeof(_class_B))) != NULL )
        t->vt = VT_class_B;
    return t;
}

// Codigo da classe _class_C
typedef struct _St_C {
    Func* vt;
}_class_C;

_class_C* new_C(void);

void _C_third( _class_C *self);

void _C_third( _class_C *self) {
}

Func VT_class_C[] = {
    (void (*) () ) _A_first,
    (void (*) () ) _B_second,
    (void (*) () ) _C_third
};

_class_C* new_C(){
    _class_C* t;
    if ( (t = malloc(sizeof(_class_C))) != NULL )
        t->vt = VT_class_C;
    return t;
}

// Codigo da classe _class_Program
typedef struct _St_Program {
    Func* vt;
}_class_Program;

_class_Program* new_Program(void);

void _Program_run( _class_Program *self);

void _Program_run( _class_Program *self) {
    _class_A *_a;
    _class_B *_b;
    _class_C *_c;
    _a = new_A();
    _b = new_B();
    _c = new_C();
    ((void(*)( _class_A *, int ))_a->vt[0] )(_a, 0);
    ((void(*)( _class_A *, int ))_b->vt[0] )((_class_A*)_b, 0);
    ((void(*)( _class_A *, int ))_c->vt[0] )((_class_A*)_c, 0);
    ((void(*)( _class_B *))_b->vt[1] )(_b);
    ((void(*)( _class_B *))_c->vt[1] )((_class_B*)_c);
    ((void(*)( _class_C *))_c->vt[2] )(_c);
    _a = (_class_A*) _b;
    _a = (_class_A*) _c;
    _b = (_class_B*) _c;
}

Func VT_class_Program[] = {
    (void (*) () ) _Program_run
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

