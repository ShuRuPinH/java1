package ru.progwards.java2.lessons.synchro;

class ThreadDefrg extends Thread {
    Heap hp;

    public ThreadDefrg(Heap heap) {
        hp = heap;
    }


    @Override
    public void run() {
        // System.out.println(name + " начал думать");
        while (true) {
            if (hp.vacant.size() > 2 && hp.vacant.size() > 1000) ;
            // System.out.print("d");

            // hp.defrag();
        }
    }
}