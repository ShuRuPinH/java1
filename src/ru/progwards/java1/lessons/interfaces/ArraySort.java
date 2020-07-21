package ru.progwards.java1.lessons.interfaces;
import java.util.Arrays;

public class ArraySort {

    static void sort(CompareWeight[] a){
        for (int i=0; i<=a.length-1; i++){
            for (int j=i+1; j<=a.length-1; j++){
                if (a[j].compareWeight(a[i]) == CompareWeight.CompareResult.LESS){
                    CompareWeight temp= a[j];
                    a[j]=a[i];
                    a[i]=temp;
                }
            }
        }
    }

    public static void main(String[] args) {




    }
}
