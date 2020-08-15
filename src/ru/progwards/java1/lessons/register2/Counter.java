package ru.progwards.java1.lessons.register2;

import ru.progwards.java1.lessons.register1.ByteRegister;

public class Counter {

    public static void inc(Register value) {
        boolean t = true;
        for (int i = value.get().length-1;  i >0; i--) {
            if (value.get()[i].bit && t) {
                value.get()[i].bit = false;
                t = true;
            } else if (value.get()[i].bit) t = false;
            else if (value.get()[i].bit != true && t) {
                value.get()[i].bit = true;
                t = false;
            }

        }
    }

    public static void dec(Register value) {
        boolean t = true;
        for (int i = value.get().length-1; i > 0; i--) {
            if (value.get()[i].bit && t) {
                value.get()[i].bit = false;
                t = false;
            }
            if (value.get()[i].bit!=true && t){
                value.get()[i].bit=true; t=true;

            }

        }
    }


}