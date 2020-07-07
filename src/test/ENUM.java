package test;

import java.util.Date;
public class ENUM {
    enum Day{
        UNDEFINED(0,7),
        MON(1,8),
        TUE(2,9),
        WED(3,10),
        THU(4,11),
        FRI(5,12),
        SAT(6,13),
        SUN(7,14);

        private int num;
        private int num2;

        Day(int num, int num2) {
            this.num = num;
            this.num2=num2;

        }

        public int getNum(int p) {
            if (p==1)   return num;
            if (p==2)   return num2;
            else return 444;
        }

        public static Day dayByNum(int num) {
            for (Day d : values()) {
                if (d.num == num )
                    return d;
            }
            return UNDEFINED;
        }
    }
    public static void main(String[] args) {
        System.out.println(Day.dayByNum(7));
        System.out.println(Day.valueOf("SUN"));
        System.out.println(Day.SUN.num);
        System.out.println(Day.SUN.num2);

        System.out.println(Day.dayByNum(7).num2);

        System.out.println(Day.FRI.getNum(2));


    }
}
