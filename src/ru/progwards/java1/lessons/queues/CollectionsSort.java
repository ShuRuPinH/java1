package ru.progwards.java1.lessons.queues;


import java.util.*;
import java.util.concurrent.Callable;

public class CollectionsSort {

    public static void mySort(Collection<Integer> data) {
        List<Integer> dataL = new ArrayList<>(data);

        for (int i = 0; i <= data.size() - 1; i++) {
            for (int j = i + 1; j <= data.size() - 1; j++) {
                if (dataL.get(i) > dataL.get(j)) {
                    int temp = dataL.get(j);
                    dataL.set(j, dataL.get(i));
                    dataL.set(i, temp);
                }
            }
        }
        data.clear();
        data.addAll(dataL);
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


    }

    static void collSort(Collection<Integer> data) {

        Collections.sort(new ArrayList(data));
    }

    public static Collection<String> compareSort() {


        Collection<Integer> test = new ArrayList<>();
        Set<Integer> temp = new TreeSet<>();
        Collection<String> res = new ArrayList<>();


        //    PriorityQueue<PriorityString> res = new PriorityQueue<>();


        final int ELEM_COUNT = 10_000;
        long start = System.currentTimeMillis();
        for (int i = 0; i < ELEM_COUNT; i++) test.add((int) (Math.random() * 100));
        System.out.println("Making Collectz: " + (System.currentTimeMillis() - start));

        int time = 1;

        start = System.currentTimeMillis();
        mySort(test);
        final int myTime = (int) (System.currentTimeMillis() - start);
        temp.add(myTime);

        //   res.offer(new PriorityString("mySort", myTime));

        start = System.currentTimeMillis();
        minSort(test);
        int minTime = (int) (System.currentTimeMillis() - start);
        temp.add(minTime);

        //   res.offer(new PriorityString("minSort", minTime));
        start = System.currentTimeMillis();
        collSort(test);
        int collTime = (int) (System.currentTimeMillis() - start);
        temp.add(collTime);
        //    res.offer(new PriorityString("colSort", collTime));

        System.out.println("my=" + myTime + "     min=" + minTime + "      col=" + collTime);

        for (Integer i : temp) {
            if (i == myTime) {
                res.add("mySort");
                //   temp.remove(i);
                continue;
            } else if (i == collTime && i == minTime) {
                res.add("collSort");
                res.add("minSort");
                break;
            } else if (i == collTime) {
                res.add("collSort");
            } else if (i == minTime) {
                res.add("minSort");
                //      temp.remove(i);
                continue;
            }
        }
        return res;
    }






/*    static class PriorityString implements Comparable<PriorityString> {
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
       *//*     if (priority == o.priority){

               o.priority+= Byte.compare((byte)str.toCharArray()[0],(byte)o.str.toCharArray()[0]);
                System.out.println("comp="+Byte.compare((byte)str.toCharArray()[0],(byte)o.str.toCharArray()[0]));
            }*//*

        }

        @Override
        public String toString() {
            return priority + " " + str;
        }
    }*/

    public static void main(String[] args) {
        Collection<Integer> cal = new ArrayList<>();

        cal.add(37);
        cal.add(52);
        cal.add(92);
        cal.add(77);
        cal.add(49);
        cal.add(34);


        mySort(cal);
        System.out.println(cal);

        for (int i = 0; i < 6; i++) {
            System.out.println(compareSort());
        }


    }

}

