import java.util.Scanner;
public class OK_SEM12 {
    public static void main(String args[]) {
        new Program().run();
    }
}

class A {
    void m() {
    }
}

class B extends A {
    public void p() {
        B A;
        A = new B();
        A.m();
    }
}

class Program {
    void run() {
        A a;
        B b;
        a = new A();
        b = new B();
        a.m();
        b.p();
    }
}

