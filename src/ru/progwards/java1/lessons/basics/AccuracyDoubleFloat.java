package ru.progwards.java1.lessons.basics;

public class AccuracyDoubleFloat {


    public static double volumeBallDouble(double radius){
        final double PI=3.14;
        double r3=radius*radius*radius;
        return PI*r3*3/4;
    }

    public static float volumeBallFloat(float radius){
        final float PI=3.14f;
        float r3=radius*radius*radius;
        return PI*r3*3/4;

    }
    public static double calculateAccuracy(double radius){

         return volumeBallDouble(radius)-volumeBallFloat((float)radius);

    }

    public static void main(String[] args) {
        System.out.println(calculateAccuracy(6371.2));
    }
}
