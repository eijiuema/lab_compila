import java.util.Scanner;
public class OK_GER09 {
    public static void main(String args[]) {
        new Program().run();
    }
}

class A {
    void m1(int n) {
        System.out.print("1 " + n);
        System.out.print(" ");
    }
}

class B extends A {
    void m2(int n) {
        super.m1(1);
        System.out.print(" 2 " + n);
        System.out.print(" ");
    }
}

class C extends B {
    void m3(int n) {
        super.m2(2);
        System.out.print(" 3 " + n);
        System.out.print(" ");
    }
    void m4(int n) {
        this.m3(3);
        System.out.println(" 4 " + n);
        System.out.println(" ");
    }
}

class Program {
    void run() {
        C c;
        System.out.println("1 1 2 2 3 3 4 4");
        c = new C();
        c.m4(4);
    }
}

