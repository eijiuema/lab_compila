import java.util.Scanner;
public class ER_SEM40 {
    public static void main(String args[]) {
        new Program().run();
    }
}

class A {
    private int n;
    void set(int n) {
        this.n = n;
    }
    int get() {
        return this.n;
    }
}

class B extends A {
    @Override
     void set(int pn) {
        System.out.println(pn);
        super.set(pn);
    }
}

class Program {
    void m(B b) {
        b.set(0);
    }
    void run() {
        B a;
        a = new B();
        this.m(a);
    }
}

