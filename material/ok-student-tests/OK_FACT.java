import java.util.Scanner;
public class OK_FACT {
    public static void main(String args[]) {
        new Program().run();
    }
}

class A {
    int fact(int n/*aa*/) {
        A a;
        int ans;
        if (n == 0) {
            return 1;
        } else {
            a = new A();
            ans = a.fact(n - 1);
            ans = ans * n;
            return ans;
        }
    }
}

class Program {
    void run() {
        A a;
        System.out.println("");
        System.out.println("Ok-fact");
        System.out.println("The output should be :");
        System.out.println("120");
        a = new A();
        System.out.println((a.fact(5)));
    }
}

