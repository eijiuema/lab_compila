import java.util.Scanner;
public class OK_SEM15 {
    public static void main(String args[]) {
        new Program().run();
    }
}

class A {
    void print() {
        System.out.println(0);
    }
    void accept(A x) {
        x.print();
    }
}

class B extends A {
    void m() {
        super.accept(this);
    }
}

class Program {
    void run() {
        B b;
        b = new B();
        b.m();
    }
}

