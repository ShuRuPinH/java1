package ru.progwards.java2.lessons.gc;


import java.lang.ref.WeakReference;
import java.util.*;

public class Heap {
    //long lstart, lstop, lstop2;
    static byte[] bytes;

    SortedMap<Integer, Integer> vacant = new TreeMap<>();

    static HashMap<Integer, Integer> fill = new HashMap<>();
    boolean outOfMem = false;
    boolean rDir = false;
    int right;
    int left;
    int def = 0;
    static int fitSize = 0;


    Heap(int maxHeapSize) {

        long start = System.currentTimeMillis();
        bytes = new byte[maxHeapSize];
        long stop = System.currentTimeMillis();

        System.out.println(" create [ms] = " + (stop - start) + "        butes[0]/[last]=" + bytes[0] + "/" + bytes[bytes.length - 1]);

        right = bytes.length - 1;
        left = 0;
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

    static void fill(int pos, int size) {
        //System.out.print(pos+"-"+size+" / ");
        fitSize += size;
        for (int i = pos; i < pos + size; i++) {
            bytes[i] = 7;

        }

        fill.put(pos, size);
        // System.out.println(" ADD pos:"+pos+"  size="+size);

    }


    public int malloc(int size) throws OutOfMemoryException {

        def++;
        if (def % 7_000_000 == 0) {
            System.out.println(" compact 7M");

            compact();
        }
        if (left + size >= bytes.length) {
            //     System.out.print("m  sum="+(size+left));
          /*  int p = fromList(size);
            if (p >= 0) return p;*/
            compact();

            int p = fromList(size);
            if (p >= 0) return p;
            throw new OutOfMemoryException(" Out of mem size / left = " + size + "/" + left);


        }

        int pos = left;
        left = left + size + 1;
        fill(pos, size);
        vacant.remove(pos);
        vacant.put(left, bytes.length - 1);
        return pos;

        // System.out.print("O");


    }


    int fromList(int siz) {


        if (def % 30_000 == 0) {
            compact();
        }
        //  System.out.print("+");
        int pos = -1;
        int posEnd = -1;

        for (Map.Entry<Integer, Integer> x : vacant.entrySet()) {
            if (x.getValue() + 1 - x.getKey() >= siz) {
                pos = x.getKey();
                posEnd = x.getValue();
                break;
            }
        }
        if (pos == -1) {
            compact();
            try {
                malloc(siz);
            } catch (OutOfMemoryException e) {
                e.printStackTrace();
            }
        } else {
            vacant.put(pos + siz, posEnd);
            fill(pos, siz);
            vacant.remove(pos);
        }
        //  System.out.println("!!! Fromlist   pos:"+pos);
        return pos;
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
            vacant.put(ptr, ptr + size - 1);
            for (int i = ptr; i < ptr + size; i++) {
                bytes[i] = 0;
            }

            if ((vacant.size() % 150_000) == 0) {
                //   System.out.println(vacant.size());
                long start = System.currentTimeMillis();
                defrag();
                long stop = System.currentTimeMillis();

                //System.out.println(" defrag = "+(stop-start)+"       vacant.size() ="+vacant.size());
            }
            fill.remove(ptr);
            fitSize = fitSize - size;
        } else throw new InvalidPointerException("InvalidPointerException point:" + ptr);
    }

    /*4. Метод public void defrag() - осуществляет дефрагментацию кучи - ищет смежные свободные блоки, границы которых соприкасаются
    и которые можно слить в один.*/
    public void defrag() {
        // System.out.println("START defrag  vacants="+vacant.size());
        // System.out.println(vacant.toString());
        if (vacant.isEmpty()) return;
        int start = vacant.firstKey();


        Map<Integer, Integer> temp = new TreeMap(vacant);
        for (Map.Entry<Integer, Integer> x : temp.entrySet()) {
            //  System.out.println("START="+start+"/"+ vacant.get(start)+"     x.KEY="+x.getKey());

            if (x.getKey() == start) {
                continue;

            }
            if ((vacant.get(start) + 1) == (int) x.getKey()) {
                // System.out.println("   NEAR            START.end="+(vacant.get(start)+"    x.KEY="+x.getKey()));
                vacant.put(start, x.getValue());
                vacant.remove(x.getKey());
                continue;
            }
            start = x.getKey();

        }

        ends();
        //    System.out.println("END defrag  vacants="+vacant.size());

    }

    void ends() {
        int tmp = -1;
        if (vacant.containsKey(right + 1)) {
            System.out.println("  END     right");
            tmp = right + 1;
            right = vacant.get(tmp);

        }

        for (Map.Entry<Integer, Integer> x : vacant.entrySet()) {
            if (x.getValue() - 1 == left) {
                System.out.println("  END     left");
                tmp = left;
                left = x.getKey();

            }

        }
        if (tmp != -1) vacant.remove(tmp);
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
            if (bytes[i] > 0) {
                bytes[count++] = bytes[i];
            }

        }
        long stop = System.currentTimeMillis();
        //  System.out.println(" all massive & > & copy [ms] = "+(stop-start));


        for (int i = count; i < bytes.length; i++) {
            bytes[i] = -1;
        }
        vacant.clear();
        if (count == fitSize) System.out.println("count!=fitSize   " + count + "/" + fitSize);
        left = count;
        vacant.put(left, bytes.length);


        //left=count;
        right = bytes.length - 1;


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