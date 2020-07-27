package ru.progwards.java1.lessons.register1;

public class ShiftRegister {

    public static void left(ByteRegister value){
        for (byte i=0;i<7;i++) {
            value.bit8[i].bit=value.bit8[i+1].bit;
        }
    }

    public static void right(ByteRegister value){
        for (byte i=7;i>0;i--) {
            value.bit8[i].bit=value.bit8[i-1].bit;
        }
    }



}
