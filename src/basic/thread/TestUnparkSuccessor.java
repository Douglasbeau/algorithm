package basic.thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

// A -> B -> C
// 考虑unpark后继时其状态为取消，则从tail往前变量和unpark，会导致B被忽略吗？no
public class TestUnparkSuccessor {
    static Semaphore semaphore = new Semaphore(2);

    public static void main(String[] args) throws InterruptedException {
        semaphore.acquire();
        semaphore.acquire();

        Thread a = new Thread(new AcqS(), "A");
        Thread b = new Thread(new AcqS(), "B");
        Thread c = new Thread(new AcqS(), "c");
        a.start();
        TimeUnit.SECONDS.sleep(1);
        b.start();
        TimeUnit.SECONDS.sleep(1);
        c.start();
        a.interrupt();
        semaphore.release();
//        semaphore.release();
    }

    private static class AcqS implements Runnable{

        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName() + " got lock");
                TimeUnit.SECONDS.sleep(2);
                semaphore.release();
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " interrupted");
            }
        }
    }
}
