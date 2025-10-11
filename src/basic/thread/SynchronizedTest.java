package basic.thread;

import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

// 使用CountDownLatch 等待所有线程执行完成
// sleep可以被打断进行异常处理，但是synchronized不能
public class SynchronizedTest {
    private final static Random random = new Random(47);

    public static void main(String[] args) {
        final int nThreads = 8;
        Counter sharedCounter = new Counter();
        CountDownLatch countDownLatch = new CountDownLatch(nThreads); // 等待所有进程完成
        ExecutorService executorService = Executors.newCachedThreadPool(new ThreadFactory() {
            final AtomicInteger threadId = new AtomicInteger(0);
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "cm-" + threadId.incrementAndGet());
            }
        });
        for (int i=0; i<nThreads; i++){
            executorService.execute(new CounterManager(sharedCounter, countDownLatch, random));
        }
        try {
            boolean await = countDownLatch.await(5, TimeUnit.SECONDS);// 等待所有进程完成，或者等超过5秒 强制退出
            System.out.println("countDownLatch 超时，开始强制终止线程池");
        } catch (InterruptedException e) {
            System.out.println("线程被终止 " + Thread.currentThread());
        }
        executorService.shutdownNow();
    }

}

class CounterManager implements Runnable{
    private final Counter counter;
    private final CountDownLatch countDownLatch;
    private final Random random;
    CounterManager(Counter counter, CountDownLatch countDownLatch, Random random) {
        this.counter = counter;
        this.countDownLatch = countDownLatch;
        this.random = random;
    }

    @Override
    public void run() {
        if (random.nextBoolean()) {
            System.out.println("next boolean is true");
            counter.up();
        } else {
            System.out.println("next boolean is false");
            counter.down();
        }
        countDownLatch.countDown();
    }
}

class Counter{
    private int num;
    synchronized void up(){
        System.out.println("up");
        num++;
        System.out.println("after up " + num);
    }
    synchronized void down(){
        System.out.println("sleeping to down");
        sleep(2000);
        num--;
        System.out.println("after down " + num);
    }
    public static void sleep(long ms) {
        try {
            TimeUnit.MILLISECONDS.sleep(ms);
        } catch (InterruptedException e) {
            System.out.println("线程被终止" + Thread.currentThread());
        }
    }
}
