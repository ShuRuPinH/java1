package ru.progwards.java1.lessons.bitsworld;

public class Binary {
    byte num;
    public Binary(byte num){
    this.num=num;
    }
    public String toString(){
        String str="";
        for (int i=0; i<=7;i++){
            str=(num & 1) +str;
            num>>=1;
        }
        return str;
    }

    public static void main(String[] args) {
        System.out.println(new Binary((byte)127).toString());
    }
}
