package ru.progwards.java1.lessons.sets;

import java.util.Set;

public class SetOperations {


    public static Set<Integer> union(Set<Integer> set1, Set<Integer> set2) {
        set1.addAll(set2);
        return set1;
    }

    public static Set<Integer> intersection(Set<Integer> set1, Set<Integer> set2) {
        set1.retainAll(set2);
        return set1;
    }

    public static Set<Integer> difference(Set<Integer> set1, Set<Integer> set2) {
        set1.removeAll(intersection(set1, set2));
        return set1;
    }

    public static Set<Integer> symDifference(Set<Integer> set1, Set<Integer> set2) {
        set1.removeAll(intersection(set1, set2));
        set2.removeAll(intersection(set1, set2));
        set1.addAll(set2);
        return set1;
    }


}
