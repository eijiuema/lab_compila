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
}

class Program {
    void run() {
        Store s;
        int a;
        s = new Store();
        a = In.nextInt();
        s.set(a);
        System.out.println(s.get);
    }
}
