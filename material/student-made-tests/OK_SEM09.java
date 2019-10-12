import java.util.Scanner;
public class OK_SEM09 {
    public static void main(String args[]) {
        new Program().run();
    }
}

class A {
    void m(int n, A x) {
        A other;
        other = this;
        n = n - 1;
        if (n > 0) {
            other.m(n, this);
        }
    }
}

class Program {
    void run() {
        A a;
        a = new A();
        a.m(5, a);
    }
}

