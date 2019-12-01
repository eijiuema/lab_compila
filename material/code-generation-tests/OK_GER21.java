import java.util.Scanner;
public class OK_GER21 {
    public static void main(String args[]) {
        new Program().run();
    }
}

class A {
    private int n;
    void set(int n/*aa*/) {
        this.n = n;
    }
    int get() {
        return this.n;
    }
}

class Program {
    private A a;
    public void print() {
        System.out.print(this.a.get());
    }
    public A get() {
        return this.a;
    }
    public void run() {
        System.out.println("0");
        System.out.println("0");
    }
    private void set(A a/*aa*/) {
        this.a = a;
    }
}

