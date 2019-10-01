import java.util.Scanner;
public class OK_GER12 {
    public static void main(String args[]) {
        new Program().run();
    }
}

class A {
    void m1() {
        System.out.print(1);
        System.out.print(" ");
    }
    void m2(int n) {
        System.out.print(n);
        System.out.print(" ");
    }
}

class B {
    override void m2(int n) {
        System.out.print(n);
        System.out.print(" ");
        super.m2(n + 1);
    }
}

