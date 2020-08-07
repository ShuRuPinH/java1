package ru.progwards.java1.lessons.register2;

import ru.progwards.java1.lessons.register1.ByteRegister;

public class Counter {

    public static void inc(Register value) {
        boolean t = true;
        for (int i = value.arr.length ; i >0; i--) {
            if (value.arr[i].bit && t) {
                value.arr[i].bit = false;
                t = true;
            } else if (value.arr[i].bit) t = false;
            else if (value.arr[i].bit != true && t) {
                value.arr[i].bit = true;
                t = false;
            }

        }
    }

    public static void dec(Register value) {
        boolean t = true;
        for (int i = value.arr.length; i > 0; i--) {
            if (value.arr[i].bit && t) {
                value.arr[i].bit = false;
                t = false;
            }
            if (value.arr[i].bit!=true && t){
                value.arr[i].bit=true; t=true;

            }

        }
    }


}