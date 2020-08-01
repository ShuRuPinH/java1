package ru.progwards.java1.lessons.bigints;

import java.math.BigDecimal;
import java.math.BigInteger;

public class BigAlgebra {

    static BigDecimal tBDpowMain = BigDecimal.ONE;
    static BigDecimal tBDpow;
    static int n2 = 1;


   static BigDecimal fastPow(BigDecimal num, int pow) {
        int tt = pow;
        do {
            tBDpow = num;
            tempFunc(tt);
            // System.out.println("tt="+tt);
            tBDpowMain = tBDpowMain.multiply(tBDpow);
            tBDpow = num;
            tt -= n2;
        } while (tt > 0);
        //System.out.println("tt.end=" + tt);
        if (tt < 0) {
            for (byte i = 1; i <= Math.abs(tt); i++) {
                tBDpowMain = tBDpowMain.divide(num);
            }
        }
        // System.out.println(tBDpowMain);
        return tBDpowMain;
    }

    static int tempFunc(int temp1) {
        n2 = 1;
        int count2 = 0;
        while (temp1 > 0) {
            n2 *= 2;
            temp1 = temp1 / n2;
            count2++;
        }
        for (byte i = 1; i <= count2; i++) {
            tBDpow = tBDpow.multiply(tBDpow);
            // System.out.println(tBDpow);
        }
        // System.out.println("n2=" + n2);
        // System.out.println("count2=" + count2);
        return n2;
    }

    static BigInteger fibonacci(int n) {
        BigInteger f1 = new BigInteger("0");
        BigInteger f2 = new BigInteger("1");
        BigInteger f;
        int i = 1;
        do {
            f = f1.add(f2);
            f1 = f2;
            f2 = f;
            i++;
        } while (i < n);
        return f;
    }

 /*   public static void main(String[] args) {
        BigAlgebra ba = new BigAlgebra();
        System.out.println("pow:" +ba.fastPow(new  BigDecimal("3.2"), 2));

        System.out.println("fibo:" + ba.fibonacci(12));

    }*/


}
