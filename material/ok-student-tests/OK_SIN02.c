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

void _A_m( _class_A *self);

void _A_m( _class_A *self) {
    int _i;
    int _j;
    boolean _b;
    _b = false;
    if (_b ) {
    }
    while( _b ) {
        ;    }
    while( ( _b ) ) {
    }
    _i = ( ( 1 * 4 ) + 3 ) - ( 5 / 2 );
    _b = false;
    if (( ( ! _b && ( _i < 0 ) ) || ( _i > 10 ) ) ) {
        _b = true;
        _i = -1;
        _i = readInt();
        _j = readInt();
        while( _i > 0 ) {
            printf("%d\n", _i);
            _i = _i - 1;
        }
    } else {
        _b = true;
        printf("%d\n", _i);
    }
    if (( ( ( ( ( ( _i == 1 ) || ( _i < 1 ) ) || ( _i <= 5 ) ) || ( _i > 1 ) ) || ( _i >= 1 ) ) || ( _i != 3 ) ) ) {
        _i = 0;
    }
}

Func VT_class_A[] = {
    (void (*) () ) _A_m
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
    _a = new_A();
    ((void(*)( _class_A *))_a->vt[0] )(_a);
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

