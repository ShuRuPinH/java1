package ru.progwards.java1.lessons.bitsworld;

public class CheckBit {

    public static int checkBit(byte value, int bitNumber){
        int val= value & 0b00000000_00000000_00000000_11111111;
        for (int i=0 ; i<bitNumber; i++){
            val >>=1;
            }
        // System.out.println(Integer.toBinaryString(val));
        return val & 1;

    }

    public static void main(String[] args) {
        System.out.println(checkBit((byte) 8,5));
    }
}
