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
        int[] a1 = {12, 5, 0, 58, 36};
        int[] a2 = Arrays.copyOf(a1, a1.length);
        a2[2] = 0;
        System.out.println(Arrays.equals(a1, a2));





    }
}