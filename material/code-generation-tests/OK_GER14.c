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

void _A_init( _class_A* self) {
    FALTAIMPLEMENTAR = 1;
}

Func VT_class_A[] = {
    (Func) _A_get_A,
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

Func VT_class_B[] = {
    (Func) _B_get_B,
    (Func) _B_init
};

_class_B* new_B(){
    _class_B* t;
    if ( (t = malloc(sizeof(_class_B))) != NULL )
        t->vt = VT_class_B;
    return t;
}


// Codigo da classe _class_C
typedef struct _St_C {
    private int k;
    Func* vt;
}_class_C;

_class_C* new_C(void);

int _C_get_C( _class_C* self) {
    return FALTAIMPLEMENTAR;
}

void _C_init( _class_C* self) {
    FALTAIMPLEMENTAR;
    FALTAIMPLEMENTAR = 3;
}

Func VT_class_C[] = {
    (Func) _C_get_C,
    (Func) _C_init
};

_class_C* new_C(){
    _class_C* t;
    if ( (t = malloc(sizeof(_class_C))) != NULL )
        t->vt = VT_class_C;
    return t;
}


// Codigo da classe _class_D
typedef struct _St_D {
    private int k;
    Func* vt;
}_class_D;

_class_D* new_D(void);

int _D_get_D( _class_D* self) {
    return FALTAIMPLEMENTAR;
}

void _D_init( _class_D* self) {
    FALTAIMPLEMENTAR;
    FALTAIMPLEMENTAR = 4;
}

Func VT_class_D[] = {
    (Func) _D_get_D,
    (Func) _D_init
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

void _Program_run( _class_Program* self) {
    _class_A* _a;
    _class_B* _b;
    _class_C* _c;
    _class_D* _d;
    printf("%s\n", "4 3 2 1");
    _d = new_D();
    _D_init(_d);
    printf("%d", _D_get_D(_d));
    printf("%s", " ");
    _c = _d;
    printf("%d", _C_get_C(_c));
    printf("%s", " ");
    _b = _c;
    printf("%d", _B_get_B(_b));
    printf("%s", " ");
    _a = _b;
    printf("%d", _A_get_A(_a));
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
