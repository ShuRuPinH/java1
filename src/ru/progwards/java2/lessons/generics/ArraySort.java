package ru.progwards.java2.lessons.generics;

import java.util.Arrays;

public class ArraySort<T extends Comparable> {

    public static <T extends Comparable> void sort(T[] a) {

        for (int i = 0; i <= a.length - 1; i++) {
            for (int j = i + 1; j <= a.length - 1; j++) {
                if (a[i].compareTo(a[j]) == 1) {
                    T temp = (T) a[j];
                    a[j] = a[i];
                    a[i] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        //Integer[] ar1 = {10, 50, 30, 1, 100, 1};
        Double[] ar1 = {12.4, 43.5, 0.545, 2.56454, 8.455};
        sort(ar1);
        System.out.println(Arrays.toString(ar1));

    }


}
