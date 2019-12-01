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

void _A_init( _class_A *self);

int _A_get_A( _class_A *self) {
    return (int) self->_class_A_k;
}

void _A_init( _class_A *self) {
    self->_class_A_k = 1;
}

Func VT_class_A[] = {
    (void (*) () ) _A_get_A,
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

int _B_get_B( _class_B *self) {
    return (int) self->_class_B_k;
}

void _B_init( _class_B *self) {
    _A_init((_class_A*)self);
    self->_class_B_k = 2;
}

Func VT_class_B[] = {
    (void (*) () ) _A_get_A,
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
    int _class_B_k;
    int _class_C_k;
}_class_C;

_class_C* new_C(void);

int _C_get_C( _class_C *self);

void _C_init( _class_C *self);

int _C_get_C( _class_C *self) {
    return (int) self->_class_C_k;
}

void _C_init( _class_C *self) {
    _B_init((_class_B*)self);
    self->_class_C_k = 3;
}

Func VT_class_C[] = {
    (void (*) () ) _A_get_A,
    (void (*) () ) _C_init,
    (void (*) () ) _B_get_B,
    (void (*) () ) _C_get_C
};

_class_C* new_C(){
    _class_C* t;
    if ( (t = malloc(sizeof(_class_C))) != NULL )
        t->vt = VT_class_C;
    return t;
}

// Codigo da classe _class_D
typedef struct _St_D {
    Func* vt;
    int _class_A_k;
    int _class_B_k;
    int _class_C_k;
    int _class_D_k;
}_class_D;

_class_D* new_D(void);

int _D_get_D( _class_D *self);

void _D_init( _class_D *self);

int _D_get_D( _class_D *self) {
    return (int) self->_class_D_k;
}

void _D_init( _class_D *self) {
    _C_init((_class_C*)self);
    self->_class_D_k = 4;
}

Func VT_class_D[] = {
    (void (*) () ) _A_get_A,
    (void (*) () ) _D_init,
    (void (*) () ) _B_get_B,
    (void (*) () ) _C_get_C,
    (void (*) () ) _D_get_D
};

_class_D* new_D(){
    _class_D* t;
    if ( (t = malloc(sizeof(_class_D))) != NULL )
        t->vt = VT_class_D;
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
    _class_D *_d;
    printf("%s\n", "4 3 2 1");
    _d = new_D();
    ((void(*)( _class_D *))_d->vt[1] )(_d);
    printf("%d", ((int(*)( _class_D *))_d->vt[4] )(_d));
    printf("%s", " ");
    _c = (_class_C*) _d;
    printf("%d", ((int(*)( _class_C *))_c->vt[3] )(_c));
    printf("%s", " ");
    _b = (_class_B*) _c;
    printf("%d", ((int(*)( _class_B *))_b->vt[2] )(_b));
    printf("%s", " ");
    _a = (_class_A*) _b;
    printf("%d", ((int(*)( _class_A *))_a->vt[0] )(_a));
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

