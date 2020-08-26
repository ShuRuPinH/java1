package ru.progwards.java1.SeaBattle.shurupin;

import ru.progwards.java1.SeaBattle.SeaBattle.FireResult;
import ru.progwards.java1.SeaBattle.SeaBattle;
import java.util.*;

public class SeaBattleAlg {
    public int vicpoint;
    public SeaBattle seaBattle;

    int stcnt;


    int[][] pole;
    int shots;


    public void battleAlgorithm(SeaBattle seaBattle) {
        vicpoint = 0;
        stcnt = 0;
        pole = new int[10][10];
        shots = 0;

        this.seaBattle = seaBattle;


        boolean on = true;
    /*    fire(0,0);
        fire(9,9);
        fire(0,9);
        fire(9,0);*/

        while (on) {



            if (vicpoint==10  ) on=false;


        //    toScr(pole);

            next();

        }
    }
/////////////KILL////////////////////
void kill(int x, int y) {
    //   System.out.println("kill   "+x+":"+y +"    vinpoint="+vicpoint);
    //   toScr(pole);
    /// corners
    if (y == 9 && x == 9) {
        fire(x - 1, y);
        fire(x, y - 1);
    }
    if (y == 0 && x == 0) {
        fire(x + 1, y);
        fire(x, y + 1);
    }

    if (y == 0 && x == 9) {
        fire(x - 1, y);
        fire(x, y + 1);
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
            //    System.out.println("case 1   ----KILL ");
            fire(x, y + 2);
        case 2:
            //    System.out.println("case 1   ----KILL ");


        case 8: //System.out.println("case 3    ----KILL");
            fire(x + 1, y);
        case -1:// System.out.println("case 4   ----KILL");
            next();
        default:
            next();
    }

    ////////////  checking  ////
    if (y+1<10 && pole[y+1][x]==1) {
        stcnt++;
        // System.out.println("kil1");
        if (stcnt < 2) kill(x, y + 1);
        else next();
    } else if (y-1>0 && pole[y-1][x]==1) {
        stcnt++;
        if (stcnt < 2) kill(x, y - 1);
        else next();
    } else   if (x+1 <10 && pole[y][x+1]==1) {
        stcnt++;
        if (stcnt < 2)
            //System.out.println("kil3");
            kill(x + 1, y);
        else next();
    } else if (x - 1 > 0 && pole[y][x - 1] == 1) {
        stcnt++;
        if (stcnt < 2)
            //System.out.println("kil4");
            kill(x - 1, y);
        else next();
    }

}

    ///+++++++++//dz////DZONE////dz///***********************************////////////
    void dzone(int x, int y) {


        //   System.out.println("************* start of deadline ********** /ship size =" + ship.size() + " /*********");
        //  toScr(pole);

        mark(x, y);


        if (y + 1 < 10 && pole[y + 1][x] == 1) {
            mark(x, y + 1);
            if (y + 2 < 10 && pole[y + 2][x] == 1) {
                mark(x, y + 2);
                if (y + 3 < 10 && pole[y + 3][x] == 1) {
                    mark(x, y + 3);
                }
            }
        }
        if (y - 1 > 0 && pole[y - 1][x] == 1) {
            mark(x, y - 1);
            if (y - 2 > 0 && pole[y - 2][x] == 1) {
                mark(x, y - 2);
                if (y - 3 > 0 && pole[y - 3][x] == 1) {
                    mark(x, y - 3);
                }
            }
        }
        if (x + 1 < 10 && pole[y][x + 1] == 1) {
            mark(x + 1, y);
            if (x + 2 < 10 && pole[y][x + 2] == 1) {
                mark(x + 2, y);
                if (x + 3 < 10 && pole[y][x + 3] == 1) {
                    mark(x + 3, y);
                }
            }
        }
        if (x - 1 > 0 && pole[y][x - 1] == 1) {
            mark(x - 1, y);
            if (x - 2 > 0 && pole[y][x - 2] == 1) {
                mark(x - 2, y);
                if (x - 3 > 0 && pole[y][x - 3] == 1) {
                    mark(x - 3, y);
                }
            }
        }

        //    toScr(pole);
        //    System.out.println("************* end of deadline *******************");


    }

    ////////////////////////////////////////////////
    void mark(int x, int y) {
        //     System.out.println("************* working MARK of deadline ********start****** x=" + x + "* y=" + y + "****");
        int ty = x;
        int tx = y;
        int decty = ty - 1;
        int incty = ty + 1;
        int dectx = tx - 1;
        int inctx = tx + 1;

        if (dectx < 0) dectx = tx;
        if (inctx > 9) inctx = tx;
        if (decty < 0) decty = ty;
        if (incty > 9) incty = ty;

        pole[dectx][decty] = 8;
        pole[dectx][ty] = 8;
        pole[dectx][incty] = 8;
        pole[tx][decty] = 8;
        pole[tx][ty] = 8;
        pole[tx][incty] = 8;
        pole[inctx][decty] = 8;
        pole[inctx][ty] = 8;
        pole[inctx][incty] = 8;


        //   toScr(pole);
        //   System.out.println("************* working MARK of deadline *********end**********");
    }
//*************///!!!!!!!!!!!///FIRE//////!!!!!!!!!!!!!!!///*********************///
    public Integer fire(int x, int y) {
        if (vicpoint==10) return -1;
        int res = -1;
        if (y < 0 || x < 0 || y > 9 || x > 9) return -1;
        if (pole[y][x] != 0) {
            //   System.out.println(x + "," + y + " /*** Hitting place ***  / deads:" + vicpoint + "/  pole=" + pole[y][x]);
            //   toScr(pole);
            return -1;
        }

        SeaBattle.FireResult fireResult = seaBattle.fire(x, y);
        shots++;
        //    System.out.println(x + "<x,y>" + y + "- shot#" + shots + " -" + fireResult);
        switch (fireResult) {
            case MISS:
                pole[y][x] = 8;
                return 8;

            case DESTROYED:
                pole[y][x] = 2;

                vicpoint++;
                //        System.out.println("call DZONE from DESTROYED");

                dzone(x, y);

                return  2;
            case HIT:
                pole[y][x] = 1;

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


    void next() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (pole[j][i] == 0) fire(i, j);
            }
        }

    }

    void next3() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (pole[j][i] == 0) fire(i, j);
            }
        }

    }

    // main
    public static void main(String[] args) {

/*        int score=0;
        for (int i =1; i<=1000; i++){
            SeaBattle seaBattle = new SeaBattle();
        new SeaBattleAlg().battleAlgorithm(seaBattle);
        score+= seaBattle.getResult();
    }
        System.out.println("Sea battle score ="+score/1000);*/

        System.out.println("Sea battle");
        double res = 0;
        SeaBattleAlg alg = new SeaBattleAlg();
        for (int i = 0; i < 1000; i++) {
            SeaBattle seaBattle = new SeaBattle();
            alg.battleAlgorithm(seaBattle);
            res += seaBattle.getResult();
        }
        System.out.println(res / 1000);
    }


}

