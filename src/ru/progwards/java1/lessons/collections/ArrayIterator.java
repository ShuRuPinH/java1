package ru.progwards.java1.lessons.collections;


import java.util.*;

/*
    Сделать итератор по одномерному массиву, реализовать методы hasNext() и next(). Шаблон пустого итератора:

*/
public class ArrayIterator<T> implements Iterator<T> {
    int cursor = 0;       // index of next element to return
    int lastRet = -1; // index of last element returned; -1 if no such

    private T[] array;

    ArrayIterator(T[] array) {
        this.array = array;
    }


    @Override
    public boolean hasNext() {
        return cursor < array.length;
    }


    @Override
    public T next() {

        int i = cursor;
        if (i >= array.length)
            throw new NoSuchElementException();
        cursor = i + 1;
        return (T) array[lastRet = i];

    }

    public ArrayIterator<T> arrIterator() {
        return new ArrayIterator<T>(this.array);
    }


///////////////

    public static void main(String[] args) {
        String[] ll = new String[10];


        for (int i = 0; i < 10; i++) {
            ll[i] = String.valueOf(i + 1);
        }
        System.out.println(Arrays.toString(ll));



    ArrayIterator<String> ai = new ArrayIterator<String>(ll);

        for (int i = 0; i <= 10; i++) {
            System.out.println("i="+i+"     "+ ai.hasNext()+"  ="+ai.next()+ "   "+ ai.hasNext());

        }

}



}

