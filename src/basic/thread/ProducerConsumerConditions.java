package basic.thread;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

// Condition用于生产者消费者模型，有选择地通知
public class ProducerConsumerConditions {
    private static final ReentrantLock LOCK = new ReentrantLock();
    private static final Condition notFull = LOCK.newCondition();
    private static final Condition notEmpty = LOCK.newCondition();
    private static final int MAX_SIZE = 3;
    private static int size;

    public static void main(String[] args) throws InterruptedException {
        Runnable p = () -> {
            int i = 10_0000;
            while (i-- != 0) {
                LOCK.lock();
                try {
                    while (size == MAX_SIZE) {
                        try {
                            notFull.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    size++;
                    System.out.println(Thread.currentThread().getName() + " produced, now " + size);
                    notEmpty.signal();
                } finally {
                    LOCK.unlock();
                }
            }
        };

        Runnable c = () -> {
            int i = 10_0000;
            while (i-- != 0) {
                LOCK.lock();
                try {
                    while (size == 0) {
                        try {
                            notEmpty.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    size--;
                    System.out.println(Thread.currentThread().getName() + " consumed, now " + size);
                    notFull.signal();
                    TimeUnit.SECONDS.sleep(1000);
                } catch (Exception e){

                } finally {
                    LOCK.unlock();
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
