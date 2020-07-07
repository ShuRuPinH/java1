package test;

public class Parent {
    String name;
    double a=10;
    double b=44;
        Parent(){
        name="parent";
        a = 12;        }
public String print(){
    return (name+"  a="+a+"  b="+b);
}
    public static void main(String[] args) {
        System.out.println(new Child(5).print());
        System.out.println(new Parent().print());
    }
}

class Child extends Parent{

    Child (double a) {

        name="child";
        this.a=a;
        b=2;
        }
  }