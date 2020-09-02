package ru.progwards.java1.lessons.queues;

import java.util.*;

public class Order {
    private double sum;
    private int num = 0;
    static int nnum = 0;

    public Order(double sum) {
        this.sum = sum;
        num = numm();
    }

    public double getSum() {
        return sum;
    }

    public int getNum() {
        return num;
    }

    static int numm() {
        return ++nnum;
    }

    public static void main(String[] args) {
        Order o1 = new Order(10);
        Order o2 = new Order(10);
        Order o3 = new Order(10);

        System.out.println(o1.getNum() + "   " + o2.getNum() + "    " + o3.getNum());
    }

}
