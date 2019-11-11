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
    int _class_A_i;
    int _class_A_j;
    Func* vt;
}_class_A;

_class_A* new_A(void);

void _A_p( _class_A *self);

void _A_q( _class_A *self);

void _A_init_A( _class_A *self);

void _A_call_p( _class_A *self);

void _A_call_q( _class_A *self);

void _A_r( _class_A *self);

void _A_s( _class_A *self);

void _A_p( _class_A *self) {
    printf("%d", self->_class_A_i);
    printf("%s", " ");
}

void _A_q( _class_A *self) {
    printf("%d", self->_class_A_j);
    printf("%s", " ");
}

void _A_init_A( _class_A *self) {
    self->_class_A_i = 1;
    self->_class_A_j = 2;
}

void _A_call_p( _class_A *self) {
    ( (void(*)())_A_p)(self);
}

void _A_call_q( _class_A *self) {
    ( (void(*)())_A_q)(self);
}

void _A_r( _class_A *self) {
    printf("%d", self->_class_A_i);
    printf("%s", " ");
}

void _A_s( _class_A *self) {
    printf("%d", self->_class_A_j);
    printf("%s", " ");
}

Func VT_class_A[] = {
    (void(*)( _class_A)) _A_init_A,
    (void(*)( _class_A)) _A_call_p,
    (void(*)( _class_A)) _A_call_q,
    (void(*)( _class_A)) _A_r,
    (void(*)( _class_A)) _A_s
};

_class_A* new_A(){
    _class_A* t;
    if ( (t = malloc(sizeof(_class_A))) != NULL )
        t->vt = VT_class_A;
    return t;
}

// Codigo da classe _class_B
typedef struct _St_B {
    int _class_A_i;
    int _class_A_j;
    int _class_B_i;
    int _class_B_j;
    Func* vt;
}_class_B;

_class_B* new_B(void);

void _B_p( _class_B *self);

void _B_q( _class_B *self);

void _B_init_B( _class_B *self);

void _B_call_p( _class_B *self);

void _B_call_q( _class_B *self);

void _B_r( _class_B *self);

void _B_s( _class_B *self);

void _B_p( _class_B *self) {
    printf("%d", self->_class_B_i);
    printf("%s", " ");
}

void _B_q( _class_B *self) {
    printf("%d", self->_class_B_j);
    printf("%s", " ");
}

void _B_init_B( _class_B *self) {
    self->_class_B_i = 3;
    self->_class_B_j = 4;
}

void _B_call_p( _class_B *self) {
    ( (void(*)())_B_p)(self);
}

void _B_call_q( _class_B *self) {
    ( (void(*)())_B_q)(self);
}

void _B_r( _class_B *self) {
    printf("%d", self->_class_B_i);
    printf("%s", " ");
}

void _B_s( _class_B *self) {
    printf("%d", self->_class_B_j);
    printf("%s", " ");
}

Func VT_class_B[] = {
    (void(*)( _class_A)) _A_init_A,
    (void(*)( _class_B)) _B_call_p,
    (void(*)( _class_B)) _B_call_q,
    (void(*)( _class_B)) _B_r,
    (void(*)( _class_B)) _B_s,
    (void(*)( _class_B)) _B_init_B
};

_class_B* new_B(){
    _class_B* t;
    if ( (t = malloc(sizeof(_class_B))) != NULL )
        t->vt = VT_class_B;
    return t;
}

// Codigo da classe _class_C
typedef struct _St_C {
    int _class_A_i;
    int _class_A_j;
    int _class_C_i;
    int _class_C_j;
    Func* vt;
}_class_C;

_class_C* new_C(void);

void _C_p( _class_C *self);

void _C_q( _class_C *self);

void _C_init_C( _class_C *self);

void _C_call_p( _class_C *self);

void _C_call_q( _class_C *self);

void _C_r( _class_C *self);

void _C_s( _class_C *self);

void _C_p( _class_C *self) {
    printf("%d", self->_class_C_i);
    printf("%s", " ");
}

void _C_q( _class_C *self) {
    printf("%d", self->_class_C_j);
    printf("%s", " ");
}

void _C_init_C( _class_C *self) {
    self->_class_C_i = 5;
    self->_class_C_j = 6;
}

void _C_call_p( _class_C *self) {
    ( (void(*)())_C_p)(self);
}

void _C_call_q( _class_C *self) {
    ( (void(*)())_C_q)(self);
}

void _C_r( _class_C *self) {
    printf("%d", self->_class_C_i);
    printf("%s", " ");
}

void _C_s( _class_C *self) {
    printf("%d", self->_class_C_j);
    printf("%s", " ");
}

Func VT_class_C[] = {
    (void(*)( _class_A)) _A_init_A,
    (void(*)( _class_C)) _C_call_p,
    (void(*)( _class_C)) _C_call_q,
    (void(*)( _class_C)) _C_r,
    (void(*)( _class_C)) _C_s,
    (void(*)( _class_C)) _C_init_C
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
    printf("%s\n", "1 2 1 2 3 4 3 4 5 6 5 6");
    _a = new_A();
    ( (void(*)())_a->vt[0] )(_a);
    ( (void(*)())_a->vt[1] )(_a);
    ( (void(*)())_a->vt[2] )(_a);
    ( (void(*)())_a->vt[3] )(_a);
    ( (void(*)())_a->vt[4] )(_a);
    _b = new_B();
    ( (void(*)())_b->vt[5] )(_b);
    ( (void(*)())_b->vt[0] )(_b);
    ( (void(*)())_b->vt[1] )(_b);
    ( (void(*)())_b->vt[2] )(_b);
    ( (void(*)())_b->vt[3] )(_b);
    ( (void(*)())_b->vt[4] )(_b);
    _c = new_C();
    ( (void(*)())_c->vt[5] )(_c);
    ( (void(*)())_c->vt[0] )(_c);
    ( (void(*)())_c->vt[5] )(_c);
    ( (void(*)())_c->vt[1] )(_c);
    ( (void(*)())_c->vt[2] )(_c);
    ( (void(*)())_c->vt[3] )(_c);
    ( (void(*)())_c->vt[4] )(_c);
}

Func VT_class_Program[] = {
    (void(*)( _class_Program)) _Program_run
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

