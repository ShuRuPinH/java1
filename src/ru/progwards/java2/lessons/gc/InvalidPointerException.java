package ru.progwards.java2.lessons.gc;

public class InvalidPointerException extends Throwable {
    public InvalidPointerException(String tt) {
        System.out.println(tt);
        Heap.printMemInfo(tt);
    }

    public InvalidPointerException() {
        System.out.println("tt");
    }
}
