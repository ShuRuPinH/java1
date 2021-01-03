package ru.progwards.java2.lessons.calculator;


import java.util.*;

public class Сalculator {

    static Deque<Integer> stack = new ArrayDeque();

    static boolean test(String str) {
        int rule = 0;
        for (char x : str.toCharArray()) {
            if (x == '(') {
                rule++;
            } else if (x == ')') {
                rule--;
            }
        }
        return rule == 0;
    }


    static void strReader(String string) {
        if (test(string)) {


        } else {
            System.out.println(" \'(\' != \')\' ");
            return;
        }
    }

    /////////////////////
    static int calc(String cal) {
        char[] strn = cal.toCharArray();
        for (int i = 0; i < strn.length; i++) {
            System.out.println("char=" + strn[i]);
            if (i == 0 && Character.isDigit(strn[i])) stack.addLast(Character.getNumericValue(strn[0]));
            if (i != 0 && Character.isDigit(strn[i])) {

                switch (strn[i - 1]) {
                    case '+':
                        stack.addLast(Character.getNumericValue(strn[i]));
                        break;
                    case '-':
                        stack.addLast(Character.getNumericValue(strn[i]) * (-1));
                        break;
                    case '*':
                        stack.addLast(stack.pollLast() * (Character.getNumericValue(strn[i])));
                        break;
                    case '/':
                        stack.addLast(stack.pollLast() / (Character.getNumericValue(strn[i])));
                        break;

                }
            }

        }
        return sum();
    }

    static Integer sum() {
        Integer sum = 0;
        for (Integer x : stack) {
            System.out.println("s=" + x);
            sum += x;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(calc("-3-2*3-8*6"));
    }

}
