import java.util.Scanner;
public class OK_LEX10 {
    public static void main(String args[]) {
        new Program().run();
    }
}

class A {
    void m() {
    }
}

class Program {
    void run() {
        A a;
        a = new A();
        a.m();
    }
}

