package ru.progwards.java2.lessons.generics;

import java.util.Arrays;

public class ArraySort<T extends Comparable> {

    public static <T extends Comparable> void sort(T[] a) {
if (a.getClass().getCanonicalName().equals("java.lang.String[]")) {
    Arrays.asList(a).sort((a1, b1) -> {
        if (a1 == null || b1 == null) return 0;
        return a1.compareTo(b1);

    });
}
        for (int i = 0; i <= a.length - 1; i++) {
            for (int j = i + 1; j <= a.length - 1; j++) {
                if (a[i] == null || a[j] == null) {
                    continue;
                }
                if (a[i].compareTo(a[j]) == 1) {
                    T temp = (T) a[j];
                    a[j] = a[i];
                    a[i] = temp;
                }
            }
        }
    }


    public static void main(String[] args) {
        // Integer[] ar1 = {10, 50, null, 1, 100, 1};
        String[] ar1 = {"boui", null, "zqweq", "dasds", "aasdas"};
        sort(ar1);
        System.out.println(Arrays.toString(ar1));
        System.out.println(ar1.getClass().getCanonicalName());

    }


}
