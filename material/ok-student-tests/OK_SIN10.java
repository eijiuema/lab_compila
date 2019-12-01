import java.util.Scanner;
public class OK_SIN10 {
    public static void main(String args[]) {
        new Program().run();
    }
}

class A {
    private int n;
    public int get() {
        return this.n;
    }
    public void set(int pn/*aa*/) {
        this.n = pn;
    }
}

class B extends A {
    private int k;
    public void m() {
        int i;
        i = 1;
        this.k = 2;
        super.set(0);
        System.out.println(super.get());
        System.out.println(this.get());
        System.out.println(this.k);
        System.out.println(i);
    }
    public void print() {
        System.out.println(this.k);
    }
}

class Program {
    void run() {
        B b;
        b = new B();
        b.set(1);
        b.m();
    }
}

