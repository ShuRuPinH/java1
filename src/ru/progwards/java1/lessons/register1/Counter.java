package ru.progwards.java1.lessons.register1;

public class Counter {

    public static void inc(ByteRegister value) {
        boolean t = true;
        for (byte i = 7; i >= 0; i--) {
                if (value.bit8[i].bit && t) {
                value.bit8[i].bit = false;
                t = true;
            } else if (value.bit8[i].bit) t = false;
            else if (value.bit8[i].bit != true && t) {
                value.bit8[i].bit = true;
                t = false;
            }

        }
    }

    public static void dec(ByteRegister value) {
        boolean t = true;
        for (byte i = 7; i >= 0; i--) {
            if (value.bit8[i].bit && t) {
                value.bit8[i].bit = false;
                t = false;
            }
            if (value.bit8[i].bit!=true && t){
                value.bit8[i].bit=true; t=true;

            }

        }
    }


}
