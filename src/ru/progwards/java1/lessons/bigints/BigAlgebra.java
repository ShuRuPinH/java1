package ru.progwards.java1.lessons.bigints;

import java.math.BigDecimal;
import java.math.BigInteger;

public class BigAlgebra {

    static BigDecimal tBDpowMain = BigDecimal.ONE;
    static BigDecimal tBDpow;
    static int n2 = 1;


    static BigDecimal fastPow(BigDecimal num, int pow) {
        String powstr = Integer.toBinaryString(pow);
        BigDecimal temp = BigDecimal.ONE;

        for (int i = 0; i < powstr.length() - 1; i++) {
            switch (powstr.charAt(i)) {
                case '1': temp = (temp.multiply(num)).multiply((temp.multiply(num)));
                    break;
                case '0': temp = temp.multiply(temp);
                    break;
            }
        }
        switch (powstr.charAt(powstr.length() - 1)) {
            case '1':   return temp.multiply(num);
            case '0':   return temp.multiply(BigDecimal.ONE);
        }

        return temp;
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

    public static void main(String[] args) {
        BigAlgebra ba = new BigAlgebra();
        System.out.println("pow:" + ba.fastPow(new BigDecimal("15"), 6));

        System.out.println("fibo:" + ba.fibonacci(12));

    }


}
