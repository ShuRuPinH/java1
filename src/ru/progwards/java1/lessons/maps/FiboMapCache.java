package ru.progwards.java1.lessons.maps;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class FiboMapCache {
    private Map<Integer, BigDecimal> fiboCache = new HashMap<>();
    boolean swch;

    public FiboMapCache() {
        swch = false;
    }

    public FiboMapCache(boolean cacheOn) {
        swch = cacheOn;
    }

    public BigDecimal fiboNumber(int n) {

        BigDecimal temp = new BigDecimal(0);
        if (swch && fiboCache.isEmpty() == false && fiboCache.containsKey(n)) {
            temp = fiboCache.get(n);

        } else {

            int f1 = 0;
            int f2 = 1;
            int f;
            int i = 1;
            do {
                f = f1 + f2;
           //     System.out.println("f(" + swch + ")=" + f);
                f1 = f2;
                f2 = f;
                i++;
            } while (i < n);
            temp = BigDecimal.valueOf(f);
            fiboCache.put(n, temp);

        }
        return temp;
    }

    public void clearCahe() {
        fiboCache = null;
    }
/*
Кеш для чисел Фибоначчи на Map. Кеш имитирует таковой на сервере,
когда идут запросы со случайными параметрами от разных пользователей.

Сам алгоритм чисел Фибоначчи - это просто пример некоего алгоритма, который долго работает,
в сравнении с вытаскиванием значения из кэша.
Считается, что кеш ничего не знает об алгоритме, и умеет только сохранять и доставать значения по ключу.


1.1 Определить приватную локальную переменную fiboCache как Map<Integer, BigDecimal>

1.2 Определить конструктор public FiboMapCache(boolean cacheOn) - включен ли кэш.
При cacheOn = true кэш работает, при cacheOn = false - выключен

1.3 Реализовать public BigDecimal fiboNumber(int n). Алгоритм работы следующий:

в функции проверить, находится ли вычисленное значение для n в кэше, и если да - вернуть его из кэша,
если нет - рассчитать и добавить в кэш. Учитывать значение переменной cacheOn

1.4 Реализовать метод public void clearCahe() который устанавливает переменную fiboCache в null

1.5 Для проверки работы реализовать public static void test() - тест для расчета чисел Фибоначчи
от n = 1 до 1000 включительно и замерить разницу во времени с on = true и on = false, результат вывести на
 экран в формате "fiboNumber cacheOn=??? время выполнения ???" для cacheOn=true и cacheOn=false, вместо ???
 вывести реальные значения в мсек.
 */

    public static void test() {
        FiboMapCache cashON = new FiboMapCache(true);
        FiboMapCache cashOFF = new FiboMapCache(false);
        long start = 0;

        start = System.currentTimeMillis();
        for (int i = 1; i <= 1000; i++) {
            cashON.fiboNumber(i);
            //  System.out.println("on  " + i + ":" + cashON.fiboNumber(i));

        }
        long timeON = System.currentTimeMillis() - start;

        start = System.currentTimeMillis();
        for (int i = 1; i <= 1000; i++) {
            cashOFF.fiboNumber(i);
            //  System.out.println("off " + i + ":" + cashOFF.fiboNumber(i));
        }
        long timeOFF = System.currentTimeMillis() - start;


        System.out.println("fiboNumber cacheOn=true время выполнения " + timeON);
        System.out.println("fiboNumber cacheOn=falce время выполнения " + timeOFF);


    }

    public static void main(String[] args) {
        test();
    }
}
