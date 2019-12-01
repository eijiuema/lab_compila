import java.util.Scanner;
public class OK_GER06 {
    public static void main(String args[]) {
        new Program().run();
    }
}

class A {
    void m() {
        int i, j, k;
        System.out.print(7);
        System.out.print(" ");
        i = 1;
        j = i + 1;
        k = j + 1;
        System.out.print(i);
        System.out.print(" ");
        System.out.print(j);
        System.out.print(" ");
        System.out.print(k);
        System.out.print(" ");
        i = ((((3 + 1) * 3) / 2) / 2) + 1;
        System.out.print(i);
        System.out.print(" ");
        i = ((100 - 95) * 2) - 5;
        System.out.print(i);
        System.out.print(" ");
        i = (100 - (45 * 2)) - 4;
        System.out.print(i);
        System.out.print(" ");
        System.out.print(7);
        System.out.print(" ");
    }
}

class Program {
    void run() {
        A a;
        System.out.println("7 1 2 3 4 5 6 7");
        a = new A();
        a.m();
    }
}

