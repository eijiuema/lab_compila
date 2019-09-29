class OK_GER05{
    public static void main(String args[]){
        new Program().run();
    }
}

class A{
     long m(){
        int a, b, c, d, e, f;
        a = In.readInt;
        b = In.readInt;
        c = In.readInt;
        d = In.readInt;
        e = In.readInt;
        f = In.readInt;
        Out.print(a);
        Out.print(b);
        Out.print(c);
        Out.print(d);
        Out.print(e);
        Out.print(f);
    }
}

class Program{
     long run(){
        long a;
        Out.println("");
        Out.println("Ok-ger05");
        Out.println("The output should be what you give as input.");
        Out.println("Type in six numbers");
        a = new A();
        a.m;
    }
}

