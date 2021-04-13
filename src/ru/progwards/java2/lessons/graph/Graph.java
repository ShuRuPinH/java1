package ru.progwards.java2.lessons.graph;

import java.util.ArrayList;
import java.util.List;

class Graph<N, E> {
    List<Node<N, E>> nodes;
    List<EdgeB<N, E>> edges;

    public Graph() {
        this.nodes = new ArrayList<>();
        this.edges = new ArrayList<>();
    }

    void add(N start, N end, double weight) {

        //      System.out.println("-ADD    "+start+" / "+ end +"   w="+weight );

        Node nodS = new Node();
        Node nodE = new Node();

        EdgeB edge1 = new EdgeB();
        EdgeB edge2 = new EdgeB();
        edge1.weight = weight;
        edge2.weight = weight;
        if (nodes.isEmpty()) {
            //System.out.println("empty");
            nodS.info = start;
            nodE.info = end;

            edge1.out = nodS;
            edge1.in = nodE;
            edge2.out = nodE;
            edge2.in = nodS;

            edges.add(edge1);
            edges.add(edge2);

            nodS.out.add(edge1);
            nodS.in.add(edge2);

            nodE.in.add(edge1);
            nodE.out.add(edge2);

            nodes.add(nodE);
            nodes.add(nodS);
        } else {
            // System.out.println("else nodes.size="+nodes.size());
            int nns = 0;

            for (Node n : nodes) {
                //  System.out.println(n);

                if (n.info == start) {
                    nodS = n;

                    nns += 1;
                    //  System.out.println("+1");
                }
                if (n.info == end) {
                    nodE = n;

                    nns += 2;
                    //  System.out.println("+2");
                }
            }

            switch (nns) {
                case 0:
                    nodS.info = start;
                    nodE.info = end;
                    nodes.add(nodE);
                    nodes.add(nodS);
                    //  System.out.println("case 0");
                    break;

                case 1:
                    nodE.info = end;
                    nodes.add(nodE);

                    //  System.out.println("case 1");
                    break;

                case 2:
                    nodS.info = start;

                    nodes.add(nodS);
                    //  System.out.println("case 2");
                    break;

                case 3:
                    break;
                default:
                    System.out.println(" ADD error  nns= " + nns);
                    return;
            }

            edge1.out = nodS;
            edge1.in = nodE;
            edge2.out = nodE;
            edge2.in = nodS;

            //    System.out.println(edge1.toString()+"   "+edge2.toString());
            edges.add(edge1);
            edges.add(edge2);
            nodS.out.add(edge1);
            nodS.in.add(edge2);

            nodE.in.add(edge1);
            nodE.out.add(edge2);


        }
    }


}

class Node<N, E> {
    N info; // информация об узле
    List<EdgeB<N, E>> in; // массив входящих ребер
    List<EdgeB<N, E>> out; // массив исходящих ребер

    public N getInfo() {
        return info;
    }

    public List<EdgeB<N, E>> getIn() {
        return in;
    }

    public List<EdgeB<N, E>> getOut() {
        return out;
    }

    public Node() {
        in = new ArrayList<>();
        out = new ArrayList<>();
    }


}

class EdgeB<N, E> {
    @Override
    public String toString() {
        return "EdgeB{" +
                "info=" + info +
                ", out=" + out +
                ", in=" + in +
                ", weight=" + weight +
                '}';
    }

    E info; // информация о ребре
    Node<N, E> out; // вершина, из которой исходит ребро
    Node<N, E> in; // вершина, в которую можно попасть
    // по этому ребру
    double weight; // стоимость перехода
}