package ru.progwards.java2.lessons.trees;

import java.util.ArrayList;
import java.util.function.Consumer;

public class AvlTree {
    static private AvlTreeLeaf root;


    public class AvlTreeLeaf<K extends Comparable<K>, V> {
        K key;

        @Override
        public String toString() {
            return "{" +
                    key +
                    " :" + value +
                    '}';
        }

        V value;

        public AvlTreeLeaf() {
            h = 0;
        }

        public String getKey() {
            return key == null ? "*" : key.toString();
        }

        public AvlTreeLeaf(K key, V value) {
            this.key = key;
            this.value = value;
            h = 0;
        }

        int h;
        AvlTreeLeaf parent;
        AvlTreeLeaf left;
        AvlTreeLeaf right;

        private AvlTreeLeaf find(K key) {
            var tmpL = new AvlTreeLeaf<K, V>();
            tmpL = root;
            while (tmpL.key != null) {

                var comp = tmpL.key.compareTo(key);
                if (comp > 0) {
                    if (tmpL.left != null) tmpL = tmpL.left;
                    else break;
                    continue;
                } else if (comp < 0) {
                    if (tmpL.right != null) tmpL = tmpL.right;
                    else break;
                    continue;
                } else return tmpL;
            }
            return tmpL;

        }


        public void put(AvlTreeLeaf temp) {
            //   System.out.println("this="+this.getKey() + ".put(key="+temp.getKey()+")");

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

        int getH() {
            reH();
            return h;
        }

        void reH() {
            h = Math.max(left != null ? (left.h + 1) : 0, right != null ? (right.h + 1) : 0);
        }

        int balance() {
            return (right != null ? right.getH() + 1 : 0) - (left != null ? left.getH() + 1 : 0);
        }

        public void stepz(Consumer consumer) {
            if (left != null)
                left.stepz(consumer);
            consumer.accept(this);
            if (right != null)
                right.stepz(consumer);
        }


    }
////!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
////////// METODS /////////////////////////////


    public <K extends Comparable<K>, V> void put(K key, V value) {
        var temp = new AvlTreeLeaf(key, value);
        if (root == null) root = temp;
        else {
            root.find(key).put(temp);
        }
        reBalance(temp);
    }

    public <K extends Comparable<K>> void delete(K key) {
        var tmp = root.find(key);
        AvlTreeLeaf newTmp;

        if (tmp.key != key) {
            System.out.println(" Key have not found!");
            return;
        }

        //  System.out.println(" TMP=" + tmp.getKey() + "balance=" + tmp.balance());


        if (tmp.left != null || tmp.right != null) {


            if (tmp.balance() > 0) {
                newTmp = findLeft(tmp.right);
                newTmp.parent.left = null;
            } else {
                newTmp = findRight(tmp.left);
                newTmp.parent.right = null;

            }


            //   System.out.println(" NEWTMP=" + newTmp.getKey());
            if (tmp.left != newTmp) {
                newTmp.left = tmp.left;
                if (newTmp.left != null) newTmp.left.parent = newTmp;
            }
            if (tmp.right != newTmp) {
                newTmp.right = tmp.right;
                if (newTmp.right != null) newTmp.right.parent = newTmp;
            }


            if (tmp.parent != null && tmp.parent.left == tmp) {
                tmp.parent.left = newTmp;
            } else if (tmp.parent != null) tmp.parent.right = newTmp;
            else if (tmp.parent != null) newTmp.parent = tmp.parent;
            else {
                root = newTmp;
                newTmp.parent = null;

            }


        } else {
            if (tmp.parent.left == tmp) {
                tmp.parent.left = null;
            } else tmp.parent.right = null;

        }

        System.out.println("root balance=" + root.balance());
        if (root.balance() > 0)
            reBalance(findRight(root));
        else reBalance(findLeft(root));
    }

    public <K extends Comparable<K>, V> V find(K key) {
        var x = root.find(key);
        return x.key == key ? (V) x.value : null;
    }

    public <K extends Comparable<K>, V> void change(K oldKey, K newKey) {
        var x = root.find(oldKey);
        V val;
        if (x.key == oldKey) {
            val = (V) x.value;
            delete(oldKey);
            put(newKey, val);
        } else {
            System.out.println(" Key have not found!");
        }
    }

    public <K extends Comparable<K>, V> void process(Consumer<AvlTree.AvlTreeLeaf<K, V>> consumer) {

        if (root != null)
            root.stepz(consumer);
    }


///---------------------------------------------------------------------------------------------------------------------
    /////!!!!!!!!!!!!!!!!!!!!!!!!!!!//// supports metods

    AvlTreeLeaf findRight(AvlTreeLeaf ff) {
        var tmp = ff;

        while (tmp.right != null) {
            tmp = tmp.right;
        }
        return tmp;
    }

    AvlTreeLeaf findLeft(AvlTreeLeaf ff) {
        var tmp = ff;

        while (tmp.left != null) {
            tmp = tmp.left;
        }
        return tmp;
    }


