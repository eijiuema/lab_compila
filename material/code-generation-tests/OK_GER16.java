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
    public void set(int k) {
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
    override public void init() {
        super.init();
        this.k = 2;
    }
    override public void print() {
        System.out.print(this.get_B());
        System.out.print(" ");
        System.out.print(this.get_A());
        System.out.print(" ");
        super.print();
    }
}

class C extends A {
    override public int get_A() {
        return 0;
    }
}

