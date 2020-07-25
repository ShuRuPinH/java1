package ru.progwards.java1.lessons.abstractnum;

public class Test {

    public static void main(String[] args) {

        DoubleNumber dn= new DoubleNumber(3);
        IntNumber in= new IntNumber(3);


        System.out.println("Cube int volume=     "+new Cube(in).volume());
        System.out.println("Cube double volume=  "+new Cube(dn).volume());

        System.out.println("Pyramid int volume=     "+new Pyramid(in).volume());
        System.out.println("Pyramid double volume=  "+new Pyramid(dn).volume());
    }
}

