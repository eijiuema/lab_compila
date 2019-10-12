import java.util.Scanner;
public class OK_SEM08 {
    public static void main(String args[]) {
        new Program().run();
    }
}

class A {
    private int i;
    public void put(int x, int y, boolean ok) {
        if ((x > y) && ok) {
            this.i = 0;
        }
    }
    public int get() {
        return this.i;
    }
    public void set(int i) {
        this.i = i;
    }
}

class B extends A {
    @Override
     public void put(int x, int y, boolean ok) {
        if (((x + y) < 1) && ! ok) {
            System.out.print(0);
        }
    }
}

class Program {
    void run() {
        B b;
        b = new B();
        b.put(1, 2, true);
    }
}

