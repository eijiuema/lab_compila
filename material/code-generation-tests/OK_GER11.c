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

void _A_m1( _class_A *self);

void _A_m2( _class_A *self, int _n);

void _A_m1( _class_A *self) {
    printf("%s", " 2 ");
}

void _A_m2( _class_A *self, int _n) {
    printf("%d", _n);
    printf("%s", " ");
    ( (void(*)())self->vt[0] )(self);
}

Func VT_class_A[] = {
    (void(*)( _class_A)) _A_m1,
    (void(*)( _class_A, int )) _A_m2
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

void _B_m1( _class_B *self);

void _B_m1( _class_B *self) {
    printf("%s\n", " 4 ");
}

Func VT_class_B[] = {
    (void(*)( _class_B)) _B_m1,
    (void(*)( _class_A, int )) _A_m2
};

_class_B* new_B(){
    _class_B* t;
    if ( (t = malloc(sizeof(_class_B))) != NULL )
        t->vt = VT_class_B;
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
    printf("%s\n", "4 1 2 3 4");
    printf("%s", "4 ");
    _a = new_A();
    ( (void(*)())_a->vt[1] )(_a, 1);
    _a = (_class_A*) new_B();
    ( (void(*)())_a->vt[1] )(_a, 3);
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

