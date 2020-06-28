package ru.progwards.java1.lessons.compare_if_cycles;

public class TriangleSimpleInfo {

    public static int maxSide(int a, int b, int c) {
        int max = 0;
        if (a > b && a > c || a==b && b>c) max = a;
        if (b > a && b > c || b==c && c>a) max = b;
        if (c > b && c > a || c==a && a>b) max = c;
        if (a==b &&b==c) max=a;
        return max;
    }

    public static int minSide(int a, int b, int c){
        int min = 0;
        if (a < b && a < c ||a==b && b<c) min = a;
        if (b < a && b < c ||b==c && c<a) min = b;
        if (c < b && c < a ||c==a && a<b) min = c;
        if (a==b &&b==c) min=a;
        return min;
    }
    public static boolean isEquilateralTriangle(int a, int b, int c){

 if (a==b &&b==c) return true;
        else return false;
    }


    public static void main(String[] args) {
        System.out.println(maxSide(12, 12, 12));
        System.out.println(minSide(11,11,11));
        System.out.println(isEquilateralTriangle(5,5,5));
    }
}