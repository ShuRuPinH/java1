package ru.progwards.java1.lessons.classes;

public class Animal {
    double weight;
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

    //enter

    public static void main(String[] args) {
        System.out.println(new Animal(24));
    }
}