    <K extends Comparable<K>, V> void reBalance(AvlTreeLeaf rt) {
        var tmpL = new AvlTreeLeaf<K, V>();


        tmpL = rt;


        int ll = -1, lr = -1, rl = -1, rr = -1;
        while (tmpL != null) {
            //  System.out.println("reBal #"+tmpL.getKey() );

            if (tmpL.left != null) {
                if (tmpL.left.left != null) ll = tmpL.left.left.getH();
                if (tmpL.left.right != null) lr = tmpL.left.right.getH();
            }
            if (tmpL.right != null) {
                if (tmpL.right.left != null) rl = tmpL.right.left.getH();
                if (tmpL.right.right != null) rr = tmpL.right.right.getH();
            }

            int bal = tmpL.balance();
            if (bal > 1 || bal < -1) {
                if (bal < -1) {


                    if (lr <= ll) {
                        //  System.out.println("!!! small Right   key="+tmpL.getKey());
                        tmpL = smallRight(tmpL);

                    } else {
                        // System.out.println("!!! big Right   key="+tmpL.getKey());
                        tmpL = bigRight(tmpL);

                    }
                } else {
                    if (rl <= rr) {
                        // System.out.println("!!! small Left   key="+tmpL.getKey());
                        tmpL = smallLeft(tmpL);

                    } else {
                        //  System.out.println("!!! big Left   key="+tmpL.getKey());
                        tmpL = bigLeft(tmpL);
                    }


                }


//                System.out.println("ROTATE balance=" + tmpL.balance());
            }
            if (tmpL == null) {
                System.out.println("rebalanse  tmpl  NULL");
                return;
            }
            tmpL = tmpL.parent;


            // System.out.println("rebalanse  tmpl.parent="+tmpL);
        }

    }

    static AvlTreeLeaf smallRight(AvlTreeLeaf mm) {
        var b = mm.left;
        var c = b.right;

        mm.left = c;
        b.right = mm;
        //  System.out.println("sR mm/parent"+mm+"/"+mm.parent+" b="+b+"  c="+c+"  mm="+mm);

        b.parent = mm.parent;
        mm.parent = b;

        if (c != null) c.parent = mm;

        if (b.parent == null) root = b;
        return b;
    }

    static AvlTreeLeaf smallLeft(AvlTreeLeaf mm) {
        var b = mm.right;
        var c = b.left;

        mm.right = c;
        b.left = mm;
        //  System.out.println("sL mm/parent"+mm+"/"+mm.parent+" b="+b+"  c="+c+"  mm="+mm);

        b.parent = mm.parent;
        mm.parent = b;

        if (c != null) c.parent = mm;

        if (b.parent == null) root = b;
        return b;
    }


    static AvlTreeLeaf bigRight(AvlTreeLeaf mm) {

        var b = mm.left;
        var c = b.right;
        var n = c == null ? null : c.right;
        var m = c == null ? null : c.left;
        //   System.out.println("bR   mm="+mm+"  b="+b+"  c="+c+"  n/m="+n+"/"+m);
        mm.left = n;
        b.right = m;
        c.right = mm;
        c.left = b;

        c.parent = mm.parent;

        mm.parent = c;
        b.parent = c;
        if (n != null) n.parent = mm;
        if (m != null) m.parent = b;

        if (c.parent == null) root = c;

        return c;
    }

    static AvlTreeLeaf bigLeft(AvlTreeLeaf mm) {

        var b = mm.right;
        var c = b.left;
        var n = c == null ? null : c.left;
        var m = c == null ? null : c.right;
        //   System.out.println("bL   mm="+mm+"  b="+b+"  c="+c+"  n/m="+n+"/"+m);
        mm.right = n;
        b.left = m;
        c.left = mm;
        c.right = b;

        c.parent = mm.parent;

        mm.parent = c;
        b.parent = c;
        if (n != null) n.parent = mm;
        if (m != null) m.parent = b;

        if (c.parent == null) root = c;

        return c;
    }


    String viewer(AvlTreeLeaf root) {
        String l = "-", r = "-";
        String lh = "-", rh = "-";
        if (root == null) return "[--]";


        if (root.left != null) {
            l = root.left.getKey();
            lh = String.valueOf(root.left.getH());

        }
        if (root.right != null) {
            r = root.right.getKey();
            rh = String.valueOf(root.right.getH());
        }
        return ("[" + l + "." + lh + " / \\" + r + "." + rh + "]");
    }




    /*
    *Реализовать класс AvlTree - АВЛ дерево, с методами:
2.1 public void put(K key, V value) - добавить пару ключ-значение, если уже такой ключ есть - заменить
2.2 public void delete(K key) - удалить ключ
2.3 public V find(K key) - найти ключ
2.4 public void change(K oldKey, K newKey) - заменить значение ключа на другое
2.5 public void process(Consumer<TreeLeaf<K,V>> consumer) - прямой обход дерев
* */

    public static void main(String[] args) {
        AvlTree test = new AvlTree();

        ArrayList<AvlTree.AvlTreeLeaf> sorted = new ArrayList<AvlTree.AvlTreeLeaf>();


        for (int i = -10; i < 50; i += 7) {
            test.put(i * (int) (Math.random() * 10), "fdf" + i);
        }
    /*    test.put(7,"fds");
        test.put(12,"fds");
        test.put(15,"fds");*/


        test.delete(17);
        System.out.println("V=" + test.find(39));

        test.change(66, 57);

        System.out.println("find (57)=" + test.find(57));

        test.process(sorted::add);
        System.out.print("{");
        int counttt = 0;
        for (AvlTreeLeaf x : sorted) {
            System.out.print(" k= " + x.key + ":" + x.value + "-# " + (++counttt) + ";");
        }
        System.out.println("}");
        System.out.println("          " + test.root.getKey() + " h=" + test.root.getH());
        System.out.println("        " + test.viewer(test.root));
        var l = test.root.left;
        var r = test.root.right;

        System.out.println(" " + test.viewer(l) + " " + test.viewer(r));

    }

}
