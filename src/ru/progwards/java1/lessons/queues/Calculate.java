package ru.progwards.java1.lessons.queues;

public class Calculate {
    public static double calculation1() {
        StackCalc ccc = new StackCalc();
        ccc.push(3);
        ccc.push(12.1);
        ccc.add();
        ccc.push(2.2);
        ccc.mul();
        return ccc.pop();
    }

    /*
    3.7 public static double calculation1(), возвращающую результат вычислени€ следующей формулы:

2.2*(3+12.1), использу€ класс StackCalc


3.8 public static double calculation2(), возвращающую результат вычислени€ следующей формулы:

(737.22+24)/(55.6-12.1)+(19-3.33)*(87+2*(13.001-9.2)), использу€ класс StackCalc

     */
    static double calculation2() {
        StackCalc c = new StackCalc();
        c.push(13.001);
        c.push(9.2);
        c.sub();
        c.push(2);
        c.mul();
        c.push(87);
        c.add();
        c.push(19);
        c.push(3.33);
        c.sub();
        c.mul();
        c.push(737.22);
        c.push(24);
        c.add();
        c.push(55.6);
        c.push(12.1);
        c.sub();
        c.div();
        c.add();

        return c.pop();


    }


    public static void main(String[] args) {
        System.out.println(calculation1());
        System.out.println(calculation2());
    }
}
