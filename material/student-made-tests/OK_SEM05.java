import java.util.Scanner;
public class OK_SEM05 {
    public static void main(String args[]) {
        new Program().run();
    }
}

class A {
    private int n;
    void set(int pn) {
        this.n = pn;
    }
    int get() {
        return this.n;
    }
}

class B extends A {
    @Override
     void set(int pn) {
        System.out.print(pn);
        super.set(pn);
    }
}

class Program {
    B m(A a) {
        a.set(0);
        return new B();
    }
    A p(int i) {
        if (i > 0) {
            return new A();
        } else {
            return new B();
        }
    }
    void run() {
        A a;
        B b;
        a = new A();
        b = new B();
        a = b;
        a.set(0);
        a = this.m(a);
        b = this.m(b);
        a = this.p(0);
    }
}

