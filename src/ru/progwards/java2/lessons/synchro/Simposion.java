package ru.progwards.java2.lessons.synchro;

import java.util.ArrayList;
import java.util.List;

public class Simposion {
   static int  PandF=5;
   public static Fork [] forks ;
   public static Philosopher [] philos;
    long reflectTime;
    long eatTime;
    List<Long> threads= new ArrayList<>();



    public Simposion(long reflectTime, long eatTime) {
        this.reflectTime = reflectTime;
        this.eatTime = eatTime;
        forks =new Fork[PandF];



        for (int i = 1; i <= PandF; i++) {
            forks [i]= new Fork(i);
            philos [i]= new Philosopher(reflectTime,eatTime);
            philos [i].setNumber(i);
            try {
                philos [i].wait();
                threads.add(philos[i].getId());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }



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
   void start(){
       notifyAll();
    }

    void stop(){
       var ts = Thread.getAllStackTraces().keySet();
       for (Thread t: ts){
           if (threads.contains(t.getId())){
               t.interrupt();
           }
       }
    }

    void print(){
        for ( Philosopher p: philos
             ) {
            System.out.println("Философ "+p.name+", ел "+p.eatSum+", размышлял "+p.reflectSum);
        }
    }

    public static void main(String[] args) {
        Simposion test = new Simposion(2000,500);
        test.start();
    }

}
