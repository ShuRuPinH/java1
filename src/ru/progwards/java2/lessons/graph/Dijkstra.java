package ru.progwards.java2.lessons.graph;

import java.util.*;

public class Dijkstra {
    int[][] graph = null;
    int[][] rz;

    int grafSize = 0;
    TreeMap<Integer, List<Edge>> edges;

    Dijkstra(int[][] graph) {
        this.graph = graph;
        grafSize = graph.length;
        init();
    }

    public int[][] find(int n) {
        rz = new int[grafSize][];
        rz[n] = new int[]{0, n};


        MinList listM = new MinList(n, grafSize);

        while (listM.check()) {

            Integer pos = listM.getMin();


            System.out.println("*****  POS  =" + pos);

            listM.toString();

            for (Edge e : edges.get(pos)) {
                System.out.println("Edge.size = " + edges.get(pos).size() + " { end=" + e.end + "  weight=" + e.w + " }");
                int end = e.end;

                Double oldWay = listM.getWay(pos);
                Double prevWay = listM.getWay(end);
                if (prevWay == null || oldWay == null) continue;

                System.out.println("oldWay=" + oldWay);
                double newWay = oldWay + e.w;

                if (newWay < prevWay) {
                    System.out.println("  # new Way =" + newWay);
                    listM.change(end, prevWay, newWay);
                    System.out.println("  # pos = " + pos +
                            "  # end = " + end);
                    rz[end] = new int[rz[pos].length + 1];
                    System.arraycopy(rz[pos], 0, rz[end], 0, rz[pos].length);
                    rz[end][rz[end].length - 1] = end;


                }


            }

            listM.change(pos, listM.posWay.get(pos), -13);

        }


        return rz;
    }

    void init() {
        edges = new TreeMap<>();
        for (int i = 1; i < grafSize; i++) {

            for (int j = 1; j < grafSize; j++) {
                double w = graph[i][j];
                //  System.out.print("i/j   "+ i+" / "+j+"w="+w+"  --  ");
                if (w != 0 && w != Integer.MAX_VALUE) {
                    //     System.out.print("i/j{"+ i+" / "+j+ "}");
                    if (!edges.containsKey(i)) {
                        ArrayList<Edge> tmp = new ArrayList();
                        tmp.add(new Edge(j, w));
                        edges.put(i, tmp);
                        //       System.out.println( "add Edge :"+i);
                    } else {
                        var tmp = new Edge(j, w);
                        edges.get(i).add(tmp);
                        //    System.out.println( "add Edge :"+i);
                    }
                }


            }

        }

    }

    void print(int[][] mass) {
        System.out.println();
        System.out.println("** Graf toString **");
        String tmp = "";
        for (int i = 0; i < grafSize; i++) {
            tmp = "";
            for (int j = 0; j < grafSize; j++) {
                if (i == 0) {
                    if (j == 0) {
                        tmp = "ij";
                        continue;
                    }
                    tmp += " " + j;
                } else {
                    if (j == 0) {
                        tmp = i + ":";
                        continue;
                    }
                    if (mass[i] == null || mass[i].length - 1 < j) continue;
                    tmp += " " + (mass[i][j] == Integer.MAX_VALUE ? "X" : mass[i][j]);
                }


            }
            System.out.println(tmp);
        }
    }


    ///////////////////////// M A I N //////////////////////////////////////////////////////////
    public static void main(String[] args) {
        Integer START = 1;
/*
Алгоритм обрабатывает ориентированные и не ориентированные

Для удобства в массиве  нумерация позиции вершин равна вершинам,
т.е. первая [0], позиция не используется (если вес ребер целочисленный можно хранить все в случае пути).
для теста использован граф с 9-ю вершинами, соотвественно матрица смежности получилась 10х10


 */

        int[][] test = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 3, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 5, 0, 2, 0, 0, 0, 0},
                {0, 0, 5, 0, 0, 0, 3, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 3, 5, 0},
                {0, 0, 0, 0, 0, 0, 5, 0, 1, 0},
                {0, 0, 0, 3, 0, 5, 0, 0, 0, 3},
                {0, 0, 0, 0, 0, 0, 0, 0, 2, 0},
                {0, 0, 0, 0, 5, 0, 0, 2, 0, 5},
                {0, 0, 0, 0, 0, 0, 3, 0, 5, 0}};

/*
        int[][] test = {
                {0, 0, 0, 0, 0},
                {0, 0, 3, 0, 5},
                {0, 0, 0, 2, 0,},
                {0, 0, 0, 0, 2},
                {0, 5, 0, 2, 0}};*/

        Dijkstra testD = new Dijkstra(test);

        testD.print(testD.graph);

        System.out.println("\nFrom #" + START);
        testD.print(testD.find(START));


    }

}
