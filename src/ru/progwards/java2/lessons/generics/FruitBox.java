package ru.progwards.java2.lessons.generics;

import java.util.ArrayList;
import java.util.List;


public class FruitBox<T extends Fruit> extends ArrayList {
    List<T> list = new ArrayList();
    float APL_WEIGHT = 1.0f;
    float ORG_WEIGHT = 1.5f;

    enum CompareResult {LESS, EQUAL, GREATER}

    ;

    public void add(T frt) {
        if (list.size() == 0 || list.get(0).getClass() == frt.getClass()) {
            list.add(frt);
        } else return;
    }

    public float getWeight() {
        if (list.size() == 0) return 0;
        if (list.get(0).getClass().equals(new Apple().getClass())) {
            return list.size() * APL_WEIGHT;
        } else if (list.get(0).getClass().equals(new Orange().getClass())) {
            return list.size() * ORG_WEIGHT;
        } else return 0;
    }

    public void moveTo(FruitBox box) {
        if (this.list.size() == 0) return;
        if (box.getWeight() == 0 || this.list.get(0).getClass() == box.list.get(0).getClass()) {
            System.out.println("move");
            this.list.forEach(x -> box.add(x));
            this.list.clear();
        } else throw new UnsupportedOperationException();
    }

    public int compareTo(FruitBox box) {
        if (this.getWeight() > box.getWeight()) return 1;
        else if (this.getWeight() < box.getWeight()) return -1;
        else if (this.getWeight() == box.getWeight()) return 0;
        return 10;
    }

    //33///33//////////metods for ModuleTESTS
    static <M extends Comparable> CompareResult compare(M a, M b) {

        int temp = a.compareTo(b);
        if (temp < 0) return CompareResult.LESS;
        if (temp == 0) return CompareResult.EQUAL;
        if (temp > 0) return CompareResult.GREATER;
        return null;

    }

    <B> ArrayList from(B[] mas) {
        ArrayList res = new ArrayList();
        for (B x : mas) {
            res.add(x);
        }
        return res;
    }

    <U> void swap(List<U> list, int from, int to) {
        U temp = list.get(to);
        list.set(to, list.get(from));
        list.set(from, temp);
    }

    ////////////////////// MAIN
    public static void main(String[] args) {
        FruitBox aBox = new FruitBox();
        FruitBox oBox = new FruitBox();
        FruitBox zBox = new FruitBox();

        for (int i = 0; i < 3; i++) {
            oBox.add(new Orange());
            aBox.add(new Apple());
        }
        System.out.println(oBox.getWeight());
        oBox.moveTo(zBox);
        System.out.println(oBox.getWeight());
        zBox.add(new Apple());
        System.out.println(zBox.getWeight());
    }
}

