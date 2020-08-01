package ru.progwards.java1.lessons.bigints;

import java.math.BigDecimal;
import java.math.BigInteger;

public class ShortInteger extends AbsInteger{
    short num;

    public ShortInteger(short num) {
        this.num = num;
    }
    @Override
    public String toString() {
        return String.valueOf(num);
    }


    @Override
    public BigInteger value() {

        return new BigInteger(this.toString());
    }
}


