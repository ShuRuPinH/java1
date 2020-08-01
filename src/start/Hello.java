package start;

import java.math.BigDecimal;

class Rectangle {

    Rectangle(BigDecimal a, BigDecimal b) {
        this.a = a;
        this.b = b;
    }
    public BigDecimal a;
    public BigDecimal b;


    boolean rectCompare(Rectangle r1, Rectangle r2){
     int t= r1.a.multiply(r1.b).compareTo(r2.a.multiply(r2.b));
     if (t==0) return true;
     else return false;
    }

}