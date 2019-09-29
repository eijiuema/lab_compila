class OK_GER01{
    public static void main(String args[]){
        new Program().run();
    }
}

class A{
    public long m(){
        Out.print(7, "print:" );
        if(1>0) {
            Out.print(0, "print:" );
        }
        if(1>=0) {
            Out.print(1, "print:" );
        }
        if(1!=0) {
            Out.print(2, "print:" );
        }
        if(0<1) {
            Out.print(3, "print:" );
        }
        if(0<=1) {
            Out.print(4, "print:" );
        }
        if(0==0) {
            Out.print(5, "print:" );
        }
        if(0>=0) {
            Out.print(6, "print:" );
        }
        if(0<=0) {
            Out.print(7, "print:" );
        }
        if(1==0) {
            Out.print(18, "print:" );
        }
        if(0>1) {
            Out.print(10, "print:" );
        }
        if(0>=1) {
            Out.print(11, "print:" );
        }
        if(0!=0) {
            Out.print(12, "print:" );
        }
        if(1<0) {
            Out.print(13, "print:" );
        }
        if(1<=0) {
            Out.print(14, "print:" );
        }
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

