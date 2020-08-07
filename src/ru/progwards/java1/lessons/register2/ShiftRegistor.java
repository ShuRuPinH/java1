package ru.progwards.java1.lessons.register2;

public class ShiftRegistor {



        public static void left(Register value){
            for (int i=1;i<value.arr.length;i++) {
                value.arr[i].bit=value.arr[i+1].bit;
            }
            value.arr[value.arr.length-1].bit=false;
        }

        public static void right(Register value){
            System.out.println(value.arr.length);
            for (int i=value.arr.length; i>1; i--) {
               // value.arr[i-1].bit=value.arr[i-2].bit;
            }

        }

        public static void main(String[] args) {
            IntRegister br1=new IntRegister(128);
            System.out.println(br1.arr.length);
            System.out.println(br1.toString());
            right(br1);

        }


    }

