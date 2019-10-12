import java.util.Scanner;
public class OK_SIN02 {
    public static void main(String args[]) {
        new Program().run();
    }
}

class A {
    void m() {
        int i;
        int j;
        boolean b;
        b = false;
        if (b) {
        }
        while(b) {
            ;        }
        while((b)) {
        }
        i = ((1 * 4) + 3) - (5 / 2);
        b = false;
        if (((! b && (i < 0)) || (i > 10))) {
            b = true;
            i = -1;
            i = (new Scanner(System.in)).nextInt();
            j = (new Scanner(System.in)).nextInt();
            while(i > 0) {
                System.out.println(i);
                i = i - 1;
            }
        } else {
            b = true;
            System.out.println(i);
        }
        if (((((((i == 1) || (i < 1)) || (i <= 5)) || (i > 1)) || (i >= 1)) || (i != 3))) {
            i = 0;
        }
    }
}

class Program {
    void run() {
        A a;
        a = new A();
        a.m();
    }
}

