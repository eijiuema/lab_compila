import java.util.Scanner;
public class OK_GER03 {
    public static void main(String args[]) {
        new Program().run();
    }
}

class A {
    void m() {
        System.out.print(6);
        System.out.print(" ");
        if(true && true) {
            System.out.print(1);
            System.out.print(" ");
        }
        if((false && true)) {
            System.out.print(1000);
            System.out.print(" ");
        }
        if(true && false) {
            System.out.print(1000);
            System.out.print(" ");
        }
        if((false && false)) {
            System.out.print(1000);
            System.out.print(" ");
        }
        if(true || true) {
            System.out.print(2);
            System.out.print(" ");
        }
        if((true || false)) {
            System.out.print(3);
            System.out.print(" ");
        }
        if(false || true) {
            System.out.print(4);
            System.out.print(" ");
        }
        if((false || false)) {
            System.out.print(1000);
            System.out.print(" ");
        }
        if((! false)) {
            System.out.print(5);
            System.out.print(" ");
        }
        if(! true) {
            System.out.print(1000);
            System.out.print(" ");
        }
        if((true || (true && false))) {
            System.out.print(6);
            System.out.print(" ");
        }
    }
}

class Program {
    public void run() {
        A a;
        System.out.println("6 1 2 3 4 5 6");
        a = new A();
        a.m();
    }
}

