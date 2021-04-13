package ru.progwards.java2.lessons.graph;

import java.util.*;

public class MinList {
    TreeMap<Double, List> wayPoss = new TreeMap<>();
    ArrayList list;

    TreeMap<Integer, Double> posWay = new TreeMap<>();

    public MinList(int start, int size) {
        for (int i = 1; i < size; i++) {
            change(i, -1, Integer.MAX_VALUE);
        }
        change(start, Integer.MAX_VALUE, 0);
    }

    void change(int pos, double wayOld, double wayNew) {
        System.out.println("** CHANGE pos= " + pos + "  wayOld= " + wayOld + "  wayNew= " + wayNew);

        ArrayList tmp;


        if (wayNew < 0) {
            posWay.remove(pos);

            //  if (wayPoss.containsKey(wayOld)) ;

            if (wayPoss.get(wayOld).size() == 1) {
                wayPoss.remove(wayOld);
                System.out.println("wayPoss.remove(wayOld);");
            } else {
                wayPoss.get(wayOld).remove((Object) pos);
                System.out.println(" wayPoss.get(wayOld).remove((Object) pos);");
            }
            return;
        }
        posWay.put(pos, wayNew);
        if (wayOld == -1) {
            tmp = new ArrayList();
            tmp.add(pos);
            wayPoss.put(wayNew, tmp);
            posWay.put(pos, wayNew);
            return;
        }

        if (wayPoss.containsKey(wayNew)) {

            wayPoss.get(wayOld).remove((Object) pos);

            wayPoss.get(wayNew).add(pos);
        } else {
            tmp = new ArrayList();
            tmp.add(pos);
            wayPoss.get(wayOld).remove((Object) pos);
            wayPoss.put(wayNew, tmp);
            posWay.put(pos, wayNew);
        }

    }
//TODO   проверку на наличие меток, получиьт минимальную, получить путь по позиции

    Double getWay(int pos) {
        Double rez = posWay.get(pos);
        System.out.println("getWay(" + pos + ")=" + rez);
        return rez;
    }


    //////////////////////////////////////////
    Integer getMin() {
        while (!wayPoss.isEmpty()) {
            if (wayPoss.firstEntry().getValue().size() == 0) {
                wayPoss.remove(wayPoss.firstEntry().getKey());
                continue;
            }
            List list = wayPoss.firstEntry().getValue();
            System.out.println("firstEntry()=" + wayPoss.firstEntry().getValue().size());
            double key = wayPoss.firstEntry().getKey();

            for (Object x : list) {
                if (x != null) {

                    System.out.println("**  get Min  key: " + key + "   x= " + x);
                    return (Integer) x;
                }


            }

        }
        System.out.println("wayPoss.isEmpty");
        return null;
    }
    //////////////////////////////////////////

    boolean check() {
        if (posWay.size() == 1 && wayPoss.containsKey((double) Integer.MAX_VALUE) || posWay.size() == 0) return false;
        return true;
    }


    ///////////////// MAIN & toSTRING //////////////////////////
    @Override
    public String toString() {
        return "MinList{" +
                "wayPoss=" + wayPoss.size() +
                ", posWay=" + posWay.size() +
                '}';
    }

    public static void main(String[] args) {
        MinList tt = new MinList(2, 5);
        tt.change(3, Integer.MAX_VALUE, 0);
        tt.change(2, 3, 4);


        for (Map.Entry t : tt.wayPoss.entrySet()) {
            System.out.println(t);
        }

    }
}

class Edge {
    int start;
    int end;
    double w;

    public Edge(int end, double w) {

        this.end = end;
        this.w = w;
    }
}
