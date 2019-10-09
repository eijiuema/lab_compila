import java.util.Scanner;
public class OK_GER15 {
    public static void main(String args[]) {
        new Program().run();
    }
}

class A {
    private int i;
    private int j;
    void init_A() {
        this.i = 1;
        this.j = 2;
    }
    void call_p() {
        this.p();
    }
    void call_q() {
        this.q();
    }
    void r() {
        System.out.print(this.i);
        System.out.print(" ");
    }
    void s() {
        System.out.print(this.j);
        System.out.print(" ");
    }
    private void p() {
        System.out.print(this.i);
        System.out.print(" ");
    }
    private void q() {
        System.out.print(this.j);
        System.out.print(" ");
    }
}

class B extends A {
    private int i;
    private int j;
    void init_B() {
        this.i = 3;
        this.j = 4;
    }
    override void call_p() {
        this.p();
    }
    override void call_q() {
        this.q();
    }
    override void r() {
        System.out.print(this.i);
        System.out.print(" ");
    }
    override void s() {
        System.out.print(this.j);
        System.out.print(" ");
    }
    private void p() {
        System.out.print(this.i);
        System.out.print(" ");
    }
    private void q() {
        System.out.print(this.j);
        System.out.print(" ");
    }
}

class C extends A {
    private int i;
    private int j;
    void init_C() {
        this.i = 5;
        this.j = 6;
    }
    override void call_p() {
        this.p();
    }
    override void call_q() {
        this.q();
    }
    override void r() {
        System.out.print(this.i);
        System.out.print(" ");
    }
    override void s() {
        System.out.print(this.j);
        System.out.print(" ");
    }
    private void p() {
        System.out.print(this.i);
        System.out.print(" ");
    }
    private void q() {
        System.out.print(this.j);
        System.out.print(" ");
    }
}

