import java.util.Scanner;
public class OK_SEM17 {
    public static void main(String args[]) {
        new Program().run();
    }
}

class Program {
    void run() {
        int num = 10;
        if (num < 15) {
            System.out.println("pequeno");
            assert num < 15 : "num < 15";
        } else {
            System.out.println("grande");
            assert num >= 15 : "num >= 15";
        }
    }
}

