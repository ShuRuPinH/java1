package test;


import java.util.Scanner;

/*
Программа выводить месяц в читаемом виде календарный месяц,
любого мяесяца   григорианского календаря,
начиная с первого дня его действия — 15 октября 1582 года.

для рачета нужно ввести:
mm - месяц года
yyyy - год
в psvm

 */
public class Calendar {
    static int dINmob=0;
    public Day cur ;
    Calendar(){}

    enum Day{

        MON (1),
        TUE (2),
        WED (3),
        THU (4),
        FRI (5),
        SAT (6),
        SUN (7),
        NAN (0);

        int d;

        Day (int d){
            this.d=d;
        }
        public static Day byNum(int num){
            Day dw=NAN;
            for (Day n : values())
                if (n.d==num) dw=n;

            return dw;
    }}
        enum Month{
            JAN (1,31),
            FEB (2,28),
            MAR (3,31),
            APR (4,30),
            MAY (5,31),
            JUN (6,30),
            JUL (7,31),
            AUG (8,31),
            SEP (9,30),
            OCT (10,31),
            NOV (11,30),
            DEC (12,31),
            NAN (44,44);

            int n;
            int ds;

            Month (int n,int ds){
                this.n=n; this.ds=ds;
            }
            public static Month byNum(int num){
                for (Month n : values())
                    if (n.n==num) return n;
                    return NAN;
            }

            public static int DaysBN(int num) {
                for (Month n : values()) {
                    if (n.n == num) return n.ds;
                }
                return 0;
            }}
    // Определяет число дней в месяце
    int dInMon(int mm,double yyyy){
       int temp=Month.DaysBN(mm);

       if (mm==2){ if (yyyy %4==0 || yyyy %400 ==0) temp=29;
           if (yyyy %100==0) temp=28;
           else temp=28;}
       return temp;
   }
    // ВВОД 2 СРОКАМИ НИЖЕ ;)


    public static void main(String[] args) {
        int mm=07;  //  месяц нужного года
        int yyyy=1982; //  требуемый год
        Scanner in = new Scanner(System.in);

        System.out.println("month?");
        if (in.hasNextInt()){
            mm=in.nextInt();
        }
        else System.out.println("error");

        System.out.println("year?");
        if (in.hasNextInt()){
            yyyy=in.nextInt();
        }
        else System.out.println("error");

        if (mm>12 || yyyy <1583) System.out.println("!! wrong month or year !!");

        dINmob=new Calendar().dInMon(mm,yyyy);
        System.out.println(" "+Month.byNum(mm)+"_"+yyyy+"   (1st - "+ Day.byNum(Fst(mm,yyyy))+" & "+dINmob+" days)");
        print(mm,yyyy);
    }
    // формирует строку календаря
    public static String calendStr(Day d,int f){
        Integer fi=f-6;
        String ws5="";
        String ws6="";
        String fs=" ";
        if (fi>0)fs=Integer.toString(fi);
        Integer ww5=(fi+28);
        if (ww5<=dINmob) ws5=Integer.toString(ww5);
        Integer ww6=(fi+35);
        if (ww6<=dINmob) ws6=Integer.toString(ww6);
        return d+"\t\t"+fs+"  "+(fi+7)+"  "+(fi+14)+"  "+(fi+21)+"  "+ws5+"  "+ws6;
    }
    // выводит построчно календарь
    public static void print(int mm,int yyyy){
        Day dpr=Day.MON;
        int st=Fst(mm,yyyy);
        for (int i=1; i<=7; i++){
            System.out.println(calendStr(dpr,7-st+i));
            dpr=Day.byNum(1+i);
        }
    }

    // определяет на кокой день приходится первое число
    public static int Fst(int mm, int yyyy) {
        int mon=0;
        int year=0;
        int day=1;
        //       https://ru.wikibooks.org/wiki/Реализации алгоритмов/Вечный календарь
        if (mm==1 || mm==2) {
            year=yyyy-1;
            mon=mm+10;
        }
        else {mon=mm-2;
            year=yyyy; }
        day=(1+(31*mon)/12+year+year/4-year/100+year/400)%7 ;
        if (day==0) day=7;
        return day;
    }
}

