import java.util.Scanner;
public class OK_GER22 {
    public static void main(String args[]) {
        new Program().run();
    }
}

class Program {
    public void run() {
        System.out.println("100");
        int i, j, n;
        i = 0;
        j = 0;
        n = 10;
        boolean b;
        b = false;
        do{
            n = n + 1;
        } while( (true) == false);
        assert n == 11 : "'repeat-until' statement with 'false' as expression'";
        n = 10;
        int sum;
        sum = 0;
        do{
            i = 0;
            do{
                i = i + 1;
                sum = sum + 1;
            } while( (i < n) == false);
            j = j + 1;
        } while( (j < n) == false);
        System.out.println(sum);
        assert sum == 100 : "Nested 'repeat-until' statement with two indexes";
    }
}

