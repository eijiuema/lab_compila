import java.util.Scanner;
public class OK_SEM11 {
    public static void main(String args[]) {
        new Program().run();
    }
}

class A {
    public void open() {
    }
}

class Program {
    void run() {
        A a;
        a = new A();
        a.open();
    }
}

