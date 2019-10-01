import java.util.Scanner;
public class teste {
    public static void main(String args[]) {
        new Program().run();
    }
}

class Store {
    private int n;
    int get() {
        return this.n;
    }
    void set(int n) {
        this.n = n;
    }
    void set(int n, int m) {
        this.n = n;
        System.out.print(m);
    }
}

class Program {
    void run() {
        Store s;
        int a;
        s = new Store();
        a = (new Scanner(System.in)).nextInt();
        s.set(a);
        s.set(a, a);
        System.out.println(s.get());
    }
}

