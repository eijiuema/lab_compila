import java.util.Scanner;
public class OK_GER14 {
    public static void main(String args[]) {
        new Program().run();
    }
}

class A {
    private int k;
    int get_A() {
        return this.k;
    }
    void init() {
        this.k = 1;
    }
}

class B extends A {
    private int k;
    int get_B() {
        return this.k;
    }
    override void init() {
        super.init();
        this.k = 2;
    }
}

class C extends B {
    private int k;
    int get_C() {
        return this.k;
    }
    override void init() {
        super.init();
        this.k = 3;
    }
}

class D extends C {
    private int k;
    int get_D() {
        return this.k;
    }
    override void init() {
        super.init();
        this.k = 4;
    }
}

class Program {
    void run() {
        A a;
        B b;
        C c;
        D d;
        System.out.println("4 3 2 1");
        d = new D();
        d.init();
        System.out.print(d.get_D());
        System.out.print(" ");
        c = d;
        System.out.print(c.get_C());
        System.out.print(" ");
        b = c;
        System.out.print(b.get_B());
        System.out.print(" ");
        a = b;
        System.out.print(a.get_A());
        System.out.print(" ");
    }
}

