package test;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;



public class Rect {

    public String test(String filename){
        String str="";
        try {  filename.toString() ;
            str="File processing" ;}
        catch (Exception e) {
            System.out.println(new IOException()+(" File not found"));}

        return str;
    }


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

        System.out.println(temp.test(null));
        System.out.println((byte)1281);







    }
}