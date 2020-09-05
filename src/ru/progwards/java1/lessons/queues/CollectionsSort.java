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


            }
        data.clear();
        data.addAll(nnew);


    }

    public static void collSort(Collection<Integer> data) {
        Collections.sort((List) data);
    }

    static class CompMetod {
        String name;
        long time;

        public CompMetod(String name, long time) {
            this.name = name;
            this.time = time;
        }

        public String getName() {
            return name;
        }

        public long getTime() {
            return time;
        }

        public void setName(String name) {
            this.name = name;
        }

        static class TimeComparator implements Comparator<CompMetod> {
            @Override
            public int compare(CompMetod o1, CompMetod o2) {
                return Long.compare(o1.getTime(), o2.getTime());
            }
        }

        static class NameComparator implements Comparator<CompMetod> {
            @Override
            public int compare(CompMetod o1, CompMetod o2) {
                return o1.getName().compareTo(o2.getName());
            }
        }
    }

    public static Collection<String> compareSort() {

        ArrayList<String> arrayList = new ArrayList<>();    //  для возврата
        Collection<Integer> list1 = fillFull(1_000);
        Collections.shuffle((List<?>) list1);


        Comparator<CompMetod> metСomp = new CompMetod.TimeComparator().thenComparing(new CompMetod.NameComparator());
        TreeSet<CompMetod> metСoll = new TreeSet<CompMetod>(metСomp);

        long start0 = System.currentTimeMillis();
        mySort(list1);
        long start1 = System.currentTimeMillis();
        metСoll.add(new CompMetod("mySort", start1 - start0));

        start0 = System.currentTimeMillis();
        minSort(list1);
        start1 = System.currentTimeMillis();
        metСoll.add(new CompMetod("minSort", start1 - start0));

        start0 = System.currentTimeMillis();
        collSort(list1);
        start1 = System.currentTimeMillis();
        metСoll.add(new CompMetod("collSort", start1 - start0));

        for (CompMetod mets : metСoll) {
            arrayList.add(mets.getName());
        }
        System.out.println(arrayList);
        return arrayList;
    }

    private static Collection<Integer> fillFull(int i) {
        final Collection<Integer> test = new ArrayList<>();
        for (int j = 0; j < i; j++) test.add((int) (Math.random() * 100));
        return test;
    }

       /*
        Set<Integer> temp = new TreeSet<>();
        Collection<String> res = new ArrayList<>();


        //    PriorityQueue<PriorityString> res = new PriorityQueue<>();


        final int ELEM_COUNT = 10_000;
        long start = System.currentTimeMillis();

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
    }*/






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
        cal.add(02);
        cal.add(77);
        cal.add(49);
        cal.add(34);


        collSort(cal);
        System.out.println("       coll               =" + cal);


        for (int i = 0; i < 6; i++) {
            System.out.println(compareSort());
        }


    }


}

