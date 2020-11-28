package ru.progwards.java2.lessons.recursion;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.function.Predicate;

public class AsNumbersSum {
    private static Integer[] a;
    static StringBuilder temp = new StringBuilder();
    static Predicate<Integer> nNul = x -> x != 0;

    public static Integer[] getA() {
        return a;
    }

    /*
    Реализовать класс, AsNumbersSum, содержащий метод
    public static String asNumbersSum(int number), который раскладывает параметр number,
    как всевозможные уникальные комбинации сумм натуральных чисел, например:
    5 = 4+1 = 3+2 = 3+1+1 = 2+2+1 = 2+1+1+1 = 1+1+1+1+1
     */
    public static String asNumbersSum(int number) {

        a = new Integer[number];
        Arrays.fill(a, 0);
        ones(number, number, 0);
        temp.delete(temp.length() - 3, temp.length() - 1);

        return temp.toString();

    }

    static void ones(int n, int k, int i) {


        if (n < 0) {

            return;
        }

        if (n == 0) {
            Arrays.stream(a).filter(nNul).forEach(nu -> temp.append(nu + "+"));
            temp.deleteCharAt(temp.length() - 1);
            temp.append(" = ");
        } else {
            if (n - k >= 0) {
                a[i] = k; // фиксируем i-ое слагаемое
                ones(n - k, k, i + 1);
            }

            if (k - 1 > 0) {
                ones(n, k - 1, i);
            }
        }

        return;
    }


    public static void main(String[] args) {
        AsNumbersSum test = new AsNumbersSum();


        System.out.println(asNumbersSum(8));


    }
}
