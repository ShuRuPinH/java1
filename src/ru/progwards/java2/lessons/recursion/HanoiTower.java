package ru.progwards.java2.lessons.recursion;

import javax.management.Query;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.function.Function;

public class HanoiTower {
    int size;
    int pos;
    boolean trace = false;

    int count = 1;

    ArrayDeque<Integer> st1 = new ArrayDeque<>();
    ArrayDeque<Integer> st2 = new ArrayDeque<>();
    ArrayDeque<Integer> st3 = new ArrayDeque<>();

    ArrayDeque[] sts = new ArrayDeque[]{st1, st2, st3};


    public HanoiTower(int size, int pos) {
        this.size = size;
        this.pos = pos;

        for (int i = size; i > 0; i--) {
            switch (pos) {
                case 1:
                    st1.add(i);
                    break;
                case 2:
                    st2.add(i);
                    break;
                case 3:
                    st3.add(i);
                    break;
                default:
                    System.out.println("POSITION ERROR");
            }
        }
    }

    public void move(int from, int to) {
        int buf = 6 - from - to;

        han(size, from, to, buf);

    }

    ///////////////////////////////
    private void han(int q, int from, int to, int buf) {
        if (q == 0) return;
        han(q - 1, from, buf, to);
        turn(from, to);
        han(q - 1, buf, to, from);
        return;
    }

    private void turn(int from, int to) {
        sts[to - 1].add(sts[from - 1].pollLast());
        if (trace) print();
    }


    /////////////////////////////////
    void print() {
        Integer[] s1 = st1.toArray(new Integer[size]);
        Integer[] s2 = st2.toArray(new Integer[size]);
        Integer[] s3 = st3.toArray(new Integer[size]);


        System.out.println("*** MOVE #" + count + " ***");
        count++;

        for (int i = size - 1; i >= 0; i--) {
            System.out.println("" + disk(s1[i]) + " " + disk(s2[i]) + " " + disk(s3[i]) + "");
        }
        System.out.println("=================");
    }

    String disk(Integer num) {
        if (num == null) return "  I  ";
        String temp = String.valueOf(num);
        String z = "";
        if (temp.length() == 1) z = "00";
        else if (temp.length() == 2) z = "0";

        return "<" + z + temp + ">";

    }

    void setTrace(boolean on) {
        trace = on;
    }

    //***//***// MAIN //***//***//***//***//***//***//***//***//***//***//
    public static void main(String[] args) {
        HanoiTower test = new HanoiTower(7, 1);
        test.setTrace(true);
        test.move(1, 3);

    }
}
