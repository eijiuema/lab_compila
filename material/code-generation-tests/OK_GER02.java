class OK_GER02{
    public static void main(String args[]){
        new Program().run();
    }
}

class A{
    public long m(){
        Out.print(6, " " );
        Out.print(1, " " );
        Out.print(1 + 1, " " );
        Out.print(4 - 1, " " );
        Out.print(6 - 3 + 1, " " );
        Out.print(10 / 2, " " );
        Out.print(2 * 3, " " );
    }
}

class Program{
    public long run(){
        long a;
        Out.println("6 1 2 3 4 5 6" );
        a = new A();
        a.m;
    }
}

