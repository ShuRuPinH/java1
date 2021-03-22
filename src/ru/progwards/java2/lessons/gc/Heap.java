package ru.progwards.java2.lessons.gc;


import com.sun.source.tree.BinaryTree;

import java.util.*;

public class Heap {
    //long lstart, lstop, lstop2;
    static byte[] bytes;

    static TreeMap<Integer, Integer> vacant = new TreeMap();
    //  static ArrayList<Integer> list100 = new ArrayList<>();
    //  static ArrayList<Integer> list1000 = new ArrayList<>();
    //  static ArrayList<Integer> list10000 = new ArrayList<>();


    static TreeMap<Integer, Integer> fill = new TreeMap<>();

    static TreeMap<Integer, Integer> newFill = new TreeMap<>();

    static TreeMap<Integer, String> test = new TreeMap<>();
    int testH = 0;

    boolean once = true;
    int def = 0;
    static int fitSize = 0;
    int left = 0;


    Heap(int maxHeapSize) {

        long start = System.currentTimeMillis();
        bytes = new byte[maxHeapSize];
        long stop = System.currentTimeMillis();

        System.out.println(" create [ms] = " + (stop - start) + "        butes[0]/[last]=" + bytes[0] + "/" + bytes[bytes.length - 1]);


        vacant.put(0, bytes.length - 1);
    }


    /*
2. Метод public int malloc(int size) - "размещает", т.е. помечает как занятый блок памяти с количеством ячеек массива heap
равным size. Соответственно это должен быть непрерывный блок (последовательность ячеек), которые на момент выделения свободны.
 Возвращает "указатель" - индекс первой ячейки в массиве, размещенного блока.
 переделать поиск по значению

 */
    static void printMemInfo(String info) {
        System.out.println(String.format("%1$tI:%1$tM:%1$tS", new Date()));

        Runtime runtime = Runtime.getRuntime();
        System.out.println("всего: " + runtime.totalMemory() / 1048576 + "MB");
        System.out.println("максимально: " + runtime.maxMemory() / 1048576 + "MB");
        System.out.println("свободно: " + runtime.freeMemory() / 1048576 + "MB");

    }

    static void put(int start, int end, int pos) {
        //System.out.print(pos+"-"+size+" / ");
        fitSize += end - start + 1;
        fill.put(start, pos - start);
        //     test(start,"put/");

        vacant.remove(start);
        putIn(pos, end);
    }


    static void test(int ptr, String from) {
        String temp;

        if (test.containsKey(ptr)) temp = test.get(ptr) + " + " + from;
        else temp = from;

        test.put(ptr, temp);
    }


    public int malloc(int size) throws OutOfMemoryException {
        int start;
        int endSpace;
        int newPosFreeSpace;
        def++;

        if (left > bytes.length * 0.5 && once) {

            defrag();

            once = false;
        }

        if (left < bytes.length) {
            start = left;
            endSpace = bytes.length;
            newPosFreeSpace = left + size;
            left = newPosFreeSpace;
        } else {
            if (vacant.size() > 100) defrag();
            int fL = fromList(size);
            if (fL >= 0) return fL;

            else {
                compact();
                throw new OutOfMemoryException("no space in vacant");
            }

        }
        put(start, endSpace, newPosFreeSpace);


        return start;
    }

    int fromList(int size) {
        int str;
        int en;

        for (Map.Entry<Integer, Integer> e : vacant.entrySet()) {
            str = e.getKey();
            en = e.getValue();

            if (size <= en - str + 1) {
                put(str, en, str + size);
                return str;
            }
        }
        return -100;


    }


    /*
3. Метод public void free(int ptr) - "удаляет", т.е. помечает как свободный блок памяти по "указателю". Проверять валидность
указателя - т.е. то, что он соответствует началу ранее выделенного блока, а не его середине, или вообще, уже свободному.

ЗАЧЕМ ВСЕ ПЕРЕБИРАТЬ???  MAP

*/
    public void free(int ptr) throws InvalidPointerException {
        //    System.out.println("free     ptr="+ptr);
        def++;
        if (fill.containsKey(ptr)) {
            int size = fill.get(ptr);

            putIn(ptr, ptr + size - 1);

            fill.remove(ptr);
            //    test(ptr, "free");

        } else
            throw new InvalidPointerException("InvalidPointerException point:" + ptr + "     test: " + test.get(ptr));
    }

    /*4. Метод public void defrag() - осуществляет дефрагментацию кучи - ищет смежные свободные блоки, границы которых соприкасаются
    и которые можно слить в один.*/
    public void defrag() {

 /*       System.out.println(" START DEFRAG   " +"\n"+
                "left="+left+
                "        vacantH.size()" + vacant.size()+"\n"
        );*/
        TreeMap<Integer, Integer> temp = new TreeMap<>();

        temp.putAll(vacant);

        vacant.clear();

        int start = temp.firstKey();
        int end = temp.get(start);

        for (Map.Entry<Integer, Integer> x : temp.entrySet()) {
            //    System.out.println("START="+start+"/"+ temp.get(start)+"     x.KEY="+x.getKey());
            if (x.getKey() == start) {
                continue;
            }
            if (end + 1 == (int) x.getKey()) {
                //       System.out.println("   NEAR            START.end="+(temp.get(start)+"    x.KEY="+x.getKey()));
                end = x.getValue();
                continue;
            } else {
                putIn(start, end);

                end = x.getValue();
            }

            start = x.getKey();


        }
        putIn(start, end);
/*        System.out.println(" DEFRAG REZULT    " +"\n"+
                        "left="+left+
                "        vacantH.size()" + vacant.size()+"\n"
                 );*/


        testH = 0;
    }

    static void putIn(int st, int end) {
        int size = end - st + 1;
        vacant.put(st, end);


    }


    /*
5. Метод public void compact() - компактизация кучи - перенос всех занятых блоков в начало хипа, с копированием самих данных
- элементов массива. Для более точной имитации производительности копировать просто в цикле по одному элементу,
не используя System.arraycopy. Обязательно запускаем compact из malloc если не нашли блок подходящего размера*/
    public void compact() {
        //  System.out.println("--------------- compact");
        int count = 0;

        long start = System.currentTimeMillis();
        for (int i = 0; i < bytes.length; i++) {


        }
        for (Map.Entry x : fill.entrySet()) {
            newFill.put((Integer) x.getKey(), count);
            count = count + (Integer) x.getValue();

        }


        //left=count;


    }
/*
6. Исключения - свои собственные

OutOfMemoryException - нет свободного блока подходящего размера
InvalidPointerException - неверный указатель. Возникает при освобождении блока, если переданный указатель не является началом блока

Для реализации этих методов надо будет завести структуру данных - список (или другая структура данных) свободных блоков.
 При выделении памяти искать блок подходящего размера в этом списке, при освобождении - добавлять его туда.
 Для проверки валидности освобождения указателей - список (или другая структура данных) занятых блоков.
 При компактизации саму процедуру замены старый указателей на новые опускаем, поэтому и делаем не очень эффективное
  копирование самих данных, что бы была близкая производительность.
     */

    public void getBytes(int ptr, byte[] bytes) {
        int point;
        if (newFill.size() > 0) point = newFill.get(ptr);
        else point = ptr;


        System.arraycopy(this.bytes, point, bytes, 0, fill.get(ptr));
    }

    public void setBytes(int ptr, byte[] bytes) {
        //  System.arraycopy(bytes, 0, this.bytes, ptr, size);
    }
}