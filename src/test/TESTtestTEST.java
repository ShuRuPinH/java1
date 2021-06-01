package test;

import java.util.concurrent.CountDownLatch;

public class TESTtestTEST {

    static void sum(int a, int b) {
        System.out.println(a + b);


    }


    public static void main(String[] args) {


        CountDownLatch cDl = new CountDownLatch(20);

        for (int i = 0; i < 20; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    cDl.countDown();
                    if (cDl.getCount() % 2 == 0) TESTtestTEST.sum(2, 3);
                    else TESTtestTEST.sum(3, 4);
                }
            }).start();
        }
        try {
            cDl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
