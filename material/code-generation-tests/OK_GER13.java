import java.util.Scanner;
public class OK_GER13 {
    public static void main(String args[]) {
        new Program().run();
    }
}

class A {
    private int n;
    void set(int pn/*aa*/) {
        System.out.print(1);
        System.out.print(" ");
        this.n = pn;
    }
    int get() {
        return this.n;
    }
    void print() {
        System.out.print("A ");
    }
    private void p1() {
        System.out.print("999 ");
    }
    private void p2() {
        System.out.print("888 ");
    }
}

class B extends A {
    @Override
     void set(int pn/*aa*/) {
        System.out.print(pn);
        System.out.print(" ");
        super.set(pn);
    }
    void p1() {
        System.out.print(2);
        System.out.print(" ");
    }
    @Override
     void print() {
        System.out.print("B ");
    }
    private void p2() {
    }
}

class Program {
    private Program program;
    void print() {
        System.out.print("P ");
    }
    B m(A a/*aa*/) {
        a.set(0);
        return new B();
    }
    A p(int i/*aa*/) {
        if (i > 0) {
            return new A();
        } else {
            return new B();
        }
    }
    void run() {
        A a, a2;
        B b;
        System.out.println("0 1 0 1 0 1 2 B A 0 1 P");
        a = new A();
        b = new B();
        a = b;
        a.set(0);
        a = this.m(a);
        b = this.m(b);
        b.p1();
        a = this.p(0);
        a.print();
        a = this.p(1);
        a.print();
        a = null;
        b = null;
        a2 = new A();
        if (a == b) {
            System.out.print(0);
            System.out.print(" ");
        }
        if (b == a) {
            System.out.print(1);
            System.out.print(" ");
        }
        if (a == a2) {
            System.out.print(2);
            System.out.print(" ");
        }
        this.program = new Program();
        this.program.print();
    }
}

