package ru.progwards.java1.lessons.compare_if_cycles;

public class TriangleInfo {
    public static boolean isTriangle(int a, int b, int c){
        if (a<b+c && b<a+c && c<b+a) return true;
        else return false;
    }

    public static boolean isRightTriangle(int a, int b, int c){
        if (a*a==b*b+c*c| b*b==a*a+c*c | c*c==b*b+a*a) return true;
        else return false;
    }

    public static boolean isIsoscelesTriangle(int a, int b, int c){
        if (isTriangle(a, b, c)) {
            if (a==b || c==a || b==c) return true;
            else return false;
        }
        else return false;
    }


    public static void main(String[] args) {
        System.out.println(isTriangle(2,2,3));
        System.out.println(isRightTriangle(3,4,5));
        System.out.println(isIsoscelesTriangle(2,2,3));
    }

}
