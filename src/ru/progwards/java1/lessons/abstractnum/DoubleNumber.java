package ru.progwards.java1.lessons.abstractnum;

public class DoubleNumber extends Number {

   double num=0d;
        public DoubleNumber(double num){
            super();
            this.num=num;
        }

        @Override
        public Number mul(Number num) {
            double temp;
            DoubleNumber n= (DoubleNumber) num;
            temp=this.num* n.num;
            return new DoubleNumber(temp);

        }

        @Override
        public Number div(Number num) {
            double temp;
            DoubleNumber n= (DoubleNumber)num;
            temp=this.num/n.num;
            return new DoubleNumber(temp);
        }

        @Override
        public DoubleNumber newNumber(String strNum) {
            DoubleNumber n= new DoubleNumber(Double.parseDouble(strNum));
            return n;
        }

        @Override
        public String toString() {

            return String.valueOf(num);
        }
    }

