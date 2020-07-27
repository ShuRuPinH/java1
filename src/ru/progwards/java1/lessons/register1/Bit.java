package ru.progwards.java1.lessons.register1;

public class Bit {
    boolean bit;

    public Bit(){

    }

    public Bit(boolean value){
        this.bit=value;
    }

    public String toString() {
        int st=0;
        if (bit == true) st=1;
        return Integer.toString(st);
    }


}
