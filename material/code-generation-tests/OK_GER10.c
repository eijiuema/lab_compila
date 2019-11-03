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
    int _k;
    Func* vt;
}_class_A;

_class_A* new_A(void);

void _A_m1( _class_A *self, int _n) {
    FALTAIMPLEMENTAR = 1;
    printf("%s",  concat(  intToStr(FALTAIMPLEMENTAR),  concat( " ",  concat(  intToStr(_n), " "))));
}

int _A_getK( _class_A *self) {
    return (int) FALTAIMPLEMENTAR;
}

Func VT_class_A[] = {
    (Func) _A_m1,
    (Func) _A_getK};

_class_A* new_A(){
    _class_A* t;
    if ( (t = malloc(sizeof(_class_A))) != NULL )
        t->vt = VT_class_A;
    return t;
}

// Codigo da classe _class_B
typedef struct _St_B {
    int _k;
    Func* vt;
}_class_B;

_class_B* new_B(void);

void _B_m2( _class_B *self, int _n) {
    FALTAIMPLEMENTAR = 2;
    FALTAIMPLEMENTAR;
    printf("%s",  concat(  intToStr(FALTAIMPLEMENTAR),  concat( " ",  concat(  intToStr(_n), " "))));
}

int _B_getK( _class_B *self) {
    return (int) FALTAIMPLEMENTAR;
}

Func VT_class_B[] = {
    (Func) _A_m1,
    (Func) _A_getK,
    (Func) _B_m2,
    (Func) _B_getK};

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

void _C_m3( _class_C *self, int _n) {
    FALTAIMPLEMENTAR;
    printf("%s",  concat( "3 ",  concat(  intToStr(_n), " ")));
}

void _C_m4( _class_C *self, int _n) {
    FALTAIMPLEMENTAR;
    printf("%s",  concat( "4 ",  intToStr(_n)));
    printf("%s", " ");
}

Func VT_class_C[] = {
    (Func) _A_m1,
    (Func) _A_getK,
    (Func) _B_m2,
    (Func) _B_getK,
    (Func) _C_m3,
    (Func) _C_m4};

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

void _Program_run( _class_Program *self) {
    _class_C *_c;
    printf("%s\n", "1 1 2 2 3 3 4 4");
    _c = new_C();
    (_c->vt[5] )(_c, 4);
}

Func VT_class_Program[] = {
    (Func) _Program_run};

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

