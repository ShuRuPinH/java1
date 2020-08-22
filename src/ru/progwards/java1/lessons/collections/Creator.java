package ru.progwards.java1.lessons.collections;

import java.util.*;

public class Creator {


    public static Collection<Integer> fillEven(int n){
        List <Integer> col= new ArrayList();
        for (int i=0; i<n; i++){
            col.add(2*(i+1));
        }
        return col;
    }

    public static Collection<Integer> fillOdd(int n){
        List <Integer> col= new ArrayList();
        for (int i=n-1; i>=0; i--){
            col.add(i*2+1);
        }

        return col;
    }


    public static Collection<Integer> fill3(int n){
        List <Integer> col= new LinkedList();
        ListIterator lsi= col.listIterator();
        for (int i=0; i<n; i++){
            int ind=lsi.nextIndex();
            lsi.add(ind);
            lsi.add((ind)*(ind));
            lsi.add((ind)*(ind)*(ind));
            }
        return col;
    }



    public static void main(String[] args) {
        Creator test= new Creator();
        System.out.println(fillEven(10));
        System.out.println(fillOdd(10));
        System.out.println(fill3(10));
    }
}
