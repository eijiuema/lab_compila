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
    int _class_A_n;
}_class_A;

_class_A* new_A(void);

void _A_set( _class_A *self, int _n);

int _A_get( _class_A *self);

void _A_set( _class_A *self, int _n) {
    self->_class_A_n = _n;
}

int _A_get( _class_A *self) {
    return (int) self->_class_A_n;
}

Func VT_class_A[] = {
    (void (*) () ) _A_set,
    (void (*) () ) _A_get
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
    struct _St_A *_class_Program_a;
}_class_Program;

_class_Program* new_Program(void);

void _Program_set( _class_Program *self, _class_A *_a);

void _Program_print( _class_Program *self);

_class_A* _Program_get( _class_Program *self);

void _Program_run( _class_Program *self);

void _Program_set( _class_Program *self, _class_A *_a) {
    self->_class_Program_a = _a;
}

void _Program_print( _class_Program *self) {
    printf("%d", ( (int(*)( _class_A *))self->_class_Program_a->vt[1] )((_class_A*)self->_class_Program_a));
}

_class_A* _Program_get( _class_Program *self) {
    return (_class_A* ) self->_class_Program_a;
}

void _Program_run( _class_Program *self) {
    printf("%s\n", "0");
    printf("%s\n", "0");
}

Func VT_class_Program[] = {
    (void (*) () ) _Program_print,
    (void (*) () ) _Program_get,
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

