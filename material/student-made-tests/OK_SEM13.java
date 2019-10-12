import java.util.Scanner;
public class OK_SEM13 {
    public static void main(String args[]) {
        new Program().run();
    }
}

class A {
    void m() {
    }
    void p() {
        int m;
    }
}

class Program {
    public void run() {
        A a;
        a = new A();
        a.p();
    }
}

