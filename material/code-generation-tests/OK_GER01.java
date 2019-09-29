class OK_GER01{
    public static void main(String args[]){
        new Program().run();
    }
}

class A{
    public long m(){
        Out.print(7, "print:" );
    }
}

class Program{
    public long run(){
        long a;
        Out.println("println:" );
        a = new A();
        a.m;
    }
}

