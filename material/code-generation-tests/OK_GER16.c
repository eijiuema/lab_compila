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
    private int k;
    Func* vt;
}_class_A;

_class_A* new_A(void);

int _A_get_A( _class_A* self) {
    return FALTAIMPLEMENTAR;
}

void _A_set( _class_A* selfint _k) {
    FALTAIMPLEMENTAR = _k;
}

void _A_print( _class_A* self) {
    printf("%d", FALTAIMPLEMENTAR);
    printf("%s", " ");
}

void _A_init( _class_A* self) {
    FALTAIMPLEMENTAR;
}

Func VT_class_A[] = {
    (Func) _A_get_A,
    (Func) _A_set,
    (Func) _A_print,
    (Func) _A_init
};

_class_A* new_A(){
    _class_A* t;
    if ( (t = malloc(sizeof(_class_A))) != NULL )
        t->vt = VT_class_A;
    return t;
}


// Codigo da classe _class_B
typedef struct _St_B {
    private int k;
    Func* vt;
}_class_B;

_class_B* new_B(void);

int _B_get_B( _class_B* self) {
    return FALTAIMPLEMENTAR;
}

void _B_init( _class_B* self) {
    FALTAIMPLEMENTAR;
    FALTAIMPLEMENTAR = 2;
}

void _B_print( _class_B* self) {
    printf("%d", FALTAIMPLEMENTAR);
    printf("%s", " ");
    printf("%d", FALTAIMPLEMENTAR);
    printf("%s", " ");
    FALTAIMPLEMENTAR;
}

Func VT_class_B[] = {
    (Func) _B_get_B,
    (Func) _B_init,
    (Func) _B_print
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

int _C_get_A( _class_C* self) {
    return 0;
}

Func VT_class_C[] = {
    (Func) _C_get_A
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

void _Program_run( _class_Program* self) {
    _class_A* _a;
    _class_B* _b;
    _class_C* _c;
    printf("%s\n", "2 2 0 0 2 0 0 0 0 0 0");
    _b = new_B();
    _B_init(_b);
    _c = new_C();
    _C_init(_c);
    printf("%d", _B_get_B(_b));
    printf("%s", " ");
    _a = _b;
    _A_print(_a);
    _B_print(_b);
    _A_init(_a);
    _B_init(_b);
    printf("%d", _A_get_A(_a));
    printf("%s", " ");
    printf("%d", _B_get_A(_b));
    printf("%s", " ");
    _a = _c;
    printf("%d", _A_get_A(_a));
    printf("%s", " ");
    _c = new_C();
    printf("%d", _C_get_A(_c));
    printf("%s", " ");
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

