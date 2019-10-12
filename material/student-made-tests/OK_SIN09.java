import java.util.Scanner;
public class OK_SIN09 {
    public static void main(String args[]) {
        new Program().run();
    }
}

class A {
    private int n;
    private int k;
    public int m() {
        this.m2(0);
        return this.m1() + this.k;
    }
    public void init() {
        this.k = 1;
        this.n = 0;
    }
    private int m1() {
        return this.n + 1;
    }
    private void m2(int pk) {
        this.k = pk;
    }
}

class Program {
    void run() {
        A a;
        a = new A();
        System.out.println(a.m());
    }
}

