package ru.progwards.java2.lessons.basetypes;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;

public class BiDirList<T> implements Iterable<T> {

    class BDItem<T> {
        private T item;
        BDItem<T> prev;
        BDItem<T> next;


        public BDItem(T item) {
            this.item = item;
        }

        public void setPrev(BDItem<T> prev) {
            this.prev = prev;
        }

        public void setNext(BDItem<T> next) {
            this.next = next;
        }

        public T getItem() {
            return item;
        }

        public BDItem<T> getPrev() {
            return prev;
        }

        public BDItem<T> getNext() {
            return next;
        }
    }

    /////params
    int size = 0;

    BDItem<T> head;
    BDItem<T> end;

    ////////////////////////!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    @Override
    public Iterator<T> iterator() {
        Iterator<T> it = new Iterator<T>() {

            BDItem<T> temp = head;

            @Override
            public boolean hasNext() {
                if (temp == null) return false;

                return temp.getNext() != null || temp.getItem() != null;
            }

            @Override
            public T next() {

                var tt = temp.getItem();

                temp = temp.getNext();
                return tt;


            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return it;
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        Objects.requireNonNull(action);
        for (T t : this) {
            action.accept(t);
        }
    }

    @Override
    public Spliterator<T> spliterator() {
        return null;
    }

    /*
Реализовать класс BiDirList - двунаправленный связный список

Методы

1.1 public void addLast(T item) - добавить в конец списка
*/
    public void addLast(T item) {
        BDItem temp = new BDItem(item);
        if (head == null) {
            head = temp;
            end = temp;
        } else {
            end.setNext(temp);
            temp.setPrev(end);
            end = temp;
        }
        size++;
    }

    /*
1.2 public void addFirst(T item)- добавить в начало списка
*/
    public void addFirst(T item) {
        BDItem temp = new BDItem(item);
        if (head == null) {
            head = temp;
            end = temp;
        } else {
            head.setPrev(temp);
            temp.setNext(head);
            head = temp;
        }
        size++;
    }

    /*
1.3 public void remove(T item) - удалить
*/
    public void remove(T item) {

        if (head == null) return;
        if (head.item.equals(item)) {
            head = head.getNext();
            head.setPrev(null);
        } else {
            BDItem temp = head.getNext();
            while (temp.getNext() != null) {
                if (temp.getItem().equals(item)) {
                    temp.getPrev().setNext(temp.getNext());
                }
            }
        }
        size--;
    }

    /*
1.4 public T at(int i) - получить элемент по индексу
*/
    public T at(int i) {

        if (i < size / 2) {
            if (i == 0) return head.getItem();
            BDItem<T> temp = head;
            for (int j = 1; j < size / 2; j++) {
                temp = temp.getNext();
                if (j == i) return temp.getItem();
            }
        }
        if (i >= size / 2) {
            if (i == size - 1) return end.getItem();
            BDItem<T> temp = end;
            for (int j = size - 2; j >= size / 2; j--) {
                temp = temp.getPrev();
                if (j == i) return temp.getItem();
            }
        }
        return null;
    }

    /*
1.5 public int size() - получить количество элементов
*/
    public int size() {
        return size;
    }

    /*
1.6 public static<T> BiDirList<T> from(T[] array) - конструктор из массива
*/
    public static <T> BiDirList<T> from(T[] array) {
        BiDirList temp = new BiDirList();
        for (T x : array) {
            temp.addLast(x);
        }
        return temp;
    }

    /*
1.7 public static<T> BiDirList<T> of(T...array) -  конструктор из массива
*/
    public static <T> BiDirList<T> of(T... array) {
        BiDirList<T> temp = new BiDirList();
        for (T x : array) {
            temp.addLast(x);
        }
        return temp;
    }

    /*
1.8 public static void toArray(T[] array) - скопировать в массив
*/
    public <T> void toArray(T[] array) {
        BiDirList temp = this;
        Iterator<T> iter = temp.iterator();
        int i = 0;
        while (iter.hasNext() && array.length > i) {
            array[i] = iter.next();
            i++;
        }

    }
    /*
1.9 реализовать интерфейс Iterable<T>
     */


    public static void main(String[] args) {
        BiDirList<Integer> test = of(1, 4, 2, 4, 7, 8, 6, 3);


        for (int i = 0; i < test.size(); i++) {
            System.out.println("[" + i + "]= " + test.at(i));
        }
        System.out.println("size = " + test.size);


        Integer[] arr = new Integer[7];
        test.toArray(arr);

        System.out.println(Arrays.toString(arr));

    }
}
