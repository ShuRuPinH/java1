package start;

public class Hello {

    static double fractional(double num){
     Integer cell=(int) num;
     return num-cell;
    }


    public static void main (String[] args){


String sost=Double.toString(fractional(1.53));
Float fost=Float.valueOf(sost);
Double dost=Double.valueOf(sost);
int celi=fost.intValue();
float ff= 24;
int cc=(int)ff;
long dd=12;





        System.out.print("\t"+sost);System.out.println("=sost is "+sost.getClass());
        System.out.print("\t"+fost);System.out.println("=fost is "+fost.getClass());
        System.out.print("\t"+dost);System.out.println("=fost is "+dost.getClass());


    }

}
