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

// Codigo da classe _class_Point
typedef struct _St_Point {
    Func* vt;
    int _class_Point_x;
    int _class_Point_y;
}_class_Point;

_class_Point* new_Point(void);

void _Point_set( _class_Point *self, int _x, int _y);

int _Point_getX( _class_Point *self);

int _Point_getY( _class_Point *self);

void _Point_set( _class_Point *self, int _x, int _y) {
    self->_class_Point_x = _x;
    self->_class_Point_y = _y;
}

int _Point_getX( _class_Point *self) {
    return (int) self->_class_Point_x;
}

int _Point_getY( _class_Point *self) {
    return (int) self->_class_Point_y;
}

Func VT_class_Point[] = {
    (void (*) () ) _Point_set,
    (void (*) () ) _Point_getX,
    (void (*) () ) _Point_getY
};

_class_Point* new_Point(){
    _class_Point* t;
    if ( (t = malloc(sizeof(_class_Point))) != NULL )
        t->vt = VT_class_Point;
    return t;
}

// Codigo da classe _class_Program
typedef struct _St_Program {
    Func* vt;
    struct _St_Point *_class_Program_p;
}_class_Program;

_class_Program* new_Program(void);

void _Program_run( _class_Program *self);

_class_Point* _Program_getPoint( _class_Program *self);

void _Program_run( _class_Program *self) {
    self->_class_Program_p = new_Point();
    _Point_set(self->_class_Program_p, 0, 0);
}

_class_Point* _Program_getPoint( _class_Program *self) {
    return (_class_Point* ) self->_class_Program_p;
}

Func VT_class_Program[] = {
    (void (*) () ) _Program_run,
    (void (*) () ) _Program_getPoint
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

