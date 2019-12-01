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

// Codigo da classe _class_Person
typedef struct _St_Person {
    Func* vt;
    char * _class_Person_course;
    int _class_Person_number;
    int _class_Person_age;
    char * _class_Person_name;
}_class_Person;

_class_Person* new_Person(void);

char * _Person_getCourse( _class_Person *self);

void _Person_setCourse( _class_Person *self, char * _course);

int _Person_getNumber( _class_Person *self);

void _Person_setNumber( _class_Person *self, int _number);

void _Person_init( _class_Person *self, char * _name, int _age);

char * _Person_getName( _class_Person *self);

int _Person_getAge( _class_Person *self);

void _Person_print( _class_Person *self);

char * _Person_getCourse( _class_Person *self) {
    return (char *) self->_class_Person_course;
}

void _Person_setCourse( _class_Person *self, char * _course) {
    self->_class_Person_course = _course;
}

int _Person_getNumber( _class_Person *self) {
    return (int) self->_class_Person_number;
}

void _Person_setNumber( _class_Person *self, int _number) {
    self->_class_Person_number = _number;
}

void _Person_init( _class_Person *self, char * _name, int _age) {
    self->_class_Person_name = _name;
    self->_class_Person_age = _age;
}

char * _Person_getName( _class_Person *self) {
    return (char *) self->_class_Person_name;
}

int _Person_getAge( _class_Person *self) {
    return (int) self->_class_Person_age;
}

void _Person_print( _class_Person *self) {
    printf("%s\n",  concat( "Name = ", self->_class_Person_name));
    printf("%s\n",  concat( "Age = ",  intToStr(self->_class_Person_age)));
}

Func VT_class_Person[] = {
    (void (*) () ) _Person_getCourse,
    (void (*) () ) _Person_setCourse,
    (void (*) () ) _Person_getNumber,
    (void (*) () ) _Person_setNumber,
    (void (*) () ) _Person_init,
    (void (*) () ) _Person_getName,
    (void (*) () ) _Person_getAge,
    (void (*) () ) _Person_print
};

_class_Person* new_Person(){
    _class_Person* t;
    if ( (t = malloc(sizeof(_class_Person))) != NULL )
        t->vt = VT_class_Person;
    return t;
}

// Codigo da classe _class_Group
typedef struct _St_Group {
    Func* vt;
    struct _St_Person *_class_Group_first;
    struct _St_Person *_class_Group_second;
}_class_Group;

_class_Group* new_Group(void);

void _Group_set( _class_Group *self, _class_Person *_first, _class_Person *_second);

_class_Person* _Group_getFirst( _class_Group *self);

_class_Person* _Group_getSecond( _class_Group *self);

void _Group_print( _class_Group *self);

void _Group_set( _class_Group *self, _class_Person *_first, _class_Person *_second) {
    self->_class_Group_first = _first;
    self->_class_Group_second = _second;
}

_class_Person* _Group_getFirst( _class_Group *self) {
    return (_class_Person* ) self->_class_Group_first;
}

_class_Person* _Group_getSecond( _class_Group *self) {
    return (_class_Person* ) self->_class_Group_second;
}

void _Group_print( _class_Group *self) {
    printf("%s\n",  concat( "First: ", ( (char *(*)( _class_Person *))self->_class_Group_first->vt[5] )(self->_class_Group_first)));
    printf("%s\n",  concat( "Second: ", ( (char *(*)( _class_Person *))self->_class_Group_second->vt[5] )(self->_class_Group_second)));
}

Func VT_class_Group[] = {
    (void (*) () ) _Group_set,
    (void (*) () ) _Group_getFirst,
    (void (*) () ) _Group_getSecond,
    (void (*) () ) _Group_print
};

_class_Group* new_Group(){
    _class_Group* t;
    if ( (t = malloc(sizeof(_class_Group))) != NULL )
        t->vt = VT_class_Group;
    return t;
}

// Codigo da classe _class_University
typedef struct _St_University {
    Func* vt;
    char * _class_University_name;
    int _class_University_numberOfStudents;
    char * _class_University_city;
}_class_University;

_class_University* new_University(void);

void _University_init( _class_University *self, char * _name, char * _city, int _numberOfStudents);

void _University_print( _class_University *self);

void _University_init( _class_University *self, char * _name, char * _city, int _numberOfStudents) {
    self->_class_University_name = _name;
    self->_class_University_city = _city;
    self->_class_University_numberOfStudents = _numberOfStudents;
}

void _University_print( _class_University *self) {
    printf("%s\n", self->_class_University_name);
    printf("%s\n", self->_class_University_city);
    printf("%d\n", self->_class_University_numberOfStudents);
}

Func VT_class_University[] = {
    (void (*) () ) _University_init,
    (void (*) () ) _University_print
};

_class_University* new_University(){
    _class_University* t;
    if ( (t = malloc(sizeof(_class_University))) != NULL )
        t->vt = VT_class_University;
    return t;
}

// Codigo da classe _class_Program
typedef struct _St_Program {
    Func* vt;
}_class_Program;

_class_Program* new_Program(void);

void _Program_run( _class_Program *self);

void _Program_run( _class_Program *self) {
    _class_University *_s;
    _class_Person *_joao;
    _class_Person *_maria;
    _class_Group *_g;
    _s = new_University();
    ((void(*)( _class_University *, char * , char * , int ))_s->vt[0] )(_s, "UFSCar", "Sao Carlos", 7000);
    ((void(*)( _class_University *))_s->vt[1] )(_s);
    printf("%s\n", "");
    _joao = new_Person();
    ((void(*)( _class_Person *, char * , int ))_joao->vt[4] )(_joao, "Joao", 21);
    ((void(*)( _class_Person *, char * ))_joao->vt[1] )(_joao, "EnC");
    ((void(*)( _class_Person *, int ))_joao->vt[3] )(_joao, 6729);
    _maria = new_Person();
    ((void(*)( _class_Person *, char * , int ))_maria->vt[4] )(_maria, "Maria", 20);
    ((void(*)( _class_Person *, char * ))_maria->vt[1] )(_maria, "Fisioterapia");
    ((void(*)( _class_Person *, int ))_maria->vt[3] )(_maria, 8607);
    ((void(*)( _class_Person *))_joao->vt[7] )(_joao);
    printf("%s\n", "");
    ((void(*)( _class_Person *))_maria->vt[7] )(_maria);
    printf("%s\n", "");
    _g = new_Group();
    ((void(*)( _class_Group *, _class_Person *, _class_Person *))_g->vt[0] )(_g, _joao, _maria);
    ((void(*)( _class_Group *))_g->vt[3] )(_g);
    printf("%s\n", "");
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

