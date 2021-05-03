package ru.progwards.java2.lessons.synchro;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Simposion {
    static int PandF = 5;
    public static Fork[] forks;
    public static Philosopher[] philos;
    long reflectTime;
    long eatTime;
    List<Thread> threads = new ArrayList<>();


    public Simposion(long reflectTime, long eatTime) {
        this.reflectTime = reflectTime;
        this.eatTime = eatTime;
        forks = new Fork[PandF + 1];
        philos = new Philosopher[PandF + 1];

        for (int i = 1; i <= PandF; i++) {
            forks[i] = new Fork(i);
            forks[i].setFree(true);
        }


        threadsStart();


    }


    void threadsStart() {
        threads.clear();

        for (int i = 1; i <= PandF; i++) {


            Thread tp = new Philosopher(reflectTime, eatTime);

            philos[i] = (Philosopher) tp;
            (philos[i]).setNumber(i);

            threads.add(philos[i]);
            philos[i].start();                // отстановка в цикле, через флаг


           /* String thrdName = philos[i].getName();
            System.out.println("thrdID : " + thrdID + "           thrdNabe : " + thrdName);*/


        }
    }

    /*
      2.3 Создать class Simposion - философская беседа с методами:

  - конструктор Simposion(long reflectTime, long eatTime), который инициализирует необходимое количество философов и вилок.
  Каждый философ выполняется в отдельном потоке. reflectTime задает время в мс,
   через которое философ проголодается, eatTime задает время в мс,
   через которое получив 2 вилки философ наестся и положит вилки на место

  -  метод start() который запускает философскую беседу

  - метод stop() который завершает философскую беседу

  - метод print() который печатает результаты беседы в формате
  Философ name, ел ххх, размышлял xxx
  где ххх время в мс

  - метод main который реализует тест для философской беседы. Проверить варианты, когда ресурсов (вилок) достаточно
  (философы долго размышляют и мало едят) и вариант когда не хватает (философы много едят и мало размышляют)

       */
    void start() {
        System.out.println(" --- S T A R T ---");

        //  System.out.println("threads.size =" + threads.size());

        for (Thread thread : threads) {
            //     System.out.println("thread name=" + thread.getName() + " state: "+ thread.getState());

            ((Philosopher) thread).setDoing(true);


        }
    }

    void stop() {
        System.out.println(" --- S T O P ---");

        for (Thread thread : threads) {
            ((Philosopher) thread).setDoing(false);
            while (!((Philosopher) thread).checkDone()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            thread.interrupt();


        }

        print();

    }


    void print() {


        for (Philosopher p : philos
        ) {
            if (p != null) {
                while (!p.checkDone()) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Философ " + p.name + ", ел " + p.eatSum + ", размышлял " + p.reflectSum);
            }
        }
    }

    public static void main(String[] args) {
        Simposion test = new Simposion(5000, 1000);
        test.start();
        try {
            Thread.sleep(20_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Много филосовствуют (5 сек) , мало едят (1 сек):");
        test.stop();
        try {
            Thread.sleep(1_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Simposion test2 = new Simposion(2000, 2000);
        test2.start();
        try {
            Thread.sleep(20_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println();

        System.out.println("Одиннаково филосовствуют и едят ( 2 сек):");

        test2.stop();
        try {
            Thread.sleep(1_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Simposion test3 = new Simposion(1000, 2000);
        test3.start();
        try {
            Thread.sleep(20_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();
        System.out.println("Мало филосовствуют (1 сек), много едят (2 сек):");
        test3.stop();


    }

}
