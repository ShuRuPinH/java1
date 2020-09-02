package ru.progwards.java1.lessons.queues;


import java.util.*;

public class CollectionsSort {

    public static void mySort(Collection<Integer> data) {
        List<Integer> dataL = new ArrayList<>(data);

        for (int i = 0; i <= dataL.size() - 1; i++) {
            for (int j = i + 1; j <= dataL.size() - 1; j++) {
                if (dataL.get(i) > dataL.get(j)) {
                    int temp = dataL.get(j);
                    dataL.set(j, dataL.get(i));
                    dataL.set(i, temp);
                }
            }
        }
    }

    public static void minSort(Collection<Integer> data) {
        Collection<Integer> nnew = new ArrayList<>();
        while (!data.isEmpty()) {
            Integer minT = Collections.min(data);
            data.remove(minT);
            nnew.add(minT);
            int k = 5;
        }
        data.addAll(nnew);
     /*     - создать новую коллекцию
- найти минимальный элемент с использованием функции min()
- переместить его в новую коллекцию
- делать так до тех пор, пока все элементы не окажутся в новой коллекции
- скопировать новую коллекцию в старую */

    }

    static void collSort(Collection<Integer> data) {

        Collections.sort(new ArrayList(data));
    }

    public static Collection<String> compareSort() {


        Collection<Integer> mm = new ArrayList<>();
        PriorityQueue<PriorityString> res = new PriorityQueue<>();


        final int ELEM_COUNT = 10_000;
        long start = System.currentTimeMillis();
        for (int i = 0; i < ELEM_COUNT; i++) mm.add((int) (Math.random() * 100));
        System.out.println("Making Collectz: " + (System.currentTimeMillis() - start));

        int time = 1;
/*
по логике элементы должны раставляться  согласно величине приоритета (наименьшим временем вычисления), но
получаеся что  первым встает элемент с высшим приоритето, а оастльные  просто по очереди добавления
 */
        mySort(mm);
        int myTime = (int) (System.currentTimeMillis() - start);
        res.offer(new PriorityString("minSort", myTime));

        minSort(mm);
        int minTime = (int) (System.currentTimeMillis() - start);
        res.offer(new PriorityString("minSort", minTime));
///  Изменим время на грантировано  больше чем у  последнего
        minSort(mm);
        int minTimet = (int) (System.currentTimeMillis() - start);
        res.offer(new PriorityString("minSort *TEST*", minTimet + 200));

        collSort(mm);
        int collTime = (int) (System.currentTimeMillis() - start);
        res.offer(new PriorityString("colSort", collTime));

        System.out.println(res);
        return null;
    }
/*

РЕЗУЛЬТАТ СОРТИРОВКИ :
[166 minSort, 629 minSort, 888 minSort *TEST*, 689 colSort]



 */

    static class PriorityString implements Comparable<PriorityString> {
        private String str;
        private int priority;

        public PriorityString(String str, int priority) {
            this.str = str;
            this.priority = priority;
        }

        @Override
        public int compareTo(PriorityString o) {
            System.out.println("pr=" + priority);
            System.out.println("o.pr=" + o.priority);
            return Integer.compare(this.priority, o.priority);
       /*     if (priority == o.priority){

               o.priority+= Byte.compare((byte)str.toCharArray()[0],(byte)o.str.toCharArray()[0]);
                System.out.println("comp="+Byte.compare((byte)str.toCharArray()[0],(byte)o.str.toCharArray()[0]));
            }*/

        }

        @Override
        public String toString() {
            return priority + " " + str;
        }
    }

    public static void main(String[] args) {


        for (int i = 0; i < 6; i++) {
            compareSort();
        }


    }

}

