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
    int _n;
    Func* vt;
}_class_A;

_class_A* new_A(void);

void _A_set( _class_A *self, int _pn) {
    printf("%d", 1);
    printf("%s", " ");
    FALTAIMPLEMENTAR = _pn;
}

int _A_get( _class_A *self) {
    return (int) FALTAIMPLEMENTAR;
}

void _A_print( _class_A *self) {
    printf("%s", "A ");
}

void _A_p1( _class_A *self) {
    printf("%s", "999 ");
}

void _A_p2( _class_A *self) {
    printf("%s", "888 ");
}

Func VT_class_A[] = {
    (Func) _A_set,
    (Func) _A_get,
    (Func) _A_print
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

void _B_set( _class_B *self, int _pn) {
    printf("%d", _pn);
    printf("%s", " ");
    FALTAIMPLEMENTAR;
}

void _B_p1( _class_B *self) {
    printf("%d", 2);
    printf("%s", " ");
}

void _B_print( _class_B *self) {
    printf("%s", "B ");
}

void _B_p2( _class_B *self) {
}

Func VT_class_B[] = {
    (Func) _B_set,
    (Func) _B_p1,
    (Func) _B_print
};

_class_B* new_B(){
    _class_B* t;
    if ( (t = malloc(sizeof(_class_B))) != NULL )
        t->vt = VT_class_B;
    return t;
}

// Codigo da classe _class_Program
typedef struct _St_Program {
    _class_Program _program;
    Func* vt;
}_class_Program;

_class_Program* new_Program(void);

void _Program_print( _class_Program *self) {
    printf("%s", "P ");
}

_class_B* _Program_m( _class_Program *self, _class_A *_a) {
    (_a->vt[0] )(_a, 0);
    return (_class_B* ) new_B();
}

_class_A* _Program_p( _class_Program *self, int _i) {
    if (_i > 0 ) {
        return (_class_A* ) new_A();
    } else {
        return (_class_A* ) new_B();
    }
}

void _Program_run( _class_Program *self) {
    _class_A *_a, *_a2;
    _class_B *_b;
    printf("%s\n", "0 1 0 1 0 1 2 B A 0 1 P");
    _a = new_A();
    _b = new_B();
    _a = (_class_A*)_b;
    (_a->vt[0] )(_a, 0);
    _a = (_class_A*)FALTAIMPLEMENTAR;
    _b = FALTAIMPLEMENTAR;
    (_b->vt[4] )(_b);
    _a = FALTAIMPLEMENTAR;
    (_a->vt[2] )(_a);
    _a = FALTAIMPLEMENTAR;
    (_a->vt[2] )(_a);
    _a = (_class_A*)NULL;
    _b = (_class_B*)NULL;
    _a2 = new_A();
    if (_a == _b ) {
        printf("%d", 0);
        printf("%s", " ");
    }
    if (_b == _a ) {
        printf("%d", 1);
        printf("%s", " ");
    }
    if (_a == _a2 ) {
        printf("%d", 2);
        printf("%s", " ");
    }
    FALTAIMPLEMENTAR = new_Program();
    FALTAIMPLEMENTAR;
}

Func VT_class_Program[] = {
    (Func) _Program_print,
    (Func) _Program_m,
    (Func) _Program_p,
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

