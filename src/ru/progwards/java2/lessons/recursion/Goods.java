package ru.progwards.java2.lessons.recursion;

import java.time.Instant;

public class Goods {
    String name;
    String number;
    int available;
    double price;
    Instant expired;

    @Override
    public String toString() {
        return "Goods{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", available=" + available +
                ", price=" + price +
                ", expired=" + expired +
                '}';
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    // Коструктор для теста
    public Goods(String name) {


        this.name = name;
        this.number = ((int) (Math.random() * 1000)) + name;
        this.available = (int) (Math.random() * 100);
        this.price = Math.random() * 100;
        this.expired = Instant.now().plusSeconds(((int) (Math.random() * 10)) * 60 * 60 * 24);
    }
}