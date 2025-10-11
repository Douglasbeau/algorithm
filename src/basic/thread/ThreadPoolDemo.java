package basic.thread;

import lombok.SneakyThrows;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolDemo {
    final static AtomicInteger a = new AtomicInteger(1);
    @SneakyThrows
    public static void main(String[] args) {
        ThreadPoolExecutor executorService = new ThreadPoolExecutor(2, 2, 2l, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3), r -> new Thread(r, "t" + a.getAndIncrement()));
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        executorService.execute(() -> {
            System.out.println(Thread.currentThread().getName());
            int i = 1/0;
        }
        );
        executorService.execute(() -> {
            System.out.println(Thread.currentThread().getName());
            int i = 1/0;
        });
        executorService.execute(() -> {
            System.out.println(Thread.currentThread().getName());
            int i = 1/0;
        });
        executorService.execute(
                () -> {
                    System.out.println(Thread.currentThread().getName());
                    System.out.println("done");
                    countDownLatch.countDown();
                });
        countDownLatch.await();
        executorService.shutdown();
    }
}
