package ru.progwards.java2.lessons.tests.calc;

public class SimpleCalculator {
    public int sum(int val1, int val2) throws ArithmeticException {
        long rez = (long) val1 + (long) val2;
        System.out.println(rez);
        if (rez < Integer.MIN_VALUE || rez > Integer.MAX_VALUE) throw new ArithmeticException("Overflow int in sum()");
        return (int) rez;
    }

    public int diff(int val1, int val2) throws ArithmeticException {

        long rez = (long) val1 - (long) val2;
        if (rez < Integer.MIN_VALUE || rez > Integer.MAX_VALUE) throw new ArithmeticException("Overflow int in diff()");
        return (int) rez;

    }

    public int mult(int val1, int val2) throws ArithmeticException {
        long rez = (long) val1 * (long) val2;
        if (rez < Integer.MIN_VALUE || rez > Integer.MAX_VALUE) throw new ArithmeticException("Overflow int in mult()");
        return (int) rez;

    }

    public int div(int val1, int val2) throws ArithmeticException {
        if (val2 == 0) throw new ArithmeticException(" /0 ! ");
        long rez = (long) val1 / (long) val2;
        if (rez < Integer.MIN_VALUE || rez > Integer.MAX_VALUE) throw new ArithmeticException("Overflow int in div()");
        return (int) rez;
    }

    ;

    public static void main(String[] args) {
        SimpleCalculator tt = new SimpleCalculator();

    }
}
