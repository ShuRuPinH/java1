package ru.progwards.java1.lessons.register2;

import java.util.Arrays;

public class IntRegister extends Register{

        Bit []arr =new Bit [32];

        public IntRegister(){


            Arrays.fill(arr,new Bit(false));
        }
        public IntRegister(int value){
            ;
            if (value<0){ arr[0]=new Bit(true);
            value*=-1;}  else {arr[0]=new Bit((false));}
            int temp=value;
            for (int i=31; i>0; i-- ){
                temp = ( value & 0b1);
                if (temp == 1) {
                    arr[i] = new Bit(true);
                } else {
                    arr[i] =new Bit(false);
                }
                value>>=1;
            }

        }

        public String toString(){
            String temp="";

            for (byte i=0; i<=31;i++){
                temp+=arr[i];
            }
            return temp;
        }
        public String toDecString(){
            int temp=0;

            for (int i=1; i<=31; i++){

                int p=0;
                if (arr[i].bit) { p=1;
                for (int j=0; j<31-i;j++) {
                    p*=2;
                }}
                temp+=p;

            }
            if (arr[0].bit) temp*=-1;
            return Integer.toString(temp);
        }

        public static void main(String[] args) {
            IntRegister test = new IntRegister( -25);
            System.out.println(test.toString());
            System.out.println("22");
            System.out.println(test.toDecString());
        }
    }

