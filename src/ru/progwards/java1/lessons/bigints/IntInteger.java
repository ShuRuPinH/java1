package ru.progwards.java1.lessons.bigints;

import java.math.BigDecimal;
import java.math.BigInteger;

public class IntInteger extends AbsInteger {
    int num;

    public IntInteger(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return String.valueOf(num);
    }


    @Override
    public BigInteger value() {

        return new BigInteger (this.toString());
    }
}


