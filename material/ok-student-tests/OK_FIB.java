import java.util.Scanner;
public class OK_FIB {
    public static void main(String args[]) {
        new Program().run();
    }
}

class A {
    int fib(int n/*aa*/) {
        A a;
        int left;
        int right;
        if (n < 2) {
            return n;
        } else {
            a = new A();
            left = a.fib(n - 1);
            right = a.fib(n - 2);
            return left + right;
        }
    }
}

class Program {
    void run() {
        A a;
        System.out.println("");
        System.out.println("Ok-fib");
        System.out.println("The output should be :");
        System.out.println("34");
        a = new A();
        System.out.println((a.fib(10)));
    }
}

