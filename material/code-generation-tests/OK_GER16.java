import java.util.Scanner;
public class OK_GER16 {
    public static void main(String args[]) {
        new Program().run();
    }
}

class A {
    private int k;
    public int get_A() {
        return this.k;
    }
    public void set(int k/*aa*/) {
        this.k = k;
    }
    public void print() {
        System.out.print(this.get_A());
        System.out.print(" ");
    }
    public void init() {
        this.set(0);
    }
}

class B extends A {
    private int k;
    public int get_B() {
        return this.k;
    }
    @Override
     public void init() {
        super.init();
        this.k = 2;
    }
    @Override
     public void print() {
        System.out.print(this.get_B());
        System.out.print(" ");
        System.out.print(this.get_A());
        System.out.print(" ");
        super.print();
    }
}

class C extends A {
    @Override
     public int get_A() {
        return 0;
    }
}

class Program {
    public void run() {
        A a;
        B b;
        C c;
        System.out.println("2 2 0 0 2 0 0 0 0 0 0");
        b = new B();
        b.init();
        c = new C();
        c.init();
        System.out.print(b.get_B());
        System.out.print(" ");
        a = b;
        a.print();
        b.print();
        a.init();
        b.init();
        System.out.print(a.get_A());
        System.out.print(" ");
        System.out.print(b.get_A());
        System.out.print(" ");
        a = c;
        System.out.print(a.get_A());
        System.out.print(" ");
        c = new C();
        System.out.print(c.get_A());
        System.out.print(" ");
    }
}

