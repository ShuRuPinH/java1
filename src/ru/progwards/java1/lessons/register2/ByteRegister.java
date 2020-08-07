package ru.progwards.java1.lessons.register2;

import java.util.Arrays;

public class ByteRegister extends Register {
    Bit []arr =new Bit[8];

    public ByteRegister(){

        Arrays.fill(arr,new Bit(false));
    }
    public ByteRegister(byte value){

        byte temp=value;
        for (int i=7; i>=0; i-- ){
            temp = (byte) ( value & 1);
            if (temp == 1) {
                arr[i] = new Bit(true);
            } else {
                arr[i] =new Bit(false);
            }
            value>>=1;
        }
        //System.out.println(Arrays.toString(arr));
    }

    public String toString(){
        String temp="";

        for (byte i=0; i<=7;i++){
            temp+=arr[i];
        }
        return temp;
    }
    public String toDecString(){
        int temp=0;

        for (byte i=0; i<=7; i++){
            int dd=0;
            int p=1;
            if (arr[i].bit) dd=1;
            for (byte j=0; j<7-i;j++) {
                p*=2;
            }
            temp+=p*dd;

        }
        return Integer.toString(temp);
    }

    public static void main(String[] args) {
        ByteRegister test = new ByteRegister((byte) 25);
        System.out.println(test.toString());
        System.out.println("22");
        System.out.println(test.toDecString());
    }
}
