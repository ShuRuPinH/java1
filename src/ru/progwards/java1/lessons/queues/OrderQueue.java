package ru.progwards.java1.lessons.queues;

import java.util.PriorityQueue;


public class OrderQueue {
    PriorityQueue<PrioOrders> list = new PriorityQueue();


    public void add(Order order) {
        if (order.getSum() <= 10000) list.offer(new PrioOrders(order, 3));
        if (order.getSum() > 10000 && order.getSum() <= 20000) list.offer(new PrioOrders(order, 2));
        if (order.getSum() > 20000) list.offer(new PrioOrders(order, 1));
    }


    static class PrioOrders implements Comparable<PrioOrders> {
        private Order ord;
        private int priority;

        public PrioOrders(Order ord, int priority) {
            this.ord = ord;
            this.priority = priority;
        }

        @Override
        public int compareTo(PrioOrders o) {
            return Integer.compare(priority, o.priority);
        }

        @Override
        public String toString() {
            return priority + " " + ord;
        }
    }

    public Order get() {
        try {
            return list.poll().ord;
        } catch (Exception e) {
            return null;
        }
    }
/*


Создать класс OrderQueue

2.7 Создать метод, public void add(Order order), размещающий заказы в очередь с приоритетом, разбивая их по 3-м классам
3 - заказы до 10000 руб включительно
2 - заказы от 10000 до 20000 руб включительно
1 - заказы от 20000 руб

2.8 Создать метод, public Order get(), возвращающий первый заказ в очереди для обслуживания.
Вначале обслуживаются заказы класса 1, потом 2, потом 3.
Внутри каждого класса заказы должны обслуживаться в порядке поступления (по номеру заказа).
Метод не выбрасывает исключения, возвращает null в случае пустой очереди.

Продумать, и, при необходимости, добавить в классы нужные методы и свойства, для того, чтобы реализовать эту задачу.
 */


}
