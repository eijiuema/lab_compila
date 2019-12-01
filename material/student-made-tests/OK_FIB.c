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

int _A_fib( _class_A *self, int _n);

int _A_fib( _class_A *self, int _n) {
    _class_A *_a;
    int _left;
    int _right;
    if (_n < 2 ) {
        return (int) _n;
    } else {
        _a = new_A();
        _left = ((int(*)( _class_A *, int ))_a->vt[0] )(_a, _n - 1);
        _right = ((int(*)( _class_A *, int ))_a->vt[0] )(_a, _n - 2);
        return (int) _left + _right;
    }
}

Func VT_class_A[] = {
    (void (*) () ) _A_fib
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
    printf("%s\n", "");
    printf("%s\n", "Ok-fib");
    printf("%s\n", "The output should be :");
    printf("%s\n", "34");
    _a = new_A();
    printf("%d\n", ( ((int(*)( _class_A *, int ))_a->vt[0] )(_a, 10) ));
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

