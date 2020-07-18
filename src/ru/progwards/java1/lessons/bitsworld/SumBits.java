package ru.progwards.java1.lessons.bitsworld;

public class SumBits {
    public static int sumBits(byte value){
        int rz=0;
            while (value>0) {
            rz+=1 & value;
            value >>=1;
            }
        return rz;
    }

    public static void main(String[] args) {
        System.out.println("rz="+sumBits((byte) 7 ));
    }
}
