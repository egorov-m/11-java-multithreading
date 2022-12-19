package org.example;

import java.util.concurrent.atomic.AtomicInteger;

public class Lucky {
    static final AtomicInteger atomicX = new AtomicInteger(-1);
    static final AtomicInteger count = new AtomicInteger(0);

    static class LuckyThread extends Thread {
        @Override
        public void run() {
            int x = atomicX.incrementAndGet();
            while (x < 999999) {

                if ((x % 10) + (x / 10) % 10 + (x / 100) % 10 == (x / 1000)
                        % 10 + (x / 10000) % 10 + (x / 100000) % 10) {
                    System.out.println(Thread.currentThread().getName() + ": " + x);

                    count.incrementAndGet();
                }

                x = atomicX.incrementAndGet();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new LuckyThread();
        Thread t2 = new LuckyThread();
        Thread t3 = new LuckyThread();
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        System.out.println("Total: " + count);
    }
}