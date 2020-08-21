package ru.progwards.java1.lessons.collections;



import java.util.*;

public class MatrixIterator <T> implements Iterator<T> {
        int cursor = 0;       // index of next element to return
        int lastRet = -1; // index of last element returned; -1 if no such


        private T[][] array2D;

        List <T> array= new ArrayList<>();

        MatrixIterator(T[][] array2D) {
            this.array2D=array2D;
        for (int i=0; i< array2D.length; i++) {
            for (int j=0; j< array2D[i].length;i++) {
                array.add(array2D[i][j]);
            }

        }
        }


        @Override
        public boolean hasNext() {
            return cursor < array.size();
        }


        @Override
        public T next() {

            int i = cursor;
            if (i >= array.size())
                throw new NoSuchElementException();
            cursor = i + 1;
            return (T) array.get(lastRet = i);

        }

        public MatrixIterator<T> arrIterator() {
            return new MatrixIterator<T>(this.array2D);
        }


///////////////

        public static void main(String[] args) {
            String[] ll = new String[10];


            for (int i = 0; i < 10; i++) {
                ll[i] = String.valueOf(i + 1);
            }
            System.out.println(Arrays.toString(ll));



            ru.progwards.java1.lessons.collections.ArrayIterator<String> ai = new ru.progwards.java1.lessons.collections.ArrayIterator<String>(ll);

            for (int i = 0; i <= 10; i++) {
                System.out.println("i="+i+"     "+ ai.hasNext()+"  ="+ai.next()+ "   "+ ai.hasNext());

            }

        }



    }


