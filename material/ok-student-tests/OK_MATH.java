import java.util.Scanner;
public class OK_MATH {
    public static void main(String args[]) {
        new Program().run();
    }
}

class A {
    int sum(int n1/*aa*/, int n2/*aa*/) {
        return n1 + n2;
    }
    int sub(int n1/*aa*/, int n2/*aa*/) {
        return (n1 - n2);
    }
    int mult(int n1/*aa*/, int n2/*aa*/) {
        return (n1 * n2);
    }
    int div(int n1/*aa*/, int n2/*aa*/) {
        if (n2 == 0) {
            return -1;
        } else {
            return (n1 / n2);
        }
    }
    int pow2(int n1/*aa*/) {
        return (n1 + n1);
    }
}

class Program {
    void run() {
        A a;
        int n1;
        int n2;
        System.out.println("");
        a = new A();
        System.out.println("Ok-math");
        n1 = 1;
        n2 = 2;
        System.out.println(a.sum(n1, n2));
        System.out.println(a.sub(n1, n2));
        System.out.println(a.mult(n1, n2));
        System.out.println(a.div(n1, n2));
        System.out.println(a.pow2(n1));
        System.out.println(a.pow2(n2));
    }
}

