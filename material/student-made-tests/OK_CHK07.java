import java.util.Scanner;
public class OK_CHK07 {
    public static void main(String args[]) {
        new Program().run();
    }
}

class Person {
    private String course;
    private int number;
    private int age;
    private String name;
    public String getCourse() {
        return this.course;
    }
    public void setCourse(String course) {
        this.course = course;
    }
    public int getNumber() {
        return this.number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public void init(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public String getName() {
        return this.name;
    }
    public int getAge() {
        return this.age;
    }
    public void print() {
        System.out.println("Name = " + this.name);
        System.out.println("Age = " + this.age);
    }
}

class Group {
    private Person first;
    private Person second;
    public void set(Person first, Person second) {
        this.first = first;
        this.second = second;
    }
    public Person getFirst() {
        return this.first;
    }
    public Person getSecond() {
        return this.second;
    }
    public void print() {
        System.out.println("First: " + this.first.getName());
        System.out.println("Second: " + this.second.getName());
    }
}

class University {
    private String name;
    private int numberOfStudents;
    private String city;
    public void init(String name, String city, int numberOfStudents) {
        this.name = name;
        this.city = city;
        this.numberOfStudents = numberOfStudents;
    }
    public void print() {
        System.out.println(this.name);
        System.out.println(this.city);
        System.out.println(this.numberOfStudents);
    }
}

class Program {
    public void run() {
        University s;
        Person joao;
        Person maria;
        Group g;
        s = new University();
        s.init("UFSCar", "Sao Carlos", 7000);
        s.print();
        System.out.println("");
        joao = new Person();
        joao.init("Joao", 21);
        joao.setCourse("EnC");
        joao.setNumber(6729);
        maria = new Person();
        maria.init("Maria", 20);
        maria.setCourse("Fisioterapia");
        maria.setNumber(8607);
        joao.print();
        System.out.println("");
        maria.print();
        System.out.println("");
        g = new Group();
        g.set(joao, maria);
        g.print();
        System.out.println("");
    }
}

