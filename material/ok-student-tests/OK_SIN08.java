import java.util.Scanner;
public class OK_SIN08 {
    public static void main(String args[]) {
        new Program().run();
    }
}

class A {
    public int m(int x/*aa*/, int y/*aa*/, boolean ok/*aa*/) {
        return x + y;
    }
}

class Program {
    void run() {
        A a;
        a = new A();
        System.out.println((a.m(3, 4, true)));
    }
}

