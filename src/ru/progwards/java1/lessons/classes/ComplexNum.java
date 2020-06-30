package ru.progwards.java1.lessons.classes;

public class ComplexNum {

    int a;
    int b;
    public ComplexNum(int a, int b){
        this.a=a;
        this.b=b;
    }
    @Override
    public String toString(){
        return a+"+"+b+"i";
    }

    public ComplexNum add(ComplexNum num){

        return new ComplexNum(a+num.a,b+num.b);

    }


    public ComplexNum sub(ComplexNum num){

        return new ComplexNum(a-num.a,b-num.b);
    }
    public ComplexNum mul(ComplexNum num){
        return new ComplexNum(a*num.a-b*num.b,b*num.a+a*num.b);//(a + bi) * (c + di) = (a*c - b*d) + (b*c + a*d)i
    }
    public ComplexNum div(ComplexNum num){
        return new ComplexNum((a*num.a+b*num.b)/(num.a*num.a + num.b*num.b),(b*num.a-a*num.b)/(num.a*num.a + num.b*num.b)); //(a + bi) / (c + di) = (a*c + b*d)/(c*c+d*d) + ((b*c - a*d)/(c*c+d*d))i
    }

    //enter

    public static void main(String[] args) {

        System.out.println(new ComplexNum(2,4).add(new ComplexNum(1,5)));
        System.out.println(new ComplexNum(2,4).sub(new ComplexNum(1,5)));
        System.out.println(new ComplexNum(7,4).mul(new ComplexNum(3,5)));
        System.out.println(new ComplexNum(7,4).div(new ComplexNum(3,5)));
    }

}
