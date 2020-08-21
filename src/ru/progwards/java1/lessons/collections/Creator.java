package ru.progwards.java1.lessons.collections;

import java.util.*;

public class Creator {


    /*
    Задача 1, класс Creator
1.1 Реализовать метод
public static Collection<Integer> fillEven(int n) - создать коллекцию и заполнить последовательностью четных
возрастающих чисел начиная с 2, количество элементов в коллекции n
*/
    public static Collection<Integer> fillEven(int n){
        List <Integer> col= new ArrayList();
        for (int i=0; i<n; i++){
            col.add(2*(i+1));
        }
        return col;
    }

    /*
1.2 Реализовать метод
public static Collection<Integer> fillOdd(int n) - создать коллекцию и заполнить последовательностью нечетных
убывающих чисел, минимальное число в коллекции 1, количество элементов в коллекции n
*/
    public static Collection<Integer> fillOdd(int n){
        List <Integer> col= new ArrayList();
        for (int i=n-1; i>=0; i--){
            col.add(i*2+1);
        }

        return col;
    }

    /*
1.3 Реализовать метод
public static Collection<Integer> fill3(int n) - создать коллекцию и заполнить ее тройками чисел. Каждая тройка
создается по алгоритму: первое число тройки - индекс числа в коллекции, второе - индекс в квадрате,
третье - индекс в кубе, количество элементов в коллекции n*3
     */
    public static Collection<Integer> fill3(int n){
        List <Integer> col= new LinkedList();
        ListIterator lsi= col.listIterator();
        for (int i=0; i<n; i++){
            lsi.add(lsi.nextIndex());
            lsi.add((lsi.nextIndex())*(lsi.nextIndex()));
            lsi.add((lsi.nextIndex())*(lsi.nextIndex())*(lsi.nextIndex()));
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
