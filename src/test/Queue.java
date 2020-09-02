package test;

import java.util.ArrayDeque;

public class Queue {

    ArrayDeque<Integer> array2queue(int[] a) {
        ArrayDeque<Integer> qA = new ArrayDeque<>();
        for (int i : a) {
            qA.offer(i);
        }
        return qA;
    }

    void dequeueTest() {
        ArrayDeque deque = new ArrayDeque<>();

        for (int i = 0; i <= 10; i++) {
            deque.offer(i);
            if (i % 2 == 0)
                deque.poll();
        }

        System.out.println(deque);
    }

    int sumLastAndFirst(ArrayDeque<Integer> deque) {
        int res = 0;
        if (deque.peekFirst() != null && deque.peekLast() != null) res = deque.peekFirst() + deque.peekLast();

        return res;
    }

    public static void main(String[] args) {
        Queue test = new Queue();
        test.dequeueTest();
    }
}
