package ru.progwards.java1.SeaBattle.shurupin;

import ru.progwards.java1.SeaBattle.SeaBattle;
import java.util.*;

public class SeaBattleAlg {
    public int vicpoint;
    public SeaBattle seaBattle;

    int stcnt;
    int steppos;

    int[][] pole;
    int shots;


    public void battleAlgorithm(SeaBattle seaBattle) {
        vicpoint = 0;
        stcnt = 0;
        pole = new int[10][10];
        shots = 0;
        steppos = 0;

        this.seaBattle = seaBattle;
        boolean on = true;



/*
        for (int i=0; i<10; i++){
            fire(i,i);
            fire(i,9-i);
        }
        for (int i=3; i<70; i+=11){
            fire(i/10,i%10);
            fire(i%10,i/10);
        }
        for (int i=6; i<40; i+=11){
            fire(i/10,i%10);
            fire(i%10,i/10);
        }
*/


        while (on) {

            stcnt = 0;

            if (vicpoint == 10) on = false;




            next4();

        }
    }
/////////////KILL////////////////////
void kill(int x, int y) {
    System.out.println("kill   " + x + ":" + y + "    vinpoint=" + vicpoint + "      shots:" + shots);
    //   toScr(pole);
    /// corners
    if (y == 9 && x == 9) {
        if (fire(x - 1, y) != 1)
            fire(x, y - 1);
    }
    if (y == 0 && x == 0) {
        if (fire(x + 1, y) != 1) fire(x, y + 1);
    }

    if (y == 0 && x == 9) {
        if (fire(x - 1, y) != 1) fire(x, y + 1);
    }
    if (y == 9 && x == 0) {

        if (fire(x, y - 1) != 1) fire(x + 1, y);
    }

    /////sides///
    if (y == 0) {
        if (fire(x + 1, y) == 8)
            fire(x + 1, y);
    }
    if (y == 9) {
        if (fire(x + 1, y) == 8)
            fire(x + 1, y);
    }
    if (x == 0) {
        if (fire(x, y + 1) == 8)
            fire(x, y - 1);
    }
    if (x == 9) {
        if (fire(x, y + 1) == 8)
            fire(x, y - 1);
    }
// TODO:  selecting way  .....  наводка и в отдельный метод........
    switch (fire(x, y + 1)) {
        case 1:
            //    System.out.println("case 1   ----KILL ");
            fire(x, y + 2);
        case 2:
            next4();//    System.out.println("case 1   ----KILL ");


        case 8: //System.out.println("case 3    ----KILL");
            fire(x + 1, y);
        case -1:// System.out.println("case 4   ----KILL");
            next4();
        case 11:
            return;
        default:
            next();
    }

    //todo//////////  checking  ////
    if (y + 1 < 10 && pole[y + 1][x] == 0) {
        stcnt++;
        System.out.println("kil1");
        if (stcnt < 2) rfire(x, y + 1);
        else next4();
    } else if (y - 1 > 0 && pole[y - 1][x] == 0) {
        stcnt++;
        System.out.println("kil2");
        if (stcnt < 2) rfire(x, y - 1);
        else next4();
    } else if (x + 1 < 10 && pole[y][x + 1] == 0) {
        stcnt++;
        System.out.println("kil3");
        if (stcnt < 2)

            rfire(x + 1, y);
        else next4();
    } else if (x - 1 > 0 && pole[y][x - 1] == 0) {
        stcnt++;
        System.out.println("kil4");
        if (stcnt < 2)

            rfire(x - 1, y);
        else next4();
    }

}

