import java.util.Scanner;
public class OK_CALC {
    public static void main(String args[]) {
        new Program().run();
    }
}

class A {
    int calculaNota(int ntrab, int nprov) {
        int ans;
        ans = 11;
        if (ntrab >= 6) {
            if (nprov >= 6) {
                ans = ntrab + nprov;
                ans = ans / 2;
                return ans;
            }
        }
        return ans;
    }
    int calculaNotaB(int ntrab, int nprov) {
        int ans;
        ans = (2 * ntrab + nprov) / 3;
        return ans;
    }
}

class B extends A {
    @Override
     int calculaNotaB(int ntrab, int nprov) {
        if (ntrab < nprov) {
            return ntrab;
        } else {
            return nprov;
        }
    }
}

class Program {
    void run() {
        A a;
        int ans;
        int ntrab;
        int nprov;
        System.out.println("");
        System.out.println("Ok-fib");
        System.out.println("The output should be :");
        System.out.println("0 <= x <= 10");
        a = new A();
        nprov = 10;
        ntrab = 10;
        ans = a.calculaNota(ntrab, nprov);
        if (ans != 11) {
            System.out.println(ans);
        } else {
            if ((ans == 11) && (nprov < 6)) {
                a = new B();
                System.out.println((a.calculaNotaB(ntrab, nprov)));
            } else {
                System.out.println((a.calculaNotaB(ntrab, nprov)));
            }
        }
    }
}

