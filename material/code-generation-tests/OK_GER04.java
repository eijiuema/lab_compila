import java.util.Scanner;
public class OK_GER04 {
    public static void main(String args[]) {
        new Program().run();
    }
}

class A {
    void m() {
        int i;
        boolean b;
        System.out.print(6);
        System.out.print(" ");
        i = 1;
        while(i <= 5) {
            System.out.print(i);
            System.out.print(" ");
            i = i + 1;
        }
        b = false;
        while(b != true) {
            System.out.print(6);
            System.out.print(" ");
            b = ! b;
        }
    }
}

class Program {
    void run() {
        A a;
        System.out.println("6 1 2 3 4 5 6");
        a = new A();
        a.m();
    }
}