    ///+++++++++//dz////DZONE////dz///***********************************////////////
    void dzone(int x, int y) {
        List<int[]> ship = new ArrayList<>();

        //   System.out.println("************* start of deadline ********** /ship size =" + ship.size() + " /*********");
        //  toScr(pole);

        ship.add(new int[]{x, y});


        if (y + 1 < 10 && pole[y + 1][x] == 1) {
            ship.add(new int[]{x, y + 1});
            if (y + 2 < 10 && pole[y + 2][x] == 1) {
                ship.add(new int[]{x, y + 2});
                if (y + 3 < 10 && pole[y + 3][x] == 1) {
                    ship.add(new int[]{x, y + 3});
                }
            }
        }
        if (y - 1 > 0 && pole[y - 1][x] == 1) {
            ship.add(new int[]{x, y - 1});
            if (y - 2 > 0 && pole[y - 2][x] == 1) {
                ship.add(new int[]{x, y - 2});
                if (y - 3 > 0 && pole[y - 3][x] == 1) {
                    ship.add(new int[]{x, y - 3});
                }
            }
        }
        if (x + 1 < 10 && pole[y][x + 1] == 1) {
            ship.add(new int[]{x + 1, y});
            if (x + 2 < 10 && pole[y][x + 2] == 1) {
                ship.add(new int[]{x + 2, y});
                if (x + 3 < 10 && pole[y][x + 3] == 1) {
                    ship.add(new int[]{x + 3, y});
                }
            }
        }
        if (x - 1 > 0 && pole[y][x - 1] == 1) {
            ship.add(new int[]{x - 1, y});
            if (x - 2 > 0 && pole[y][x - 2] == 1) {
                ship.add(new int[]{x - 2, y});
                if (x - 3 > 0 && pole[y][x - 3] == 1) {
                    ship.add(new int[]{x - 3, y});
                }
            }
        }

        for (int[] sh : ship) {
            mark(sh[0], sh[1]);
        }
        ship.clear();


        //    toScr(pole);
        //    System.out.println("************* end of deadline *******************");


    }


    ////////////////////////////////////////////////
    void mark(int x, int y) {
        //    System.out.println("************* working MARK of deadline ********start****** x=" + x + "* y=" + y + "****");
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



        //  System.out.println("************* working MARK of deadline *********end**********");
    }

    void markhit(int x, int y) {
        System.out.println("************* working MARKHIT ********start****** x=" + x + "* y=" + y + "****");
        int ty = x;
        int tx = y;
        int decty = ty - 1;
        int incty = ty + 1;
        int dectx = tx - 1;
        int inctx = tx + 1;

        if (dectx < 0) dectx = 50;
        if (inctx > 9) inctx = 50;
        if (decty < 0) decty = 50;
        if (incty > 9) incty = 50;

        if (dectx < 10 & decty < 10) pole[dectx][decty] = 8;
        if (dectx < 10 & incty < 10) pole[dectx][incty] = 8;
        pole[tx][ty] = 1;
        if (inctx < 10 & decty < 10) pole[inctx][decty] = 8;
        if (inctx < 10 & incty < 10) pole[inctx][incty] = 8;

    }

///          ROUND FIRE

    void rfire(int x, int y) {
        switch (fire(x, y)) {
            case 1:
                //    System.out.println("case 1   ----KILL ");
                kill(x, y);
            case 2:
                next4();//    System.out.println("case 1   ----KILL ");


            case 8: //System.out.println("case 3    ----KILL");
                next4();
            case -1:// System.out.println("case 4   ----KILL");
                next4();
            case 11:
                return;
            default:
                next();
        }

    }


    //*************///!!!!!!!!!!!///FIRE//////!!!!!!!!!!!!!!!///*********************///
    public Integer fire(int x, int y) {
        if (vicpoint == 10) return -1;
        int res = -1;
        if (y < 0 || x < 0 || y > 9 || x > 9) return -1;
        if (pole[y][x] != 0) {
            System.out.println(x + "," + y + " /*** Hitting place ***  / deads:" + vicpoint + "shots:" + shots + "/  pole=" + pole[y][x]);
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
                if (vicpoint == 10) return 11;
                //        System.out.println("call DZONE from DESTROYED");

                dzone(x, y);

                return  2;
            case HIT:
                pole[y][x] = 1;
                markhit(x, y);
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
        if (vicpoint == 10) return;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (pole[j][i] == 0) fire(i, j);
            }
        }
        System.out.println("next" + "       shot=" + shots + "      vic=" + vicpoint);
    }

    void next4() {
/*        if (vicpoint==10) return;
        while (steppos < 96) {
            steppos = steppos % 100;
            steppos += 4;


            if (pole[(steppos / 10)][(steppos % 10)] == 0) {
                fire((steppos / 10), (steppos % 10));
                break;
            } else next4();
            System.out.println("next4");
        }*/
        next();
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

