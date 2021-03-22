package ru.progwards.java2.lessons.graph;

import java.util.Arrays;

public class Dijkstra {
    int[][] graph = null;

    Dijkstra(int[][] graph) {
        this.graph = graph;
    }

    public int[][] find(int n) {

        int[] piks = new int[graph.length];
        Arrays.fill(piks, Integer.MAX_VALUE);
        piks[n] = 0;

        int tmp = n;

        while (piks[tmp] > 0) {


        }
        return null;
    }


    int minWay(int n) {
        int tmp = Integer.MAX_VALUE;
        int pik = 0;

        for (int i = 1; i <= graph.length; i++) {
            if (graph[n][i] < tmp) {
                tmp = graph[n][i];
                pik = i;
            }
        }
        return pik;
    }

    ///////////////////////// M A I N //////////////////////////////////////////////////////////
    public static void main(String[] args) {

        int[][] test = new int[5][5];

        test[1][1] = 0;
        test[1][2] = 1;
        test[1][3] = 2;
        test[1][4] = 5;
        test[2][1] = 1;
        test[2][2] = 0;
        test[2][3] = 0;
        test[2][4] = 2;
        test[3][1] = 2;
        test[3][2] = 0;
        test[3][3] = 0;
        test[3][4] = 2;
        test[4][1] = 5;
        test[4][2] = 2;
        test[4][3] = 2;
        test[2][4] = 0;

    }

}
