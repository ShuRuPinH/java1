package ru.progwards.java1.SeaBattle.shurupin;


import org.checkerframework.checker.units.qual.A;
import ru.progwards.java1.SeaBattle.SeaBattle;
import ru.progwards.java1.SeaBattle.SeaBattle.FireResult;

import java.lang.reflect.Array;
import java.util.*;

public class SeaBattleAlg {
public int vicpoint=0;
    public SeaBattle seaBattle;


    List ship = new ArrayList();
    int[][] pole = new int[10][10];
     int shots=0;

    public void battleAlgorithm(SeaBattle seaBattle) {
        this.seaBattle = seaBattle;

        boolean on=true;
        fire(0,0);
        fire(9,9);
        fire(0,9);
        fire(9,0);

        while ( on) {



            if (vicpoint==10  ) on=false;


            toScr(pole);

            next();

        }
    }
/////////////KILL////////////////////
    void kill(int x, int y) {
        System.out.println("kill   "+x+":"+y +"    vinpoint="+vicpoint);
        toScr(pole);
   /// corners
        if (y==9 && x==9){ fire (x-1,y);
            fire(x,y-1);
        }
        if (y==0 && x==0){ fire (x+1,y);
            fire(x,y+1);
        }

        if (y==0 && x==9){ fire (x-1,y);
            fire(x,y+1);
        }
        if (y==9 && x==0){ fire (x+1,y);
            fire(x,y-1);
        }

        /////sides///
        if (y==0){ fire(x+1,y);
            fire(x+1,y);
        }
        if (y==9){ fire(x+1,y);
            fire(x+1,y);
        }
        if (x==0){ fire(x,y+1);
            fire(x,y-1);
        }
        if (x==9){ fire(x,y+1);
            fire(x,y-1);
        }

       switch (fire(x, y + 1)) {
            case 1:
                System.out.println("case 1   ----KILL ");
                fire(x,y+2);
            case 2:
                System.out.println("case 1   ----KILL ");




            case 8: System.out.println("case 3    ----KILL");
               fire(x+1, y);
            case -1: System.out.println("case 4   ----KILL");
               next();
        }

        ////////////  checking  ////
        if (y+1<10 && pole[y+1][x]==1){

            System.out.println("kil1");kill(x, y+1);
        }

        else if (y-1>0 && pole[y-1][x]==1){

            System.out.println("kil2"); kill(x, y-1);
        }

        else   if (x+1 <10 && pole[y][x+1]==1){

            System.out.println("kil3");kill(x+1,y);
        }

        else   if (x-1 >0 && pole[y][x-1]==1){

            System.out.println("kil4");kill(x-1,y);
        }

    }
/////////DZONE///////////////////
    void dzone() {

        System.out.println("************* start of deadline *******************");
        toScr(pole);

        int [] xd = new int[ship.size()];
        int [] yd = new int[ship.size()];

        if (ship.size()==1){
            int [] temp= (int []) ship.get(0);
            int ty = temp [1];
            int tx = temp [0];
            int decty=ty-1; int incty=ty+1;
            int dectx=tx-1; int inctx=tx+1;

            if (dectx<0) dectx=tx ; if (inctx>9) inctx=tx;
            if (decty<0) decty=ty ; if (incty>9) incty=ty;





            pole [dectx][decty]=8;  pole [dectx][ty]=8;  pole [dectx][incty]=8;
            pole [tx][decty]=8;     pole [tx][ty]=8;  pole [tx][incty]=8;
            pole [inctx][decty]=8;  pole [inctx][ty]=8;  pole [inctx][incty]=8;



            toScr(pole);


        }
        else {
            System.out.println("else deadzone*");
            int[] s0 = (int[]) ship.get(0);
            int[] s1 = (int[]) ship.get(1);

            System.out.println(Arrays.toString(s0)+"       "+Arrays.toString(s1));
            System.out.println(s0[1]==s1[1]);


        if (s0[0]==s1[0])  {System.out.println("else deadzone*   [0]");
            for(int i=0;i<ship.size();i++) {

                int[] temp = (int[]) ship.get(i);
                int ty = temp[1];
                int tx = temp[0];

                int dectx = tx - 1;
                int inctx = tx + 1;

                int decty = ty - 1;
                int incty = ty + 1;


                if (dectx < 0) dectx = tx;
                if (inctx > 9) inctx = tx;
                if (decty < 0) decty = ty;
                if (incty > 9) incty = ty;
                System.out.println(dectx+":"+ty+"      "+tx+":"+ty+"     "+inctx+":"+ty);
                pole [dectx][ty]=8;     pole [tx][ty]=8;  pole [inctx][ty]=8;

            }}
            if (s0[1]==s1[1])  {System.out.println("else deadzone*   [1]");
                for(int i=0;i<ship.size();i++) {

                    int[] temp = (int[]) ship.get(i);
                    int ty = temp[1];
                    int tx = temp[0];

                    int decty = ty - 1;
                    int incty = ty + 1;
                    int dectx = tx - 1;
                    int inctx = tx + 1;

                    if (dectx < 0) dectx = tx;
                    if (inctx > 9) inctx = tx;
                    if (decty < 0) decty = ty;
                    if (incty > 9) incty = ty;
                    System.out.println(tx+":"+decty+"      "+tx+":"+ty+"     "+tx+":"+incty);
                    pole [tx][decty]=8;     pole [tx][ty]=8;  pole [tx][incty]=8;

                }

        }



            toScr(pole);
            System.out.println("************* end of deadline *******************");
        }
      ship.clear();
    }
////////////////////////////////////////////////
/*    void lineF(List ll) {
        System.out.println("line");
        int[] st = (int[]) ll.get(0);
        int[] lt = (int[]) ll.get(ll.size() - 1);


        for (int i = 0; i < ll.size(); i++) {
            int [] temp= (int [])ll.get(i);
            System.out.println(ll.size()+"-size   "+Arrays.toString(temp));
        }


        if (st[0] == lt[0]) {
            if (st[1] > lt[1] && lt[1] - 1 >= 0) fire(st[0], lt[1] - 1);
            else if (st[1] > lt[1]) fire(st[0], st[1] + 1);
            if (st[1] < lt[1] && lt[1] + 1 <= 9) fire(st[0], lt[1] + 1);
            else if (st[1] < lt[1]) fire(st[0], st[1] - 1);
        }
        if (st[1] == lt[1]) {
            if (st[0] > lt[0] && lt[0] - 1 >= 0) fire(lt[0] - 1, lt[1]);
            else if (st[0] > lt[0]) fire(st[0] + 1, st[1]);
            if (st[0] < lt[0] && lt[0] + 1 <= 9) fire(lt[0] + 1, lt[1]);
            else if (st[0] < lt[0]) fire(st[0] - 1, st[1]);
        }

    }*/
//*************///!!!!!!!!!!!///FIRE//////!!!!!!!!!!!!!!!///*********************///
    public Integer fire(int x, int y) {
        if (vicpoint==10) return -1;
        int res=0;
        if (y < 0 || x < 0 || y > 9 || x > 9) return -1;
        if (pole[y][x] != 0) {
            System.out.println(x+","+y+" //בטעמו ןמכו/"+vicpoint+"/  pole="+pole[y][x]);
            return -1;
        }

        SeaBattle.FireResult fireResult = seaBattle.fire(x, y);
        shots++;
        System.out.println(x+"<x,y>"+y+"- shot#"+shots+" -"+fireResult);
        switch (fireResult) {
            case MISS:
                pole[y][x] = 8;
                return 8;

            case DESTROYED:
                pole[y][x] = 2;
                ship.add(new int[]{y, x});
                vicpoint++;
                System.out.println("גûחמג החמם טח פאונ הוסענ");

                dzone();

                return  2;
            case HIT:
                pole[y][x] = 1;
                ship.add(new int[]{y, x});
                kill(x, y);
                return 1;
            default: res=-1;

        }

        return res;
    }


    void toScr(int[][] pl) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(pl[i][j] + "\t");
            }
            System.out.println();
        }
    }


    void next (){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (pole[j][i] == 0) fire(i,j);
            }}
    }

    // main
    public static void main(String[] args) {
        System.out.println("Sea battle");
        SeaBattle seaBattle = new SeaBattle();
        new SeaBattleAlg().battleAlgorithm(seaBattle);
        System.out.println(seaBattle.getResult());
    }
}

