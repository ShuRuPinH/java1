package test;

import java.util.*;

public class Collection {

    public List<Integer> listAction(List<Integer> list) {

        list.remove(Collections.min(list));
        list.add(0, list.size());
        list.add(2,Collections.max(list));


        return list;

    }

/*
удаляет минимальный элемент коллекции
по индексу 0 добавляет число равное количеству элементов
по индексу 2 добавляет максимальный элемент из list
возвращает list как результат метода
 */

    /*
    суммирует значения всех элементов списка
удаляет из списка элементы, значение которых больше суммы, деленной на 100 (целочисленное деление)
возвращает результирующий список
     */
    public List<Integer> filter(List<Integer> list){
        long sum=0;
        for (int i=0 ; i< list.size(); i++){
            sum +=list.get( i);
        }
        sum/=100;
        List <Integer> fltr= new ArrayList();
        for (int i=0 ; i< list.size(); i++){
            if (list.get( i)>sum) fltr.add(list.get( i));
        }
        list.removeAll(fltr);
        return list;
    }

    /*
    Напишите метод с сигнатурой public void iterator3(ListIterator<Integer> iterator)
    который заменяет значение каждого элемента,
     которое кратно 3 на значение его индекса.

     */

    public Set<Integer> a2set(int[] a) {

        Set<Integer> res = new HashSet();
        for (int aa : a) {
            res.add(aa);
        }

        return res;
    }


    public void iterator3(ListIterator<Integer> iterator) {
        for (ListIterator<Integer> it = iterator; it.hasNext(); ) {
            Integer i = iterator.next();
            if (i % 3 == 0 && i != 0) {
                it.set(iterator.previousIndex());
            }

        }
    }

    public static void main(String[] args) {
        Collection test = new Collection();
        List<Integer> ll = new ArrayList();
        for (int i = 0; i < 10; i++) {
            ll.add(i -1);
        }


        System.out.println(ll.toString());
        test.iterator3(ll.listIterator());
        System.out.println(ll.toString());




    }
}