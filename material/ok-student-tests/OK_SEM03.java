import java.util.Scanner;
public class OK_SEM03 {
    public static void main(String args[]) {
        new Program().run();
    }
}

class Program {
    void run() {
        String r, s;
        r = "Ola";
        s = "Tudo bem?";
        if (((r == s) && (r != s) && (r == null) && (null == r) && (r == "Ola") && ("Ola" == r) && (r != null) && (r != "Ola") && ("Ola" != r) && ("nil" == null))) {
            System.out.println("impossivel");
        }
    }
}

