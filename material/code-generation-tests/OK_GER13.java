import java.util.Scanner;
public class OK_GER13 {
    public static void main(String args[]) {
        new Program().run();
    }
}

class A {
    private int n;
    void set(int pn) {
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
    override void set(int pn) {
        System.out.print(pn);
        System.out.print(" ");
        super.set(pn);
    }
    void p1() {
        System.out.print(2);
        System.out.print(" ");
    }
    override void print() {
        System.out.print("B ");
    }
    private void p2() {
    }
}

