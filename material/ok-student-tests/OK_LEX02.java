import java.util.Scanner;
public class OK_LEX02 {
    public static void main(String args[]) {
        new Program().run();
    }
}

class A {
    void m() {
        int i;
        i = 1;
        System.out.print(i);
    }
}

class Program {
    void run() {
        A a;
        a = new A();
        a.m();
    }
}

