package basic.thread;

import java.util.concurrent.*;

public class CyclicBarrierTest {
    private static final class Runner extends Thread {
        private final CyclicBarrier barrier;
        private final int no;
        Runner(CyclicBarrier barrier, int no) {
            this.barrier = barrier;
            this.no = no;
            Thread.currentThread().setName("t-" + no);
        }
        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread() + "waiting");

                barrier.await();
                System.out.println("run!");
                Thread.sleep((int)(Math.random() * 3000));
                if (no == 4)
                    throw new RuntimeException("模拟异常");
            } catch (InterruptedException e) {
                System.out.println("中断");
            } catch (BrokenBarrierException e) {
                System.out.println(Thread.currentThread() + " 异常");
            }
        }
    }

    public static void main(String[] args) {
        final int N = 8;
        CompletableFuture[] arr = new CompletableFuture[N];
        CountDownLatch cdl = new CountDownLatch(1);
        for (int i=0; i<N; i++) {
            final int j = i;
            arr[i] = CompletableFuture.supplyAsync(() -> {
                try {
                    cdl.await();
                    System.out.println("running");
                    Thread.sleep((int)(Math.random() * 5000));
                    if ((j & 1) == 0)
                        throw new RuntimeException("ex");
                    System.out.println("finish");
                } catch (InterruptedException e) {
                    System.out.println("interrupted.." + e.getMessage());
                }
                return j;
            });
        }
        System.out.println("run!");
        cdl.countDown();
        try {
            CompletableFuture.allOf(arr).get();
            System.out.println("all done");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            for (CompletableFuture cf : arr) {
                if (!cf.isCompletedExceptionally())
                    cf.cancel(true);
            }
            e.printStackTrace();
        }

    }
}
