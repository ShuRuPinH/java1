package start;
import java.util.Arrays;

public class Test17 {
    String name;
    public Test17(String name) {
        this.name = name;
    }

    void println(String str) {
        System.out.println("Вызван метод println объекта с name = " + name + ", сообщение: " + str);
    }

    public static void research(Test17 test) {
        test.println("Вызван из Research");
    }

    public static void main(String[] args) {
        
        int a[]={};
        int b[]={};

        Test17 test17 = new Test17("test17");

        Child child = new Child("1545");

     research(test17);
     research(child);
    }
}

class Child extends Test17 {
    public Child (String name){
        super(name);
 
    }
    @Override
    void println(String str) {
        System.out.println("Потомок, вызван метод println объекта с name = " + name + ", сообщение: " + str);
    }
}