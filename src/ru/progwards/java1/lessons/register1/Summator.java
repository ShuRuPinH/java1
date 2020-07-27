package ru.progwards.java1.lessons.register1;

public class Summator {

    public static boolean add(ByteRegister value1, ByteRegister value2){
        boolean v1=false,v2=false;
        for (byte i=7; i>0;i--){
            if (value1.bit8[i].bit && value2.bit8[i].bit) {
               if (v1)  value1.bit8[i].bit=true;
               else {value1.bit8[i].bit=false;}
               v1=true;
            }
            else if (value1.bit8[i].bit || value2.bit8[i].bit){
                if (v1) {
                    value1.bit8[i].bit=false;

                }
                else {value1.bit8[i].bit=true;
                v1=false;}

            }
            else if ((value1.bit8[i].bit!=true && value2.bit8[i].bit!=true && v1)) {
                value1.bit8[i].bit=true;
                v1=false;
            }

        }
        System.out.println(value1.toString());
        if (v1) return false;
        return true;
    }


}
