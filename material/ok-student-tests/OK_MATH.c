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

int _A_sum( _class_A *self, int _n1, int _n2);

int _A_sub( _class_A *self, int _n1, int _n2);

int _A_mult( _class_A *self, int _n1, int _n2);

int _A_div( _class_A *self, int _n1, int _n2);

int _A_pow2( _class_A *self, int _n1);

int _A_sum( _class_A *self, int _n1, int _n2) {
    return (int) _n1 + _n2;
}

int _A_sub( _class_A *self, int _n1, int _n2) {
    return (int) ( _n1 - _n2 );
}

int _A_mult( _class_A *self, int _n1, int _n2) {
    return (int) ( _n1 * _n2 );
}

int _A_div( _class_A *self, int _n1, int _n2) {
    if (_n2 == 0 ) {
        return (int) -1;
    } else {
        return (int) ( _n1 / _n2 );
    }
}

int _A_pow2( _class_A *self, int _n1) {
    return (int) ( _n1 + _n1 );
}

Func VT_class_A[] = {
    (void (*) () ) _A_sum,
    (void (*) () ) _A_sub,
    (void (*) () ) _A_mult,
    (void (*) () ) _A_div,
    (void (*) () ) _A_pow2
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
    int _n1;
    int _n2;
    printf("%s\n", "");
    _a = new_A();
    printf("%s\n", "Ok-math");
    _n1 = 1;
    _n2 = 2;
    printf("%d\n", ((int(*)( _class_A *, int , int ))_a->vt[0] )(_a, _n1, _n2));
    printf("%d\n", ((int(*)( _class_A *, int , int ))_a->vt[1] )(_a, _n1, _n2));
    printf("%d\n", ((int(*)( _class_A *, int , int ))_a->vt[2] )(_a, _n1, _n2));
    printf("%d\n", ((int(*)( _class_A *, int , int ))_a->vt[3] )(_a, _n1, _n2));
    printf("%d\n", ((int(*)( _class_A *, int ))_a->vt[4] )(_a, _n1));
    printf("%d\n", ((int(*)( _class_A *, int ))_a->vt[4] )(_a, _n2));
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

