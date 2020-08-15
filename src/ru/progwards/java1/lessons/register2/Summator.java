package ru.progwards.java1.lessons.register2;

public class Summator {



    public static boolean add(Register value1, Register value2){
         if (value1.get()[0].bit ==true && value2.get()[0].bit==true) {
                //
         }
         if (value1.get()[0].bit == true){

         }


        boolean v1=false,v2=false;
        int lens=0;
        if (value1.get().length > value2.get().length) lens=value1.get().length;
        else lens=value2.get().length;
        for (int i=lens-1; i>0;i--){
            if (value1.get()[i].bit && value2.get()[i].bit) {
                if (v1)  value1.get()[i].bit=true;
                else {value1.get()[i].bit=false;}
                v1=true;
            }
            else if (value1.get()[i].bit || value2.get()[i].bit){
                if (v1) {
                    value1.get()[i].bit=false;

                }
                else {value1.get()[i].bit=true;
                    v1=false;}

            }
            else if (value1.get()[i].bit!=true && value2.get()[i].bit!=true && v1) {
                value1.get()[i].bit=true;
                v1=false;
            }
            // if (value1.bit8[i].bit!=true && value2.bit8[i].bit!=true )

        }

        if (v1) return false;
        return true;
    }


    private Register toTwosComplement(Register value){
        for (int i=value.get().length-1; i>=0; i--){
            if (value.get()[i].bit==true)value.get()[i].bit=false;
            else value.get()[i].bit= true;
        }
        Counter.dec(value);
        return value;
    }
    public static void main(String[] args) {
        ByteRegister br1=new ByteRegister((byte)-14);
        ByteRegister br2=new ByteRegister((byte)15);
        System.out.println(br1.toString()+" + " );
        System.out.println(br2.toString()+" = " );
        add(br1,br2);
        System.out.println(br1.toString());

    }

}
