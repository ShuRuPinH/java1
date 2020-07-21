package ru.progwards.java1.lessons.interfaces;

public interface CompareWeight {


    enum CompareResult{
        LESS, EQUAL, GREATER
    }
    double getWeight();

    default CompareResult compareWeight(CompareWeight smthHasWeigt) {
        int temp = Double.compare(this.getWeight(), smthHasWeigt.getWeight());

        switch (temp){
            case -1: return CompareResult.LESS;
            case 0: return CompareResult.EQUAL;
            case 1: return CompareResult.GREATER;
        }
        return null;
    }





}
