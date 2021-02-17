package test;

import java.util.*;

public class rerewrew {
    static int nu = 0;
    TreeMap<Integer, Integer> temp = new TreeMap<>();

    String rez = "";

    public rerewrew(int i) {
        this.nu = i;
    }

    void ddd() {
        int start = temp.firstKey();
        int end = temp.get(start);

        for (Map.Entry<Integer, Integer> x : temp.entrySet()) {
            System.out.println("START=" + start + "/" + temp.get(start) + "     x.KEY=" + x.getKey());
            if (x.getKey() == start) {
                continue;
            }
            if (end + 1 == (int) x.getKey()) {
                end = x.getValue();
                System.out.println("   NEAR            START.end=" + (temp.get(start) + "    x.KEY=" + x.getKey()) + "  end=" + end);

                continue;
            } else {
                putIn(start, end);

                end = x.getValue();
            }

            start = x.getKey();


        }
        putIn(start, end);
    }

    void putIn(int st, int en) {
        rez += "{" + st + "--" + en + "}";
    }

    public static void main(String[] args) {
        ArrayList test = new ArrayList();
        test.add("12");
        test.add("34");


        System.out.println(test);
        System.out.println(test.get(0));
        test.remove(0);
        test.add("56");
        System.out.println(test.get(0));


        try {
            System.out.println(test);
        } catch (Exception e) {
            System.out.println("не должно быть");
        }


    }


}

