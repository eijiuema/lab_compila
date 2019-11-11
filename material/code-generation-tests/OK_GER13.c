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
    int _class_A_n;
    Func* vt;
}_class_A;

_class_A* new_A(void);

void _A_p1( _class_A *self);

void _A_p2( _class_A *self);

void _A_set( _class_A *self, int _pn);

int _A_get( _class_A *self);

void _A_print( _class_A *self);

void _A_p1( _class_A *self) {
    printf("%s", "999 ");
}

void _A_p2( _class_A *self) {
    printf("%s", "888 ");
}

void _A_set( _class_A *self, int _pn) {
    printf("%d", 1);
    printf("%s", " ");
    self->_class_A_n = _pn;
}

int _A_get( _class_A *self) {
    return (int) self->_class_A_n;
}

void _A_print( _class_A *self) {
    printf("%s", "A ");
}

Func VT_class_A[] = {
    (void(*)( _class_A, int )) _A_set,
    (int(*)( _class_A)) _A_get,
    (void(*)( _class_A)) _A_print
};

_class_A* new_A(){
    _class_A* t;
    if ( (t = malloc(sizeof(_class_A))) != NULL )
        t->vt = VT_class_A;
    return t;
}

// Codigo da classe _class_B
typedef struct _St_B {
    int _class_A_n;
    Func* vt;
}_class_B;

_class_B* new_B(void);

void _B_p2( _class_B *self);

void _B_set( _class_B *self, int _pn);

void _B_p1( _class_B *self);

void _B_print( _class_B *self);

void _B_p2( _class_B *self) {
}

void _B_set( _class_B *self, int _pn) {
    printf("%d", _pn);
    printf("%s", " ");
    ( (void(*)())((_class_A*)self)->vt[0] )((_class_A*) self, _pn);
}

void _B_p1( _class_B *self) {
    printf("%d", 2);
    printf("%s", " ");
}

void _B_print( _class_B *self) {
    printf("%s", "B ");
}

Func VT_class_B[] = {
