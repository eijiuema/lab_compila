import java.util.Scanner;
public class ER_SIN_EXTRA01 {
    public static void main(String args[]) {
        new Program().run();
    }
}

class A {
    private int num1;
    private int num2;
    private int result;
    int mult() {
        return this.num1 * this.num2;
    }
    void div(int num3, int num4) {
        this.result = num3 / num4;
    }
}

class B {
    private String name;
    void setName(String name) {
        this.name = name;
    }
    String getName() {
        return this.name;
    }
}

