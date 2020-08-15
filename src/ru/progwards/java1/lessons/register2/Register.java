package ru.progwards.java1.lessons.register2;

import java.util.Arrays;

public class Register  {
    Bit [] arr ;
    Register reg;

    public  Register(Register reg) { this.reg =reg;

    }

    public Register() {

    }

    public Bit[] get(){
        return arr;
    }
}
