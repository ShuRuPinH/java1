package ru.progwards.java2.lessons.gc;

import kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.DeclaredMemberIndex;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.*;

public class Heap2 {
    //long lstart, lstop, lstop2;
    byte[] bytes;

    NavigableMap<Integer, Integer> vacant = new TreeMap<>();

    HashMap<Integer, Integer> fill = new HashMap<>();
    boolean outOfMem = false;
    List bb = new ArrayList();


    Heap2(int maxHeapSize) {
        bytes = new byte[maxHeapSize];
    }

    public int worker(boolean add, int pos, int size, int end) {
        //System.out.println("START worker   -vacant.toString="+vacant.toString());
        if (add) {
            //    System.out.println("worker add:   pos="+pos+"    size="+size +"     end="+end);
            for (int i = pos; i <= pos + size - 1; i++) {
                bytes[i] = 88;
            }
            vacant.remove(end - pos + 1);
            vacant.put(end - pos + 1 - size, pos + size);
            // lstop2 = System.currentTimeMillis();
            //System.out.println(lstart+"time ceiling: "+(lstop-lstart)+"         worker="+(lstop2-lstop));
            return pos;
        } else {
            //           System.out.println("worker DELETE :"+pos);
            //  System.out.println("worker fill :"+ fill.toString());
            for (int i = pos; i <= pos + size - 1; i++) {
                bytes[i] = -01;
            }
            vacant.put(size, pos);
            return 0;
        }
    }

    /*
2. Метод public int malloc(int size) - "размещает", т.е. помечает как занятый блок памяти с количеством ячеек массива heap
равным size. Соответственно это должен быть непрерывный блок (последовательность ячеек), которые на момент выделения свободны.
 Возвращает "указатель" - индекс первой ячейки в массиве, размещенного блока.
 переделать поиск по значению

 */
    static void printMemInfo(String info) {
        System.out.println(String.format("%1$tI:%1$tM:%1$tS.%1$tN", new Date()));

        Runtime runtime = Runtime.getRuntime();
        System.out.println("всего: " + runtime.totalMemory() / 1048576 + "MB");
        System.out.println("максимально: " + runtime.maxMemory() / 1048576 + "MB");
        System.out.println("свободно: " + runtime.freeMemory() / 1048576 + "MB");

    }


    public int malloc(int size) throws OutOfMemoryException {
        //     System.out.println("malloс size="+size);

        //  printMemInfo("baits =" + (vacant.size()+fill.size())/1048576+"MB");
   /*  if (fill.size()%100000==0) {
         System.out.println(" !! GC !!");
         System.gc();
     }*/

        if (vacant.isEmpty()) {
            fill.put(0, size);
            bb.add(0);

            worker(true, 0, size, bytes.length);
            ;
            return 0;
        } else {

            try {
                //   lstart = System.currentTimeMillis();
                var tmp = vacant.ceilingEntry(size);
                //   System.out.println("<< Malloc   "+ vacant.toString()+"!!!!!!!!!!!!!!!!!");

                fill.put(tmp.getValue(), size);
                //   lstop = System.currentTimeMillis();

                return worker(true, tmp.getValue(), size, tmp.getKey() + tmp.getValue() - 1);


            } catch (NullPointerException e) {
                if (!outOfMem) {
                    System.out.println("piece=" + size + " err:");
                    e.printStackTrace();
                    compact();
                    //outOfMem = true;
                    malloc(size);
                }
            }


            throw new OutOfMemoryException("OutOfMemoryException");
        }
    }

    /*
3. Метод public void free(int ptr) - "удаляет", т.е. помечает как свободный блок памяти по "указателю". Проверять валидность
указателя - т.е. то, что он соответствует началу ранее выделенного блока, а не его середине, или вообще, уже свободному.

ЗАЧЕМ ВСЕ ПЕРЕБИРАТЬ???  MAP

*/
    public void free(int ptr) throws InvalidPointerException {
        //    System.out.println("free     ptr="+ptr);
        if (fill.containsKey(ptr)) {

            worker(false, ptr, fill.get(ptr), ptr + fill.get(ptr) - 1);


        } else throw new InvalidPointerException("InvalidPointerException point:" + ptr);
    }

    /*4. Метод public void defrag() - осуществляет дефрагментацию кучи - ищет смежные свободные блоки, границы которых соприкасаются
    и которые можно слить в один.*/
    public void defrag() {


    }

    /*
5. Метод public void compact() - компактизация кучи - перенос всех занятых блоков в начало хипа, с копированием самих данных
- элементов массива. Для более точной имитации производительности копировать просто в цикле по одному элементу,
не используя System.arraycopy. Обязательно запускаем compact из malloc если не нашли блок подходящего размера*/
    public void compact() {
        System.out.println("--------------- compact");
        int count = 0;
        byte[] temp = new byte[bytes.length];

        for (byte x : bytes) {
            if (x > 0) {
                temp[count] = x;
                count++;
            }
        }
        System.out.println(bytes.length - count);
        vacant.clear();
        fill.clear();
        vacant.put(temp.length - count, temp.length - count);
        fill.put(0, count - 1);
        bytes = temp;

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
        //   System.arraycopy(this.bytes, ptr, bytes, 0, size);
    }

    public void setBytes(int ptr, byte[] bytes) {
        //  System.arraycopy(bytes, 0, this.bytes, ptr, size);
    }
}