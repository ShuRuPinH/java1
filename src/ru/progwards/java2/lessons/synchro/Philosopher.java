package ru.progwards.java2.lessons.synchro;

public class Philosopher extends Thread {

    String name;
    int number;
    Fork right;// - вилка справа
    Fork left;// - вилка слева
    long reflectTime;// - время, которое философ размышляет в мс
    long eatTime;// - время, которое философ ест в мс
    long reflectSum = 0;// - суммарное время, которое философ размышлял в мс
    long eatSum = 0;// - суммарное время, которое философ ел в мс

    void setName() {
        switch (number) {
            case 1:
                name = "Маркс";
                break;
            case 2:
                name = "Юм";
                break;
            case 3:
                name = "Витгенштейн";
                break;
            case 4:
                name = "Ницше";
                break;
            case 5:
                name = "Платон";
                break;
            case 6:
                name = "Кант";
                break;
            case 7:
                name = "Фома Аквинский";
                break;
            case 8:
                name = "Сократ";
                break;
            case 9:
                name = "Аристотель";
                break;
            case 10:
                name = "Поппер";
                break;
            default:
                name = "Малоизвестный философ";
        }
    }


    public Philosopher(long reflectTime, long eatTime) {
        this.reflectTime = reflectTime;
        this.eatTime = eatTime;



    }

    void setNumber(int n) {
        number = n;
        left = Simposion.forks[n];
        right = Simposion.forks[n == 1 ? Simposion.PandF : n - 1];
        setName();
        doing();
    }
// void reflect() - размышлять. Выводит "размышляет "+ name на консоль с периодичностью 0.5 сек

    void reflect() {
        left.setFree(true);
        right.setFree(true);

        while (true) {
            System.out.println("размышляет " + name);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

// void eat() - есть. Выводит "ест "+ name на консоль с периодичностью 0.5 сек

    void eat() {
        left.setFree(false);
        right.setFree(false);
        while (true) {
            System.out.println("ест " + name);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    void doing() {
        while (true) {
            try {
                if (left.isFree() && right.isFree()) {
                    eat();
                    Thread.sleep(eatTime);
                    eatSum += eatTime;
                }
                reflect();
                Thread.sleep(reflectTime);
                reflectSum+=reflectTime;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /*
    Задача 2. Задача об обедающих философах

Решить задачу об обедающих философах

2.1. Создать class Philosopher - философ

Каждый философ реализует 2 метода

- void reflect() - размышлять. Выводит "размышляет "+ name на консоль с периодичностью 0.5 сек

- void eat() - есть. Выводит "ест "+ name на консоль с периодичностью 0.5 сек

Каждый философ имеет следующие свойства

- String name

- Fork right - вилка справа

- Fork left - вилка слева

- long reflectTime - время, которое философ размышляет в мс

- long eatTime - время, которое философ ест в мс

- long reflectSum - суммарное время, которое философ размышлял в мс

- long eatSum - суммарное время, которое философ ел в мс

Остальные методы и свойства по вашему усмотрению


Создать сеттеры и геттеры для вилок
2.2 Создать класс Fork - вилка, которая имеет 2 состояния : может быть свободна или занята

2.3 Создать class Simposion - философская беседа с методами:

- конструктор Simposion(long reflectTime, long eatTime), который инициализирует необходимое количество философов и вилок. Каждый философ выполняется в отдельном потоке. reflectTime задает время в мс, через которое философ проголодается, eatTime задает время в мс, через которое получив 2 вилки философ наестся и положит вилки на место

-  метод start() который запускает философскую беседу

- метод stop() который завершает философскую беседу

- метод print() который печатает результаты беседы в формате
Философ name, ел ххх, размышлял xxx
где ххх время в мс

- метод main который реализует тест для философской беседы. Проверить варианты, когда ресурсов (вилок) достаточно (философы долго размышляют и мало едят) и вариант когда не хватает (философы много едят и мало размышляют)

     */
}
