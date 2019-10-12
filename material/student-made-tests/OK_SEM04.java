import java.util.Scanner;
public class OK_SEM04 {
    public static void main(String args[]) {
        new Program().run();
    }
}

class Program {
    void run() {
        int a, b;
        boolean e, f;
        a = 1;
        b = 1;
        e = true;
        f = false;
        if ((a == b) && (a == 1) && (1 == b) && (a != b) && (e == f) && (true == f) && (e != f) && (true != f)) {
            System.out.println("impossivel");
        }
    }
}

