package ru.progwards.java1.lessons.interfaces;

public class CalculateFibonacci {
    private static CacheInfo lastFibo;

    public static int fiboNumber(int n) {
        if (lastFibo != null) {
            if (n == lastFibo.n) return lastFibo.fibo;
        }
        int f1 = 0;
        int f2 = 1;
        int f;
        int i = 1;
        do {
            f = f1 + f2;
            f1 = f2;
            f2 = f;
            i++;
        } while (i < n);
        lastFibo = new CacheInfo(n, f);
        return f;
    }

    public static class CacheInfo {
        public int n;
        public int fibo;

        public CacheInfo(int n, int fibo) {
            this.n = n;
            this.fibo = fibo;
        }
    }

    public static CacheInfo getLastFibo() {
        return lastFibo;
    }

    public static void clearLastFibo() {
        lastFibo = null;
    }

    public static void main(String[] args) {
        System.out.println(fiboNumber(10));

        System.out.println(lastFibo.fibo);

        System.out.println(fiboNumber(20));

        System.out.println(lastFibo.fibo);

    }
}
