package test;

public class NOD {
static int temp;
static int temp2;
static int big;
static int sml;

static int nod;



    public static int  evclid(int a, int b){

    if (a==b) System.out.println("a=b");;
    if (a>b) {big =a;
    sml =b; System.out.println(big+" и "+sml);}
    else  if (b>a) {
        big=b;
        sml=a;
        System.out.println(big+" и "+sml);
    }
    while (sml>0){

        temp = big % sml; if (temp==0){ nod=sml; break;}
        //System.out.print("temp="+temp);
        temp2= sml % temp; if (temp2==0){ nod=temp; break;}
        //System.out.println("  temp2="+temp2);
        big=temp;
        sml=temp2;
        System.out.print(" * ");

    }

return nod;
}

//enter
public static void main(String[] args) {

    System.out.println("\n НОД="+evclid(3918848,1653264));
}


}
