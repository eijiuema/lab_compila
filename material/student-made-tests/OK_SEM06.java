import java.util.Scanner;
public class OK_SEM06 {
    public static void main(String args[]) {
        new Program().run();
    }
}

class A {
    private int n;
    void set(int pn) {
        int n;
        this.n = pn;
    }
    int put(int n, String set) {
        boolean put;
        this.n = n;
        return n;
    }
}

class Program {
    void run() {
        A a;
        a = new A();
        a.set(0);
    }
}

