import java.util.Scanner;
public class OK_GER05 {
    public static void main(String args[]) {
        new Program().run();
    }
}

class A {
    void m() {
        int a, b, c, d, e, f;
        a = 1;
        b = 1;
        c = 1;
        d = 1;
        e = 1;
        f = 1;
        System.out.print(a);
        System.out.print(b);
        System.out.print(c);
        System.out.print(d);
        System.out.print(e);
        System.out.print(f);
    }
}

class Program {
    void run() {
        A a;
        System.out.println("");
        System.out.println("Ok-ger05");
        System.out.println("The output should be what you give as input.");
        System.out.println("Type in six numbers");
        a = new A();
        a.m();
    }
}

