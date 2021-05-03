package ru.progwards.java2.lessons.synchro;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Fork {
    int pos;

    Lock lock = new ReentrantLock();

    public Fork(int pos) {
        this.pos = pos;
      //  System.out.println("создана вилка - " + pos);
    }

    //2.2 Создать класс Fork - вилка, которая имеет 2 состояния : может быть свободна или занята
    boolean free;

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        lock.lock();
        this.free = free;
        lock.unlock();
    }
}
