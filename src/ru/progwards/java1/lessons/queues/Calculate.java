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
