package ru.progwards.java1.lessons.arrays;
import java.util.Arrays;
public class ArraySort {

    public static void sort(int[] a){
for (int i=0; i<=a.length-1; i++){
    for (int j=i+1; j<=a.length-1; j++){
        if (a[i] > a[j]){
            int temp= a[j];
            a[j]=a[i];
            a[i]=temp;
        }
    }
}
    }

    public static void main(String[] args) {
        int[] ar1 = {10, 50, 30, 40,100,1};
       sort(ar1);
        System.out.println(Arrays.toString(ar1));

    }
}
