package ru.progwards.java1.lessons.bigints;

import java.math.BigInteger;
import java.math.MathContext;
import java.util.Arrays;

public class ArrayInteger {
    byte[] digits;

    public ArrayInteger(int n) {
        digits = new byte[n];
    }


    void fromInt(BigInteger value) {
        MathContext mc = new MathContext(digits.length);
        BigInteger temp = value;
        MathContext mathContext = new MathContext(5);


        for (int i =0 ; i < digits.length ; i++) {

            digits[i] = temp.remainder(BigInteger.TEN).byteValue();
            temp = temp.divide(BigInteger.TEN);

        }

    }

    BigInteger toInt() {
        String temp = "";
        for (int i = 0; i < digits.length; i++) {
            temp = temp + digits[i];
        }
        return new BigInteger(temp);
    }

    boolean add(ArrayInteger num) {
        byte cnt=0;
        if (this.digits.length<num.digits.length) return false;
        ArrayInteger aTemp= new ArrayInteger(this.digits.length);
        aTemp.fromInt(num.toInt());
        for (int i=0; i< this.digits.length ; i++){
            byte temp = (byte) (this.digits[i] +aTemp.digits[i] +cnt);
            if (temp >= 10){
                this.digits[i]= (byte) ( temp%10);
                cnt=1;
            }
            else {
                this.digits[i]=temp;
                cnt=0;
            }
        }
        if (cnt!=0){Arrays.fill(this.digits, (byte) 0);
            return false;
        }
        return true;

    }

/*
    public static void main(String[] args) {
        ArrayInteger arr = new ArrayInteger(7);
        ArrayInteger arr2 = new ArrayInteger(7);


        arr.fromInt(new BigInteger("0123456789"));
        arr2.fromInt(new BigInteger("0123456789"));

        System.out.println(Arrays.toString(arr.digits));
        System.out.println(arr.toInt());

        System.out.println();
        System.out.println(arr.add(arr2));
        System.out.println(Arrays.toString(arr.digits));

    }*/

}
