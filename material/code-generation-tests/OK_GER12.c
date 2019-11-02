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

void _A_m1( _class_A* self) {
    printf("%d", 1);
    printf("%s", " ");
}

void _A_m2( _class_A* self, int _n) {
    printf("%d", _n);
    printf("%s", " ");
}

Func VT_class_A[] = {
    (Func) _A_m1,
    (Func) _A_m2
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

void _B_m2( _class_B* self, int _n) {
    printf("%d", _n);
    printf("%s", " ");
    FALTAIMPLEMENTAR;
}

Func VT_class_B[] = {
    (Func) _B_m2
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

void _C_m1( _class_C* self) {
    FALTAIMPLEMENTAR;
    printf("%d", 2);
    printf("%s", " ");
}

void _C_m3( _class_C* self) {
    FALTAIMPLEMENTAR;
    printf("%d", 1);
    printf("%s", " ");
    printf("%d", 2);
    printf("%s", " ");
}

Func VT_class_C[] = {
    (Func) _C_m1,
    (Func) _C_m3
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
    printf("%s\n", "1 2 1 2 1 2 1 2");
    _b = new_B();
    _B_m2(_b, 1);
    _c = new_C();
    _C_m1(_c);
    _C_m3(_c);
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

