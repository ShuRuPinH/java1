package ru.progwards.java1.lessons.compare_if_cycles;

public class CyclesGoldenFibo {

    public static boolean containsDigit(int number, int digit){
        Integer num=number;
        Integer n=0;
        int x=1;

       while (num / x>0) {
            n=num %(10*x);
            n=n/x;
            x *= 10;
            if (n == digit) return true;  }
        if (n == digit)return true;

        return false;
    }


    public static int fiboNumber(int n){
        int f1=0;
        int f2=1;
        int f;
        int i=1;
      do {f=f1+f2;
        f1=f2; f2=f;
        i++;}while (i<n);
      return f;
    }
    public static boolean isGoldenTriangle(int a, int b, int c){
        if (a==b) {
            Double ad= (double) a;
            Double cd= (double) c;
            if(ad/cd <1.61903 && ad/cd >1.61703) return true;

        }
        else if (c==a) {
            Double ad= (double) a;
            Double bd= (double) b;
            if(ad/bd <1.61903 && ad/bd >1.61703) return true;
        }
        else if (b==c) {
            Double ad= (double) a;
            Double bd= (double) b;
            if(bd/ad <1.61903 && bd/ad >1.61703) return true;
        }

        return false;
    }
    public static void main(String[] args) {
        System.out.println(containsDigit(1,1));
        System.out.println(fiboNumber(6));
        System.out.println(isGoldenTriangle(3,3,5));

        for (int i=1; i<=15; i++){
            System.out.println(fiboNumber(i));
        }

        for (int i=1; i<=100; i++){

            for (int j = 1; j<2*i; j++){
                if (isGoldenTriangle(i,i,j))
                    System.out.println("sides="+i+"   base="+j);

            }

        }
    }
}
