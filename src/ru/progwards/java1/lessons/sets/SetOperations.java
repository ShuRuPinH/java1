package ru.progwards.java1.lessons.sets;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SetOperations {


    public static Set<Integer> union(Set<Integer> set1, Set<Integer> set2) {
        Set<Integer> s1 = new HashSet<>(set1);
        Set<Integer> s2 = new HashSet<>(set2);
        s1.addAll(s2);
        return s1;
    }

    public static Set<Integer> intersection(Set<Integer> set1, Set<Integer> set2) {
        Set<Integer> s1 = new HashSet<>(set1);
        Set<Integer> s2 = new HashSet<>(set2);

        s1.retainAll(s2);
        return s1;
    }

    public static Set<Integer> difference(Set<Integer> set1, Set<Integer> set2) {
        Set<Integer> s1 = new HashSet<>(set1);
        Set<Integer> s2 = new HashSet<>(set2);
        s1.removeAll(intersection(s1, s2));
        return s1;
    }

    public static Set<Integer> symDifference(Set<Integer> set1, Set<Integer> set2) {
        Set<Integer> s1 = new HashSet<>(set1);
        Set<Integer> s2 = new HashSet<>(set2);
        final Set<Integer> inter = intersection(s1, s2);
        s1.addAll(s2);
        s1.removeAll(inter);
        return s1;
    }

    public static void main(String[] args) {
        Set<Integer> t1 = new HashSet<Integer>(Arrays.asList(7, 1, 2, 10, 11, 0));
        Set<Integer> t2 = new HashSet<Integer>(Arrays.asList(7, 1, 2, 10, 11, 0));


        System.out.println(Arrays.toString(symDifference(t1, t2).toArray()));
    }

}
