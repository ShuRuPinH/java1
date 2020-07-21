package ru.progwards.java1.lessons.interfaces;

import java.util.Objects;

public class Animal implements FoodCompare, CompareWeight {
    double weight;

    @Override
    public boolean equals(Object anObject) {

        if (this == anObject) return true;
        if (anObject == null || getClass() != anObject.getClass()) return false;
        Animal animal = (Animal) anObject;
        System.out.println("sds");

        return Double.compare(animal.getWeight(), this.getWeight()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(weight);
    }

    enum AnimalKind {ANIMAL, COW, HAMSTER, DUCK}
    enum FoodKind { UNKNOWN, HAY, CORN}
    public Animal(double weight){
        this.weight=weight;
    }
    public AnimalKind getKind(){
    return AnimalKind.ANIMAL;
    }
    public FoodKind getFoodKind() {
        return FoodKind.UNKNOWN;
    }
    @Override
    public String toString(){
       return "I am "+getKind()+", eat "+getFoodKind();
    }

    public double getWeight(){
        return weight;
    }
    public double getFoodCoeff(){
        return 0.02;
    }
    public double calculateFoodWeight(){
           return weight*getFoodCoeff();
    }
    public String toStringFull(){
        return "I am "+getKind()+", eat "+getFoodKind()+" "+calculateFoodWeight();
    }

    public double getFood1kgPrice(){

        switch (this.getFoodKind()){
            case CORN:return  50;
            case HAY:return  20;
            default: UNKNOWN:return  0;
        }

    }

    public double getFoodPrice(){
        return this.calculateFoodWeight()*getFood1kgPrice();
    }

    @Override
    public int compareFoodPrice(Animal aminal){
    return Double.compare(this.getFoodPrice(), aminal.getFoodPrice());
    }

    //enter

    public static void main(String[] args) {
        System.out.println(new Animal(24));
       Animal cow= new Cow(40);
       Animal duck=new Duck(20);
        Double dd=11d;
        Integer bb=11;
        System.out.println("weight   "+cow.equals(duck));

        System.out.println("cow 1kg"+cow.getFood1kgPrice());
        System.out.println("cow prs"+cow.getFoodPrice());

        System.out.println("duck 1kg"+duck.getFood1kgPrice());
        System.out.println("duck prs"+duck.getFoodPrice());

        System.out.println("cow.comp.duck="+cow.compareFoodPrice(duck));
    }
}

