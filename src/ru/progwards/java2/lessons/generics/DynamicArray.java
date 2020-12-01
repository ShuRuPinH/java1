package ru.progwards.java2.lessons.generics;

import java.util.Arrays;


public class DynamicArray<T> {
    private T[] mas;
    int last = 0;

    public DynamicArray() {
        mas =
                (T[]) new Object[4];
    }

    public void add(T obj) {
        if (last == mas.length) doubleMas();
        mas[last] = obj;
        last++;
    }

    private void doubleMas() {
        mas = Arrays.copyOf(mas, mas.length * 2);
    }

    public void insert(int pos, T num) {
        if (last == mas.length) doubleMas();
        Object[] temp = new Object[mas.length];
        System.arraycopy(mas, 0, temp, 0, pos);
        temp[pos] = num;
//        System.out.println(pos+","+num+"-ins1="+Arrays.toString(temp));
        System.arraycopy(mas, pos, temp, pos + 1, temp.length - (pos + 1));
//        System.out.println("    ins2="+Arrays.toString(temp));
        mas = (T[]) temp;
        last++;
    }

    public void remove(int pos) {
        Object[] temp = new Object[mas.length];
        System.arraycopy(mas, 0, temp, 0, pos);
//        System.out.println(pos+"-del1="+Arrays.toString(temp));
        System.arraycopy(mas, pos + 1, temp, pos, temp.length - 1 - (pos));
//        System.out.println("  del2="+Arrays.toString(temp));
        mas = (T[]) temp;
        last--;
    }

    public T get(int pos) {
        return mas[pos];
    }

    public int size() {
        return mas.length;
    }

    public String toPrint() {
        String temp = "[";
        for (int i = 0; i < size(); i++) {
            temp += ("\"" + get(i) + "\",");
        }
        return temp.substring(0, temp.length() - 1) + "]";
    }


    public static void main(String[] args) {
        DynamicArray<Double> testInt = new DynamicArray<>();
        for (int i = 0; i < 6; i++) {
            testInt.add(i + 0.5);
        }
        System.out.println(testInt.toPrint());

        testInt.insert(4, 77.0);
        System.out.println(testInt.toPrint());

        testInt.remove(4);
        System.out.println(testInt.toPrint());


    }
}
