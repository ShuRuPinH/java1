package test;

import java.util.*;

public class rerewrew<K extends Comparable<K>> {
    K nu;

    public rerewrew(K nu) {
        this.nu = nu;
    }

    public static void main(String[] args) {
        var Fst = new rerewrew<>(10);
        var Snd = new rerewrew<>(11);

        if (Fst.nu != Snd.nu) System.out.println("working");
        else System.out.println(":(");
    }


    }




