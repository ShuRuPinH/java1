package ru.progwards.java2.lessons.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Boruvka {
    /*
    Требуется реализовать метод static List<Edge> minTree(Graph graph),
    который возвращает минимальное остовное дерево в виде списка дуг графа.
     */
    static Graph tempGraf;

    public Boruvka(Graph another) {
        this.tempGraf = another;
    }

    static List<Node> treeNodes = new ArrayList<>();


    static List<EdgeB> minTree(Graph graph) {
        Node tempNode = null;
        List<EdgeB> rez = new ArrayList<>();

        Boruvka boruvka = new Boruvka(graph);

        while (!boruvka.tempGraf.edges.isEmpty()) {

            EdgeB minStart0 = minWEdge(boruvka.tempGraf.edges, tempNode);
            boruvka.tempGraf.edges.remove(minStart0);
            EdgeB minStart1 = minWEdge(boruvka.tempGraf.edges, tempNode);
            boruvka.tempGraf.edges.remove(minStart1);

            // System.out.println(minStart0.toString());
            // System.out.println(minStart1.toString());

            if (minStart0.weight != minStart1.weight || !minStart0.out.equals(minStart1.in)) {
                System.out.println("Graph structure ERROR");
                return null;
            }
            rez.add(minStart0);
            rez.add(minStart1);
            treeNodes.add(minStart0.in);
            treeNodes.add(minStart1.in);

            if (treeNodes.containsAll(boruvka.tempGraf.nodes)) return rez;


            tempNode = minOfTwo(minStart0.in, minStart1.in);
            //  System.out.println(tempNode.getInfo());
        }


        return null;

    }

    static Node minOfTwo(Node fst, Node snd) {
        //   System.out.println(" minOfTwo "+fst.getInfo()+"  "+snd.getInfo());
        var var1 = minWEdge(tempGraf.edges, fst);
        var var2 = minWEdge(tempGraf.edges, snd);

        if (var1 == null && var2 != null) return snd;
        if (var2 == null && var1 != null) return fst;
        if (var1 == null && var2 == null) {
            //     System.out.println("----  end ----");
            return null;
        }


        if (var1.weight < var2.weight)
            return fst;
        else return snd;
    }


    static EdgeB minWEdge(List<EdgeB> list, Node node) {
        //  System.out.println("minEdge in   ---->  "+node);

        double tmpW = Double.MAX_VALUE;
        EdgeB rez = null;
        for (EdgeB x : list) {
           /* System.out.println("x.out ="+x.out );
            System.out.println("x.in ="+x.in );
*/
            if (tmpW > x.weight && (node == null || x.out.equals(node) || x.in.equals(node))) {
                tmpW = x.weight;
                rez = x;
            }
        }

        //     System.out.println("minEdge ret  ---->  "+rez);
        return rez;

    }

    public static void main(String[] args) {

        Graph test = new Graph();

        test.add(1, 2, 0.5);
        test.add(1, 3, 0.3);
        test.add(3, 2, 0.7);
        test.add(4, 2, 0.8);
        test.add(4, 3, 0.4);

/*
        for (Object x : test.nodes){
            System.out.println(((Node)x).getInfo());
        }*/
        System.out.println("     RESULT  :");

        for (EdgeB e : Boruvka.minTree(test)) {
            System.out.println(e.toString());

        }

    }

}
