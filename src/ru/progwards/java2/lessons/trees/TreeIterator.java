package ru.progwards.java2.lessons.trees;

import java.util.ArrayList;
import java.util.Iterator;

public class TreeIterator<K extends Comparable<K>, V> implements Iterator {
    K key;
    V value;
    BinaryTree.TreeLeaf tree;
    ArrayList<BinaryTree.TreeLeaf> sorted = new ArrayList<>();
    int indx;


    public TreeIterator(BinaryTree.TreeLeaf root) {
        this.tree = root;
        indx = 0;
        process(tree);
    }


    @Override
    public boolean hasNext() {

        return indx < sorted.size();
    }

    @Override
    public Object next() {


        return sorted.get(indx++);

    }


    public void process(BinaryTree.TreeLeaf tree) {
        if (tree != null) {
            if (tree.left != null) process(tree.left);
            sorted.add(tree);
            if (tree.right != null)
                process(tree.right);
        }

    }
}

