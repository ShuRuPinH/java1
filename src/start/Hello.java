package start;

public class Hello {
    static double r;
    static double fractional(double num){
     Integer cell=(int) num;
     return num-cell;
    }
    static void hello(){
        System.out.println("x/y");
    }
static double add (double x,double y){
        r=y;
        return x/y;
}

    public static void main (String[] args){
System.out.print("\t ");
        System.out.println(add(5,10));

        System.out.println(fractional(1.53));


    }

}
