import java.util.Scanner;
public class OK_GER01 {
    public static void main(String args[]) {
        new Program().run();
    }
}

class A {
    public void m() {
        System.out.print(7);
        System.out.print(" ");
        if ((1 > 0)) {
            System.out.print(0);
            System.out.print(" ");
        }
        if ((1 >= 0)) {
            System.out.print(1);
            System.out.print(" ");
        }
        if ((1 != 0)) {
            System.out.print(2);
            System.out.print(" ");
        }
        if ((0 < 1)) {
            System.out.print(3);
            System.out.print(" ");
        }
        if ((0 <= 1)) {
            System.out.print(4);
            System.out.print(" ");
        }
        if ((0 == 0)) {
            System.out.print(5);
            System.out.print(" ");
        }
        if ((0 >= 0)) {
            System.out.print(6);
            System.out.print(" ");
        }
        if ((0 <= 0)) {
            System.out.print(7);
            System.out.print(" ");
        }
        if ((1 == 0)) {
            System.out.print(18);
            System.out.print(" ");
        }
        if ((0 > 1)) {
            System.out.print(10);
            System.out.print(" ");
        }
        if ((0 >= 1)) {
            System.out.print(11);
            System.out.print(" ");
        }
        if ((0 != 0)) {
            System.out.print(12);
            System.out.print(" ");
        }
        if ((1 < 0)) {
            System.out.print(13);
            System.out.print(" ");
        }
        if ((1 <= 0)) {
            System.out.print(14);
            System.out.print(" ");
        }
    }
}

class Program {
    public void run() {
        A a;
        System.out.println("7 0 1 2 3 4 5 6 7");
        a = new A();
        a.m();
    }
}

