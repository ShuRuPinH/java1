package ru.progwards.java1.lessons.basics;

public class ReverseDigits {

    public static int reverseDigits(int number){
        int a=number/100;
        int b=(number-100*a)/10;
        int c= number-100*a-10*b;

        return 100*c+10*b+a;
    }

    public static void main(String[] args) {

        System.out.println(reverseDigits(843));
    }
}
