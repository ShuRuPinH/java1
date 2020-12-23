package ru.progwards.java2.lessons.basetypes;


import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class LonikedHashTable<K, V, T> implements Iterable<T> {
    final int[] SIM_SIZE = {101, 211, 431, 863, 1733, 3467, 6947, 13901, 27803, 55609, 111227, 222461, 444929, 889871};
    final double A = 0.6180339887;
    private int size;
    HItem[] table;

    public LonikedHashTable() {
        table = new HItem[101];
        size = 0;

    }

    @Override
    public Iterator<T> iterator() {
        Iterator<T> it = new Iterator<T>() {

            HItem temp = table[0];
            int pos;

            @Override
            public boolean hasNext() {
                if (temp != null) {
                    if (temp.getNext() != null) return true;
                    else {
                        pos = hash2(hash1(tempKey((K) temp.getKey()).hashCode()));
                    }
                }
                for (int i = pos + 1; pos < table.length; i++) {
                    if (table[i] != null) {
                        return true;
                    }
                }
                return false;

            }

            @Override
            public T next() {
                if (temp == table[0]) return (T) temp;
                if (temp.getNext() != null) temp = temp.getNext();
                else {
                    pos = hash2(hash1(tempKey((K) temp.getKey()).hashCode()));
                    for (int i = pos + 1; pos < table.length; i++) {
                        if (table[i] != null) {
                            temp = table[i];
                        }
                    }
                    return null;
                }

                return (T) temp;


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

        public <T> IntKey(T numb) {
            this.numb = (int) (((long) numb) % 2147483647);
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
            return ROT13Hash(string);
        }
    }

    ///////////////////////////////////////////////////
    class HItem<K, V> {
        K key;
        V value;
        HItem next;

        public HItem(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public HItem getNext() {
            return next;
        }

        public void setNext(HItem next) {
            this.next = next;
        }

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


    /*Методы
     2.1 public void add(K key, V value) - добавить пару ключ-значение
    */
    public void add(K key, V value) {
        if (size > 10 && size / (size / 10) >= 10) test();

        int indx = hash2(hash1(tempKey(key).hashCode()));
        HItem head = table[indx];
        HItem tmp = new HItem(key, value);
        table[indx] = tmp;
        if (head != null) tmp.setNext(head);

        size++;
    }

    /*
    2.2 public V get(K key) - получить значение по ключу
    */
    public V get(K key) {
        int indx = hash2(hash1(tempKey(key).hashCode()));
        var slot = table[indx];
        do {
            if (slot.key == key) {
                return (V) slot.getValue();
            } else slot = slot.getNext();
        } while (slot != null);
        return null;
    }

    /*
    2.3 public void remove(K key) - удалить элемент по ключу
    */
    public void remove(K key) {
        int indx = hash2(hash1(tempKey(key).hashCode()));
        HItem slot = table[indx];
        HItem temp = null;
        do {

            if (slot.key == key) {
                if (temp != null) temp.setNext(slot.getNext());
                table[indx] = slot.getNext();
            } else {
                temp = slot;
                slot = slot.getNext();

            }
            slot = slot.getNext();
        } while (slot != null);
        size--;

    }

    /*
2.4 public void change(K key1, Key key2) - изменить значение ключа у элемента с key1 на key2
*/
    public void change(K key1, K key2) {
        V temp = get(key1);
        remove(key1);
        add(key2, temp);
    }


    /*
2.5 public int size() - получить количество элементов

2.6 реализовать интерфейс Iterable<T>
 */
    public int size() {
        return size;
    }

    //////////////////K E Y///////////////////////////////////////
    Object tempKey(K key) {
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
        return key % table.length;
    }

    ///////////// HASH # 2 //////////////////////////////
    private int hash2(int key) {
        double d = A * key;
        return (int) (table.length * (d - Math.floor(d)));
    }

    ///////////// ROT13Hash  /////////////////////
    int ROT13Hash(String str) {
        int hash = 0;

        for (int i = 0; i < str.length(); i++) {
            hash += over(str.charAt(i));
            hash -= over((hash << 13) | (hash >> 19));
        }
        return hash;
    }

    static int over(long num) {
        final int UINT_MAX = 2147483647;

        return ((int) num) % UINT_MAX;
    }

    void test() {
        int coliz = 0;
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                int tempCol = 0;
                var temp = table[i];
                while (temp.getNext() != null) {
                    tempCol++;
                    temp = temp.getNext();
                }
                if (tempCol > coliz) coliz = tempCol;
            }
        }
        if ((coliz / size) * 100 >= 10) {
            LonikedHashTable tempDHT = this;
            Iterator iter = tempDHT.iterator();

            List temp = Arrays.asList(SIM_SIZE);
            table = new HItem[(int) temp.get(temp.indexOf(table.length) + 1)];

            while (iter.hasNext()) {
                HItem tmpHItem = (HItem) iter.next();
                this.add((K) tmpHItem.getKey(), (V) tmpHItem.getValue());
            }
        }


    }

    ///////////////////////////////////////////////////////////////////////////////////
    public static void main(String[] args) {

    }
}
