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

int _A_calculaNota( _class_A *self, int _ntrab, int _nprov);

int _A_calculaNotaB( _class_A *self, int _ntrab, int _nprov);

int _A_calculaNota( _class_A *self, int _ntrab, int _nprov) {
    int _ans;
    _ans = 11;
    if (_ntrab >= 6 ) {
        if (_nprov >= 6 ) {
            _ans = _ntrab + _nprov;
            _ans = _ans / 2;
            return (int) _ans;
        }
    }
    return (int) _ans;
}

int _A_calculaNotaB( _class_A *self, int _ntrab, int _nprov) {
    int _ans;
    _ans = ( 2 * _ntrab + _nprov ) / 3;
    return (int) _ans;
}

Func VT_class_A[] = {
    (void (*) () ) _A_calculaNota,
    (void (*) () ) _A_calculaNotaB
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

int _B_calculaNotaB( _class_B *self, int _ntrab, int _nprov);

int _B_calculaNotaB( _class_B *self, int _ntrab, int _nprov) {
    if (_ntrab < _nprov ) {
        return (int) _ntrab;
    } else {
        return (int) _nprov;
    }
}

Func VT_class_B[] = {
    (void (*) () ) _A_calculaNota,
    (void (*) () ) _B_calculaNotaB
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
    int _ans;
    int _ntrab;
    int _nprov;
    printf("%s\n", "");
    printf("%s\n", "Ok-fib");
    printf("%s\n", "The output should be :");
    printf("%s\n", "0 <= x <= 10");
    _a = new_A();
    _nprov = 10;
    _ntrab = 10;
    _ans = ((int(*)( _class_A *, int , int ))_a->vt[0] )(_a, _ntrab, _nprov);
    if (_ans != 11 ) {
        printf("%d\n", _ans);
    } else {
        if (( _ans == 11 ) && ( _nprov < 6 ) ) {
            _a = (_class_A*) new_B();
            printf("%d\n", ( ((int(*)( _class_A *, int , int ))_a->vt[1] )(_a, _ntrab, _nprov) ));
        } else {
            printf("%d\n", ( ((int(*)( _class_A *, int , int ))_a->vt[1] )(_a, _ntrab, _nprov) ));
        }
    }
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

