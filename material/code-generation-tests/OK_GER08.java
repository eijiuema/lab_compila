import java.util.Scanner;
public class OK_GER08 {
    public static void main(String args[]) {
        new Program().run();
    }
}

class A {
    void m1(int n) {
        System.out.print(1);
        System.out.print(" ");
        System.out.print(n);
        System.out.print(" ");
    }
    void m2(int n) {
        System.out.print(2);
        System.out.print(" ");
        System.out.print(n);
        System.out.print(" ");
    }
    void m3(int n, int p, String q, int r, boolean falseBool) {
        System.out.print(3);
        System.out.print(" ");
        System.out.print(n);
        System.out.print(" ");
        System.out.print(p);
        System.out.print(" ");
        System.out.print(q);
        System.out.print(" ");
        System.out.print(r);
        System.out.print(" ");
        if (falseBool) {
            System.out.print(8 + " ");
        } else {
            System.out.print(7 + " ");
        }
    }
}

class Program {
    void run() {
        A a;
        System.out.println("1 1 2 2 3 3 4 5 6 7");
        a = new A();
        a.m1(1);
        a.m2(2);
        a.m3(3, 4, "5", 6, false);
    }
}

