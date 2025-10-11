package basic.thread;
import java.util.concurrent.CountDownLatch;
public class CountDownLatchExample {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        // Create and start the threads
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(new WaitingThread(latch), "w-" + i);
            thread.start();
        }
        // Wait for all threads to complete
        latch.countDown();
        // All threads have completed, proceed with further execution
        System.out.println("All threads have completed.");
    }
    static class WaitingThread implements Runnable {
        private final CountDownLatch latch;
        public WaitingThread(CountDownLatch latch) {
            this.latch = latch;
        }
        @Override
        public void run() {
            System.out.println("Thread " + Thread.currentThread().getName() + " waiting.");
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread " + Thread.currentThread().getName() + " is performing work.");
        }
    }
}