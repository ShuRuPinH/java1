package ru.progwards.java1.lessons.interfaces;

public class Food implements CompareWeight {
    int weight;

    public Food(int weight) {
        this.weight = weight;
    }

    @Override
    public double getWeight() {
        return weight;
    }

}
