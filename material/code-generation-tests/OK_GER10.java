import java.util.Scanner;
public class OK_GER10 {
    public static void main(String args[]) {
        new Program().run();
    }
}

class A {
    private int k;
    void m1(int n/*aa*/) {
        this.k = 1;
        System.out.print(this.k + " " + n + " ");
    }
    int getK() {
        return this.k;
    }
}

class B extends A {
    private int k;
    void m2(int n/*aa*/) {
        this.k = 2;
        super.m1(1);
        System.out.print(this.k + " " + n + " ");
    }
    @Override
     int getK() {
        return this.k;
    }
}

class C extends B {
    void m3(int n/*aa*/) {
        super.m2(2);
        System.out.print("3 " + n + " ");
    }
    void m4(int n/*aa*/) {
        this.m3(3);
        System.out.print("4 " + n);
        System.out.print(" ");
    }
}

class Program {
    void run() {
        C c;
        System.out.println("1 1 2 2 3 3 4 4");
        c = new C();
        c.m4(4);
    }
}

