package ru.progwards.java1.lessons.abstractnum;



abstract class Number {
Number num;

    public Number(Number num) {
        this.num = num;
    }

    public Number() {

    }

    public abstract Number mul(Number num);
public abstract Number div(Number num);
public abstract Number newNumber(String strNum);
public abstract String toString();


}
