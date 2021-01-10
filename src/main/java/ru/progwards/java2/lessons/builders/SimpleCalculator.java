package ru.progwards.java2.lessons.builders;

public class SimpleCalculator {
    public int sum(int val1, int val2) throws ArithmeticException {
        long rez = (long) val1 + (long) val2;

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
        int a = 0, b = 0;

        SimpleCalculator tt = new SimpleCalculator();

        if (args.length == 3) {
            try {
                a = Integer.parseInt(args[1]);
                b = Integer.parseInt(args[2]);
            } catch (Exception e) {
                System.out.println("Invalid command's params");
                return;
            }
        } else {
            System.out.println("Invalid command or params");
            return;
        }


        if (args[0].equals("sum")) {
            System.out.println(a+"+"+b+"="+tt.sum(a, b));
            return;

        }
        else   if (args[0].equals("diff")) {
            System.out.println(a+"-"+b+"="+tt.diff(a, b));
            return;

        }
        else   if (args[0].equals("mult")) {
            System.out.println(a+"*"+b+"="+tt.mult(a, b));
            return;

        }
        else   if (args[0].equals("div")) {
            System.out.println(a+"/"+b+"="+tt.div(a, b));
            return;

        }
    else {
        System.out.println("Invalid command");
        return;
    }
}
}
