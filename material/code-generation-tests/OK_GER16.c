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
    int _class_A_k;
}_class_A;

_class_A* new_A(void);

int _A_get_A( _class_A *self);

void _A_set( _class_A *self, int _k);

void _A_print( _class_A *self);

void _A_init( _class_A *self);

int _A_get_A( _class_A *self) {
    return (int) self->_class_A_k;
}

void _A_set( _class_A *self, int _k) {
    self->_class_A_k = _k;
}

void _A_print( _class_A *self) {
    printf("%d", ((int(*)( _class_A *))self->vt[0] )(self));
    printf("%s", " ");
}

void _A_init( _class_A *self) {
    _A_set(self, 0);
}

Func VT_class_A[] = {
    (void (*) () ) _A_get_A,
    (void (*) () ) _A_set,
    (void (*) () ) _A_print,
    (void (*) () ) _A_init
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
    int _class_A_k;
    int _class_B_k;
}_class_B;

_class_B* new_B(void);

int _B_get_B( _class_B *self);

void _B_init( _class_B *self);

void _B_print( _class_B *self);

int _B_get_B( _class_B *self) {
    return (int) self->_class_B_k;
}

void _B_init( _class_B *self) {
    _A_init((_class_A*)self);
    self->_class_B_k = 2;
}

void _B_print( _class_B *self) {
    printf("%d", ((int(*)( _class_B *))self->vt[4] )(self));
    printf("%s", " ");
    printf("%d", ((int(*)( _class_A *))self->vt[0] )((_class_A*)self));
    printf("%s", " ");
    _A_print((_class_A*)self);
}

Func VT_class_B[] = {
    (void (*) () ) _A_get_A,
    (void (*) () ) _A_set,
    (void (*) () ) _B_print,
    (void (*) () ) _B_init,
    (void (*) () ) _B_get_B
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
    int _class_A_k;
}_class_C;

_class_C* new_C(void);

int _C_get_A( _class_C *self);

int _C_get_A( _class_C *self) {
    return (int) 0;
}

Func VT_class_C[] = {
    (void (*) () ) _C_get_A,
    (void (*) () ) _A_set,
    (void (*) () ) _A_print,
    (void (*) () ) _A_init
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
    printf("%s\n", "2 2 0 0 2 0 0 0 0 0 0");
    _b = new_B();
    ((void(*)( _class_B *))_b->vt[3] )(_b);
    _c = new_C();
    ((void(*)( _class_A *))_c->vt[3] )((_class_A*)_c);
    printf("%d", ((int(*)( _class_B *))_b->vt[4] )(_b));
    printf("%s", " ");
    _a = (_class_A*) _b;
    ((void(*)( _class_A *))_a->vt[2] )(_a);
    ((void(*)( _class_B *))_b->vt[2] )(_b);
    ((void(*)( _class_A *))_a->vt[3] )(_a);
    ((void(*)( _class_B *))_b->vt[3] )(_b);
    printf("%d", ((int(*)( _class_A *))_a->vt[0] )(_a));
    printf("%s", " ");
    printf("%d", ((int(*)( _class_A *))_b->vt[0] )((_class_A*)_b));
    printf("%s", " ");
    _a = (_class_A*) _c;
    printf("%d", ((int(*)( _class_A *))_a->vt[0] )(_a));
    printf("%s", " ");
    _c = new_C();
    printf("%d", ((int(*)( _class_C *))_c->vt[0] )(_c));
    printf("%s", " ");
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

