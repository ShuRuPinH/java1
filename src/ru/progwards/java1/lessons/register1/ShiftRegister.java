package ru.progwards.java1.lessons.register1;

public class ShiftRegister {

    public static void left(ByteRegister value){
        for (byte i=0;i<7;i++) {
            value.bit8[i].bit=value.bit8[i+1].bit;
        }
        value.bit8[7].bit=false;
    }

    public static void right(ByteRegister value){
        for (byte i=7;i>0;i--) {
            value.bit8[i].bit=value.bit8[i-1].bit;
            }
        value.bit8[0].bit=false;
    }

    public static void main(String[] args) {
        ByteRegister br1=new ByteRegister((byte)-128);
        right(br1);
        System.out.println(br1.toString());
    }


}
