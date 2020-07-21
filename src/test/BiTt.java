package test;

import java.util.Objects;

class Rectangle {
    private double a;
    private double b;

    public Rectangle(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public double area() {

        return a*b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return Double.compare(this.area(), rectangle.area()) == 0;
    }




    public static void main(String[] args) {
        Rectangle ab= new Rectangle(2,3);
        Rectangle dc= new Rectangle(3,2);
        System.out.println(ab.equals(dc));
    }

}
