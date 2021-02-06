package ru.progwards.java2.lessons.trees;

public class AvlTree {
    private AvlTreeLeaf root;

    public class AvlTreeLeaf<K extends Comparable<K>, V> {
        K key;
        V value;


        public AvlTreeLeaf(K key, V value) {
            this.key = key;
            this.value = value;
        }

        int h;
        AvlTreeLeaf parent;
        AvlTreeLeaf left;
        AvlTreeLeaf right;

        private AvlTreeLeaf find(K key) {
            var comp = this.key.compareTo(key);

            if (comp > 0) {
                if (left != null) left.find(key);
                else return this;
            }

            if (comp < 0) {
                if (right != null) right.find(key);
                else return this;
            }

            return this;
        }


        public void put(AvlTreeLeaf temp) {
            int com = this.key.compareTo((K) temp.key);
            if (com == 0) this.value = (V) temp.value;

            if (com > 0) {
                this.left = temp;
                temp.parent = this;
            }
            if (com < 0) {
                this.right = temp;
                temp.parent = this;
            }


        }
    }

    public <K extends Comparable<K>, V> void put(K key, V value) {
        var temp = new AvlTreeLeaf(key, value);
        if (root == null) root = temp;
        else
            root.find(key).put(temp);


    }




    /*
    *Реализовать класс AvlTree - АВЛ дерево, с методами:
2.1 public void put(K key, V value) - добавить пару ключ-значение, если уже такой ключ есть - заменить
2.2 public void delete(K key) - удалить ключ
2.3 public V find(K key) - найти ключ
2.4 public void change(K oldKey, K newKey) - заменить значение ключа на другое
2.5 public void process(Consumer<TreeLeaf<K,V>> consumer) - прямой обход дерев
* */
}
