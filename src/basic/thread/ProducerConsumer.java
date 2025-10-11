package basic.thread;

import java.util.Date;

// synchronized 用于生产者消费者模型
public class ProducerConsumer {
    private static final Object LOCK = new Object();
    public static int queueSize = 0;
    public static final int MAX_SIZE = 100;

    public static void main(String[] args) throws InterruptedException {
        Runnable p = () -> {
            int i = 10_0000;
            while (i-- != 0) {
                synchronized (LOCK) {
                    while (MAX_SIZE == queueSize)
                        try {
                            LOCK.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    queueSize++;
                    System.out.println("" + Thread.currentThread().getName() + " produced, now " + queueSize);
                    LOCK.notify();

                }
            }
        };
        Runnable c = () -> {
            int i = 10_0000;
            while (i-- != 0) {
                synchronized (LOCK) {
                    while (queueSize == 0)
                        try {
                            LOCK.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    queueSize--;
                    System.out.println("" + Thread.currentThread().getName() + " consumed, now " + queueSize);
                    LOCK.notify();
                }
            }
        };
        long startTime = new Date().getTime();
        Thread[] threads = new Thread[10];
        Thread thread;
        for (int i = 0; i < 5; i++) {
            thread = new Thread(p, "p" + i);
            threads[2*i] = thread;
            thread = new Thread(c, "c" + i);
            threads[2*i + 1] = thread;
        }
        for (Thread t : threads) {
            t.start();
        }
        for (Thread t : threads) {
            t.join();
        }
        System.out.println(new Date().getTime() - startTime + " ms consumed");
    }
}
