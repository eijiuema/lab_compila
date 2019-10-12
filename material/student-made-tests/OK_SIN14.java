import java.util.Scanner;
public class OK_SIN14 {
    public static void main(String args[]) {
        new Program().run();
    }
}

class Point {
    private int x;
    private int y;
    void set(int x, int y) {
        this.x = x;
        this.y = y;
    }
    int getX() {
        return this.x;
    }
    int getY() {
        return this.y;
    }
}

class Program {
    private Point p;
    void run() {
        this.p = new Point();
        this.p.set(0, 0);
    }
    Point getPoint() {
        return this.p;
    }
}

