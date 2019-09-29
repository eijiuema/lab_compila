class OK_GER04{
    public static void main(String args[]){
        new Program().run();
    }
}

class A{
     long m(){
        int i;
        boolean b;
        Out.print(6);
        i = 1;
        while(i <= 5) {
            Out.print(i, " " );
            i = i + 1;
        }
        b = false;
        while(b != true) {
            Out.print(6, " " );
            b = ! b;
        }
    }
}

class Program{
     long run(){
        long a;
        Out.println("6 1 2 3 4 5 6" );
        a = new A();
        a.m;
    }
}

