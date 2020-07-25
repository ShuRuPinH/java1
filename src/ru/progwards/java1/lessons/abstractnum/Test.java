package ru.progwards.java1.lessons.abstractnum;

public class Test {

    public static void main(String[] args) {

        DoubleNumber dn= new DoubleNumber(3);
        IntNumber in= new IntNumber(3);


        System.out.println("Cube int volume=     "+new Figure3D.Cube(in).volume());
        System.out.println("Cube double volume=  "+new Figure3D.Cube(dn).volume());

        System.out.println("Pyramid int volume=     "+new Figure3D.Pyramid(in).volume());
        System.out.println("Pyramid double volume=  "+new Figure3D.Pyramid(dn).volume());
    }
}

