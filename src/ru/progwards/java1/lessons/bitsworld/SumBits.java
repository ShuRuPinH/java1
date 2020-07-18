package ru.progwards.java1.lessons.bitsworld;

public class SumBits {
    public static int sumBits(byte value){

        int val= value & 0b00000000_00000000_00000000_11111111;
        byte rz=0;
         // System.out.println(Integer.toBinaryString(val));
            while (val>0) {
            rz+=1 & val;
            val >>=1;
              // System.out.println(Integer.toBinaryString(val));
            }
        return (int)rz;
    }

    public static void main(String[] args) {
        System.out.println("rz="+sumBits((byte) -10 ));
    }
}
