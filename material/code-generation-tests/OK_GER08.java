class OK_GER08{
    public static void main(String args[]){
        new Program().run();
    }
}

class A{
     long m1(int n){
        Out.print(1, " ");
        Out.print(n, " ");
    }
     long m2(int n){
        Out.print(2, " ");
        Out.print(n, " ");
    }
     long m3(int n, int p, String q, int r, long falseBool){
        Out.print(3, " ");
        Out.print(n, " ");
        Out.print(p, " ");
        Out.print(q, " ");
        Out.print(r, " ");
        if(falseBool) {
            Out.print(8 + " ");
}
        else {            Out.print(7 + " ");
        }
    }
}

class Program{
     long run(){
        long a;
        Out.println("1 1 2 2 3 3 4 5 6 7");
        a = new A();
        a.m1(1);
        a.m2(2);
        a.m3(3, 4, "5", 6, false);
    }
}

