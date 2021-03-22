package ru.progwards.java2.lessons.graph;

import java.util.*;

public class MinList {
    TreeMap<Integer, List> liste = new TreeMap<>();

    public MinList(int start, int size) {
        for (int i = 1; i <= size; i++) {
            liste.put(Integer.MAX_VALUE, Collections.singletonList(i));
        }
        change(start, Integer.MAX_VALUE, 0);
    }

    void change(int pos, int wayOld, int wayNew) {

        liste.get(wayOld).remove(pos);
        if (liste.get(wayOld).isEmpty()) liste.remove(wayOld);
        if (liste.containsKey(wayNew)) {
            liste.get(wayNew).add(pos);

        } else liste.put(wayNew, Collections.singletonList(pos));

    }


    public static void main(String[] args) {
        MinList tt = new MinList(2, 5);
        tt.change(3, Integer.MAX_VALUE, 0);


    }
}
