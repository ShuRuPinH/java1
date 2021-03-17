

package ru.progwards.java2.lessons.annotation;

import ru.progwards.java2.lessons.trees.BinaryTree;
import ru.progwards.java2.lessons.trees.TreeException;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Consumer;


/**
 * Класс TreeIterator, работает с, {@link BinaryTree} сортирует дерево и обходит его по возрастанию ключей.
 * Использует метод {@link BinaryTree} @see  BinaryTree.process
 *
 * @author ShuRuPin
 * @version 1.0
 */

public class TreeIterator<K extends Comparable<K>, V> implements Iterator {
    /**
     * Тип ключей, должен реализовывать интерфейс {@link  Comparable}
     */

    K key;
    /**
     * Тип значений
     */

    V value;

    private BinaryTree.TreeLeaf tree;
    ArrayList<BinaryTree.TreeLeaf> sorted = new ArrayList<>();
    int indx;


    private TreeIterator(BinaryTree.TreeLeaf root) {
        this.tree = root;
        indx = 0;
        tree.process((Consumer) sorted);


    }

    /**
     * Возвращает значение, если есть еще элементы после текущщего.
     *
     * @return true если если еще элементы для итерации
     */

    @Override
    public boolean hasNext() {

        return indx < sorted.size();
    }

    /**
     * Возвращает следующий элемент итерации.
     *
     * @return следующий элемент из BinaryTree
     */
    @Override
    public Object next() {


        return sorted.get(indx++);

    }


/*    public static void main(String[] args) {
        BinaryTree<Integer, String> test = new BinaryTree<>();

        for (int i = 0; i < 10; i++) {
            Integer tt=(int)(Math.random()*100);
            try {
                test.add(tt,"gfgdf//"+tt);
            } catch (TreeException e) {
                e.printStackTrace();
            }
        }
        Iterator it= test.getIterator();

        while (it.hasNext()){
            System.out.println(it.next().toString());
        }
    }*/


}

