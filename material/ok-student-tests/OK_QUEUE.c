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

// Codigo da classe _class_Node
typedef struct _St_Node {
    Func* vt;
    int _class_Node_number;
    struct _St_Node *_class_Node_next;
}_class_Node;

_class_Node* new_Node(void);

void _Node_setNumber( _class_Node *self, int _number);

int _Node_getNumber( _class_Node *self);

void _Node_setNext( _class_Node *self, _class_Node *_next);

_class_Node* _Node_getNext( _class_Node *self);

void _Node_print( _class_Node *self);

void _Node_setNumber( _class_Node *self, int _number) {
    self->_class_Node_number = _number;
}

int _Node_getNumber( _class_Node *self) {
    return (int) self->_class_Node_number;
}

void _Node_setNext( _class_Node *self, _class_Node *_next) {
    self->_class_Node_next = _next;
}

_class_Node* _Node_getNext( _class_Node *self) {
    return (_class_Node* ) self->_class_Node_next;
}

void _Node_print( _class_Node *self) {
    printf("%s",  concat(  intToStr(self->_class_Node_number), " "));
    if (self->_class_Node_next != (_class_Node*) NULL ) {
        ( (void(*)( _class_Node *))self->_class_Node_next->vt[4] )(self->_class_Node_next);
    }
}

Func VT_class_Node[] = {
    (void (*) () ) _Node_setNumber,
    (void (*) () ) _Node_getNumber,
    (void (*) () ) _Node_setNext,
    (void (*) () ) _Node_getNext,
    (void (*) () ) _Node_print
};

_class_Node* new_Node(){
    _class_Node* t;
    if ( (t = malloc(sizeof(_class_Node))) != NULL )
        t->vt = VT_class_Node;
    return t;
}

// Codigo da classe _class_Head
typedef struct _St_Head {
    Func* vt;
    struct _St_Node *_class_Head_end2;
    struct _St_Node *_class_Head_first;
    int _class_Head_nElements;
}_class_Head;

_class_Head* new_Head(void);

void _Head_init( _class_Head *self);

void _Head_print( _class_Head *self);

int _Head_getNElements( _class_Head *self);

void _Head_insert( _class_Head *self, int _num);

void _Head_init( _class_Head *self) {
    self->_class_Head_end2 = NULL;
    self->_class_Head_first = NULL;
    self->_class_Head_nElements = 0;
}

void _Head_print( _class_Head *self) {
    if (self->_class_Head_nElements != 0 ) {
        ( (void(*)( _class_Node *))self->_class_Head_first->vt[4] )(self->_class_Head_first);
    }
}

int _Head_getNElements( _class_Head *self) {
    return (int) self->_class_Head_nElements;
}

void _Head_insert( _class_Head *self, int _num) {
    self->_class_Head_nElements = self->_class_Head_nElements + 1;
    _class_Node *_aux;
    _aux = new_Node();
    ((void(*)( _class_Node *, int ))_aux->vt[0] )(_aux, _num);
    ((void(*)( _class_Node *, _class_Node *))_aux->vt[2] )(_aux, NULL);
    if (( self->_class_Head_first == (_class_Node*) NULL ) && ( self->_class_Head_end2 == (_class_Node*) NULL ) ) {
        self->_class_Head_first = _aux;
    } else {
        _Node_setNext(self->_class_Head_end2, _aux);
    }
    self->_class_Head_end2 = _aux;
}

Func VT_class_Head[] = {
    (void (*) () ) _Head_init,
    (void (*) () ) _Head_print,
    (void (*) () ) _Head_getNElements,
    (void (*) () ) _Head_insert
};

_class_Head* new_Head(){
    _class_Head* t;
    if ( (t = malloc(sizeof(_class_Head))) != NULL )
        t->vt = VT_class_Head;
    return t;
}

// Codigo da classe _class_Program
typedef struct _St_Program {
    Func* vt;
}_class_Program;

_class_Program* new_Program(void);

void _Program_run( _class_Program *self);

void _Program_run( _class_Program *self) {
    _class_Head *_head;
    int _max;
    int _aux;
    _aux = 0;
    _max = 10;
    printf("%s\n", "");
    printf("%s\n", "Ok-queue");
    printf("%s\n", "The output should be :");
    printf("%s\n", "The values of queue");
    _head = new_Head();
    ((void(*)( _class_Head *))_head->vt[0] )(_head);
    while( ((int(*)( _class_Head *))_head->vt[2] )(_head) < _max ) {
        ((void(*)( _class_Head *, int ))_head->vt[3] )(_head, _aux);
    }
    ((void(*)( _class_Head *))_head->vt[1] )(_head);
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

