import java.util.Scanner;
public class OK_GER05 {
    public static void main(String args[]) {
        new Program().run();
    }
}

class A {
    void m() {
    }
}

class Program {
    void run() {
        A a;
        System.out.println("");
        System.out.println("Ok-ger05");
        System.out.println("The output should be what you give as input.");
        System.out.println("Type in six numbers");
        a = new A();
        a.m();
    }
}

