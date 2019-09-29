class OK_GER06{
    public static void main(String args[]){
        new Program().run();
    }
}

class A{
     long m(){
        int i, j, k;
        Out.print(7, " ");
        i = 1;
        j = i + 1;
        k = j + 1;
        Out.print(i, " ");
        Out.print(j, " ");
        Out.print(k, " ");
        i = 3 + 1 * 3 / 2 / 2 + 1;
        Out.print(i, " ");
        i = 100 - 95 * 2 - 5;
        Out.print(i, " ");
        i = 100 - 45 * 2 - 4;
        Out.print(i, " ");
        Out.print(7, " ");
    }
}

class Program{
     long run(){
        long a;
        Out.println("7 1 2 3 4 5 6 7");
        a = new A();
        a.m;
    }
}

