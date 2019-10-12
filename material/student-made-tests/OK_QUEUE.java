import java.util.Scanner;
public class OK_QUEUE {
    public static void main(String args[]) {
        new Program().run();
    }
}

class Node {
    private int number;
    private Node next;
    void setNumber(int number) {
        this.number = number;
    }
    int getNumber() {
        return this.number;
    }
    void setNext(Node next) {
        this.next = next;
    }
    Node getNext() {
        return this.next;
    }
    void print() {
        System.out.print(this.number + " ");
        if (this.next != null) {
            this.next.print();
        }
    }
}

class Head {
    private Node end2;
    private Node first;
    private int nElements;
    void init() {
        this.end2 = null;
        this.first = null;
        this.nElements = 0;
    }
    void print() {
        if (this.nElements != 0) {
            this.first.print();
        }
    }
    int getNElements() {
        return this.nElements;
    }
    void insert(int num) {
        this.nElements = this.nElements + 1;
        Node aux;
        aux = new Node();
        aux.setNumber(num);
        aux.setNext(null);
        if ((this.first == null) && (this.end2 == null)) {
            this.first = aux;
        } else {
            this.end2.setNext(aux);
        }
        this.end2 = aux;
    }
}

class Program {
    void run() {
        Head head;
        int max;
        int aux;
        aux = 0;
        max = 10;
        System.out.println("");
        System.out.println("Ok-queue");
        System.out.println("The output should be :");
        System.out.println("The values of queue");
        head = new Head();
        head.init();
        while(head.getNElements() < max) {
            head.insert(aux);
        }
        head.print();
    }
}

