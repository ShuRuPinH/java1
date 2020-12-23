package ru.progwards.java2.lessons.basetypes;


import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class DoubleHashTable<K, V, T> implements Iterable<T> {
    final List SIM_SIZE = Arrays.asList(101, 211, 431, 863, 1733, 3467, 6947, 13901, 27803, 55609, 111227, 222461, 444929, 889871);
    final double A = 0.6180339887;
    private int size;
    private HItem[] table;


    public DoubleHashTable() {
        table = new HItem[101];
        size = 0;
    }

    @Override
    public Iterator<T> iterator() {
        Iterator<T> it = new Iterator<T>() {
            private int pos = 0;

            @Override
            public boolean hasNext() {
                if (pos >= table.length) return false;
                for (int i = pos; i < table.length; i++) {
                    // System.out.println("log-has next: i="+i);
                    // System.out.println("log-has table[i="+table[i]);
                    if (table[i] == null) continue;
                    if (table[i] != null || !(new HItem(null, null)).equals(table[i])) return true;
                }
                return false;
            }

            @Override
            public T next() {
                if (pos >= table.length) return null; // ??
                // System.out.println("log-next pos="+pos);

                for (int i = pos; i < table.length; i++) {
                    if (table[i] == null || table[i].equals(new HItem(null, null))) continue;
                    if (table[i] != null || !table[i].equals(new HItem(null, null))) {
                        pos = i + 1;
                        return (T) table[i];
                    }
                }
                return null;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return it;
    }

    /////////// Key сlass ////////////
    interface HashValue {
        int getHash();
    }

    class IntKey implements HashValue {
        private int numb;
        private long temp = 1;

        public <T> IntKey(T numberr) {
            this.numb = (int) (Double.parseDouble(String.valueOf(numberr)) % 2147483647);
        }

        @Override
        public int getHash() {

            return numb;

        }
    }

    class StringKey implements HashValue {
        private String string;

        public StringKey(String str) {
            string = str;
        }

        @Override
        public int getHash() {
            return LYHash(string);
        }
    }

    ///////////////////////////////////////////////////
    class HItem<K, V> {
        K key;
        V value;

        @Override
        public boolean equals(Object o) {

            if (this == o) return true;
            if (!(o instanceof HItem)) return false;
            HItem<K, V> hItem = (HItem<K, V>) o;
            return Objects.equals(key, hItem.key) && Objects.equals(value, hItem.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }

        @Override
        public String toString() {
            return "HItem{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
//    HItem next;

        public HItem(K key, V value) {
            this.key = key;
            this.value = value;
        }

      /*  public HItem getNext() {
            return next;
        }

        public void setNext(HItem next) {
            this.next = next;
        }*/

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V val) {
            value = val;
        }
    }
    ///////////////M E T O D S///////////////////////////////

//     2.1 public void add(K key, V value) - добавить пару ключ-значение

    public void add(K key, V value) {
        HItem addTMP = new HItem(key, value);
        int hash1Temp = hash1(tempKey(key).getHash());
        int hash2Temp = hash2(tempKey(key).getHash());

        //  System.out.println("log: key:"+key+"  h1:"+hash1Temp+"  h2:"+hash2Temp);

        if (table[hash1Temp] == null) {
            System.out.println("log: add index(h1)=" + hash1Temp);
            table[hash1Temp] = addTMP;
        } else {
            for (int i = 1; i <= table.length / 10; i++) {
                int doubleHash = (hash1Temp + i * hash2Temp) % table.length;
                if (table[doubleHash] == null) {
                    table[doubleHash] = addTMP;
                    System.out.println("log: add index(h1 =" + hash1Temp + " i=" + i + ")=" + doubleHash);
                    size++;
                    return;
                }
            }
            System.out.println("!!!!! Count of collision more then 10% !!!!");
            //   reform Table !!!!!!!!!!!
            reformat();
            add(key, value);
        }
        size++;
    }

    private void reformat() {
        HItem[] temp = Arrays.copyOf(table, table.length);

        table = new HItem[(int) SIM_SIZE.get(SIM_SIZE.indexOf(table.length) + 1)];
        for (HItem x : temp) {
            if (x != null) add((K) x.getKey(), (V) x.getValue());
        }
    }

    //      2.2 public V get(K key) - получить значение по ключу
    public V get(K key) {
        try {
            return (V) table[finder(key)].getValue();
        } catch (Exception e) {
            return null;
        }
    }

    //      2.3 public void remove(K key) - удалить элемент по ключу
    public void remove(K key) {
        try {
            table[finder(key)] = new HItem(null, null);
            size--;
        } catch (Exception e) {
            System.out.println("KEY NOT FOUND");
        }


    }

    //      2.4 public void change(K key1, Key key2) - изменить значение ключа у элемента с key1 на key
    public void change(K key1, K key2) {
        try {
            add(key2, (V) table[finder(key1)].getValue());
            remove(key1);
        } catch (Exception e) {
            System.out.println("Changing error");
        }
    }

    //2.5 public int size() - получить количество элементов
    public int size() {
        return size;
    }

    ///////////////finder return index of cell///////////////////////////////////////////////
    int finder(K key) throws Exception {
        int hash1Temp = hash1(tempKey(key).getHash());

        if (table[hash1Temp] != null) {
            if (String.valueOf(table[hash1Temp].getKey()).equals(String.valueOf(key))) return hash1Temp;
            int hash2Temp = hash2(tempKey(key).getHash());
            for (int i = 1; i <= table.length / 10; i++) {
                int doubleHash = (hash1Temp + i * hash2Temp) % table.length;
                if (table[doubleHash] != null && (String.valueOf(table[doubleHash].getKey()).equals(String.valueOf(key))))
                    return doubleHash;
            }
        }
        throw new Exception("not found");
    }

    ////////////////////K E Y///////////////////////////////////////
    HashValue tempKey(K key) {
        if (isNumeric(String.valueOf(key))) {
            return new IntKey(key);
        } else {
            return new StringKey((String) key);
        }
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    ///////////// HASH # 1 //////////////////////////////
    private int hash1(int key) {

        return (key % table.length);
    }

    ///////////// HASH # 2 //////////////////////////////
    private int hash2(int key) {
        double d = A * (key);
        return (int) (43100 * (d - Math.floor(d)));
    }

    ///////////// LYHash  /////////////////////
    int LYHash(String str) {
        int hash = 0;

        for (int i = 0; i < str.length(); i++) {
            hash = over(hash * 1664525 + str.charAt(i) + 1013904223);
        }
        if (hash < 0) hash *= -1;
        return hash;
    }

    static int over(long num) {
        final int UINT_MAX = 2147483647;

        return ((int) num) % UINT_MAX;
    }

    public static void main(String[] args) {
        DoubleHashTable testTbl = new DoubleHashTable();

        testTbl.add(01.1, "hkhk1111jklj");
        testTbl.add(01.1, "hkhk1111jklj");
        testTbl.add(03.3, "hkhkj222klj");
        testTbl.add(07, "hkhkjkl333j");
        testTbl.add("yuihu", "hkhkjkl333j");

/*
       for (int i = 0; i < 100; i++) {
           System.out.print("line "+i +" ");
            testTbl.add("fdsf"+(int)(Math.random()*100)+"dfd", "hkhkjkl333j");

        }*/
        testPrint(testTbl);
        testTbl.remove(07);
        testTbl.change(03.3, 33);
        System.out.println(" get - " + testTbl.get(1.1) + "  ");
        System.out.println(" get - " + testTbl.get("yuihu") + "  ");
        System.out.println(" get - " + testTbl.get("yuihu") + "  ");
        System.out.println(" get - " + testTbl.get("yu89hu") + "  ");

        testPrint(testTbl);


    }

    static void testPrint(DoubleHashTable tbl) {
        System.out.println("");
        System.out.println("size=" + tbl.size());
        for (Object x : tbl) {
            if (x != null) System.out.println(x.toString());
        }
    }
}


