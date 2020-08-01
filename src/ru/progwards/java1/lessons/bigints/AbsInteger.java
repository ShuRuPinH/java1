package ru.progwards.java1.lessons.bigints;

import java.math.BigInteger;

public abstract class AbsInteger {
    AbsInteger num;


    static AbsInteger add(AbsInteger num1, AbsInteger num2) {
        BigInteger temp=num1.value().add(num2.value());

        if ( -128 <= temp.intValue() && temp.intValue() <= 127  ) return new ByteInteger(temp.byteValue());
        else if (-32768 <= temp.intValue() && temp.intValue() <= 32767 ) return new ShortInteger(temp.shortValue());
        else return new IntInteger(temp.intValue());
    }
    public String toString() {
        return null;
    }
    public BigInteger value(){
        return null;
    }

   /* public static void main(String[] args) {
        ShortInteger tb= new ShortInteger((short) 32767);
        ByteInteger tb2= new ByteInteger((byte) 127);
        System.out.println(add(tb,tb2));
        System.out.println(add(tb,tb2).getClass());

    }*/
}
