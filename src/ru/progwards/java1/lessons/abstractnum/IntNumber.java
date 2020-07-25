package ru.progwards.java1.lessons.abstractnum;

public class IntNumber extends Number {

    int num=0;
    public IntNumber(int num){
        super();
        this.num=num;
    }

    @Override
    public Number mul(Number num) {
        int temp;
        IntNumber n= (IntNumber)num;
        temp=this.num*n.num;
        return new IntNumber(temp);

    }

    @Override
    public Number div(Number num) {
        int temp;
        IntNumber n= (IntNumber)num;
        temp=this.num/n.num;
        return new IntNumber(temp);
    }

    @Override
    public Number newNumber(String strNum) {
        IntNumber n= new IntNumber(Integer.parseInt(strNum));
        return n;
    }

    @Override
    public String toString() {
        return String.valueOf(num);
    }
}