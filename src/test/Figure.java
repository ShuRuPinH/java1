package test;

import java.util.*;

class Figure {

}


class Square extends Figure {
    private double side;

    public Square(double side) {
        this.side = side;
    }

    public double getSide() {
        return side;
    }
}

class Round extends Figure {
    private double diameter;

    public Round(double diameter) {
        this.diameter = diameter;
    }

    public double getDiameter() {
        return diameter;
    }

    /*
      
      Напишите метод с сигнатурой String figDetect(Figure fig), который
 - для квадрата возвращает "Сторона квадрата "+.side
- для круга возвращает "Диаметр круга "+.diameter
- для всех остальных классов "Неизвестная фигура"
      
      */

    String figDetect(Figure fig) {

        if (fig == null) return "Неизвестная фигура";

        if (new Round(2).getClass().equals(fig.getClass())) {


            return "Диаметр круга " + ((Round) fig).getDiameter();
        } else if (new Square(2).getClass().equals(fig.getClass())) {

            return "Сторона квадрата " + ((Square) fig).getSide();
        }

        return "Неизвестная фигура";
    }

    public static void main(String[] args) {
        Round dd = new Round(2);
        System.out.println(dd.getClass());


        System.out.println(new Round(2).getClass());
        System.out.println(new Round(2).getClass().equals(dd.getClass()));

        System.out.println(dd.figDetect(new Round(1)));

    }

}