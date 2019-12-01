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
    int _class_A_num1;
    int _class_A_num2;
    int _class_A_result;
}_class_A;

_class_A* new_A(void);

int _A_mult( _class_A *self);

void _A_div( _class_A *self, int _num3, int _num4);

int _A_mult( _class_A *self) {
    return (int) self->_class_A_num1 * self->_class_A_num2;
}

void _A_div( _class_A *self, int _num3, int _num4) {
    self->_class_A_result = _num3 / _num4;
}

Func VT_class_A[] = {
    (void (*) () ) _A_mult,
    (void (*) () ) _A_div
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
    char * _class_B_name;
}_class_B;

_class_B* new_B(void);

void _B_setName( _class_B *self, char * _name);

char * _B_getName( _class_B *self);

void _B_setName( _class_B *self, char * _name) {
    self->_class_B_name = _name;
}

char * _B_getName( _class_B *self) {
    return (char *) self->_class_B_name;
}

Func VT_class_B[] = {
    (void (*) () ) _B_setName,
    (void (*) () ) _B_getName
};

_class_B* new_B(){
    _class_B* t;
    if ( (t = malloc(sizeof(_class_B))) != NULL )
        t->vt = VT_class_B;
    return t;
}

int main(void) {
    _class_Program* program;
    program = new_Program();
    _Program_run(program);
    return 0;
}

