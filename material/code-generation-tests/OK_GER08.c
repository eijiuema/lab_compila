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

void _A_m1( _class_A *self, int _n);

void _A_m2( _class_A *self, int _n);

void _A_m3( _class_A *self, int _n, int _p, char * _q, int _r, boolean _falseBool);

void _A_m1( _class_A *self, int _n) {
    printf("%d", 1);
    printf("%s", " ");
    printf("%d", _n);
    printf("%s", " ");
}

void _A_m2( _class_A *self, int _n) {
    printf("%d", 2);
    printf("%s", " ");
    printf("%d", _n);
    printf("%s", " ");
}

void _A_m3( _class_A *self, int _n, int _p, char * _q, int _r, boolean _falseBool) {
    printf("%d", 3);
    printf("%s", " ");
    printf("%d", _n);
    printf("%s", " ");
    printf("%d", _p);
    printf("%s", " ");
    printf("%s", _q);
    printf("%s", " ");
    printf("%d", _r);
    printf("%s", " ");
    if (_falseBool ) {
        printf("%s",  concat(  intToStr(8), " "));
    } else {
        printf("%s",  concat(  intToStr(7), " "));
    }
}

Func VT_class_A[] = {
    (void (*) () ) _A_m1,
    (void (*) () ) _A_m2,
    (void (*) () ) _A_m3
};

_class_A* new_A(){
    _class_A* t;
    if ( (t = malloc(sizeof(_class_A))) != NULL )
        t->vt = VT_class_A;
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
    printf("%s\n", "1 1 2 2 3 3 4 5 6 7");
    _a = new_A();
    ((void(*)( _class_A *, int ))_a->vt[0] )(_a, 1);
    ((void(*)( _class_A *, int ))_a->vt[1] )(_a, 2);
    ((void(*)( _class_A *, int , int , char * , int , boolean ))_a->vt[2] )(_a, 3, 4, "5", 6, false);
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

