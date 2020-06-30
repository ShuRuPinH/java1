package ru.progwards.java1.lessons.classes;

public class Hamster extends Animal {
    public Hamster(double weight) {
        super(weight);
    }

    @Override
    public Animal.AnimalKind getKind() {
        return AnimalKind.HAMSTER;
    }

    @Override
    public Animal.FoodKind getFoodKind() {
        return FoodKind.CORN;
    }

    @Override
    public double getFoodCoeff() {
        return 0.03;
    }
// enter
    public static void main(String[] args) {
        System.out.println(new Hamster(10));
        System.out.println(new Hamster(10).toStringFull());
    }
}

