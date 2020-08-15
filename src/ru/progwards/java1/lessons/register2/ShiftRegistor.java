package ru.progwards.java1.lessons.register2;

import java.io.IOException;

public class ShiftRegistor {



        public static void left(Register value){
            for (int i=1;i<value.get().length-1;i++) {
                value.get()[i]=value.get()[i+1];
            }
            value.get()[value.get().length-1].bit=false;
        }

        public static void right(Register value){

            for (int i=value.get().length-1; i>1; i--) {
               value.get()[i]=value.get()[i-1];
            }

        }


        public static void main(String[] args) {
            ByteRegister br1=new ByteRegister((byte) -12);
            IntRegister ir2=new IntRegister(-11);


            System.out.println("br="+br1.toString());
            System.out.println("ir="+ir2.toString());
            right(br1);
            System.out.println(br1.toString());
            left(br1);
            left(br1);
            System.out.println(br1.toString());

            Counter.inc(ir2);
            Counter.inc(ir2);
            System.out.println(ir2.toString());

        }


    }

