package ru.progwards.java1.lessons.register1;

import java.util.Arrays;

public class ByteRegister {
     Bit []bit8 =new Bit[8];

     public ByteRegister(){
        Arrays.fill(bit8,new Bit(false));
    }
    public ByteRegister(byte value){
        byte temp=value;
        for (int i=7; i>=0; i-- ){
            temp = (byte) ( value & 1);
            if (temp == 1) {
                bit8[i] = new Bit(true);
            } else {
                bit8[i] =new Bit(false);
            }
            value>>=1;
        }
        //System.out.println(Arrays.toString(bit8));
    }

    public String toString(){
         String temp="";

         for (byte i=0; i<=7;i++){
                temp+=bit8[i];
         }
         return temp;
    }
    public String toDecString(){
         int temp=0;

         for (byte i=0; i<=7; i++){
             int dd=0;
             int p=1;
             if (bit8[i].bit) dd=1;
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
