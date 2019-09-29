class OK_GER03{
    public static void main(String args[]){
        new Program().run();
    }
}

class A{
     long m(){
        Out.print(6, " " );
        if(true && true) {
            Out.print(1, " " );
        }
        if(false && true) {
            Out.print(1000, " " );
        }
        if(true && false) {
            Out.print(1000, " " );
        }
        if(false && false) {
            Out.print(1000, " " );
        }
        if(true || true) {
            Out.print(2, " " );
        }
        if(true || false) {
            Out.print(3, " " );
        }
        if(false || true) {
            Out.print(4, " " );
        }
        if(false || false) {
            Out.print(1000, " " );
        }
        if(! false) {
            Out.print(5, " " );
        }
        if(! true) {
            Out.print(1000, " " );
        }
        if(true || true && false) {
            Out.print(6, " " );
        }
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

