package test;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Rect {

    public int arrayMax(int[] a){
        Arrays.sort(a);
        if (a.length==0) return 0;
        return a[a.length-1];
    }

    public static void main(String[] args) {

        int[] ar = {-1};


        Rect temp= new Rect();
        System.out.println("Hello World!");
        System.out.println(temp.arrayMax(ar));
        boolean[] a1 = {true, false, false, true, false};







    }
}