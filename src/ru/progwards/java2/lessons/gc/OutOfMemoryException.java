package ru.progwards.java2.lessons.gc;

public class OutOfMemoryException extends Throwable {
    public OutOfMemoryException(String tx) {
        System.out.println(tx);
    }
}
