package basic.thread;

import util.Sleep;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

// 监听到消息 或者 定时状态检查 都能删key
public class MessageWithTimer {
    private static final Random random = new Random(59);
    private static final Set<String> keys = new HashSet<>();

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService exec = Executors.newFixedThreadPool(2,
                new DefaultThreadFactory());

        for (int i = 0; i < 10; i++) {
            Future<Integer> first = exec.submit(new CheckStatus("first"));
            Future<Integer> second = exec.submit(new CheckStatus("second"));
            Sleep.sleepSec(2);
            synchronized (keys) {
                keys.remove("first");
            }
            Sleep.sleepSec(10);
            synchronized (keys) {
                keys.remove("second");
            }

            Integer r1 = first.get();
            Integer r2 = second.get();
            System.out.printf("first: %d, second: %d%n---%n", r1, r2);
        }



        exec.shutdown();
    }

    private static class CheckStatus implements Callable<Integer> {
        String key;
        CheckStatus(String key) {
            keys.add(key);
            this.key = key;
        }
        @Override
        public Integer call() throws Exception {
            while (keys.contains(key)) {
                int checkResult = random.nextInt(5);
                System.out.printf("%s: Random: %s%n", Thread.currentThread().getName(), checkResult);
                if (checkResult == 0) {
                    System.out.printf("Pricer checker found failure and end checker: %s%n", key);
                    keys.remove(key);
                    return -1;
                }
                Sleep.sleepSec(2);
            }
            System.out.printf("Checker %s canceled due to onMessage :)%n", key);
            return 0;
        }
    }


    private static class DefaultThreadFactory implements ThreadFactory {
        AtomicInteger ai = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "status-check-" + ai.getAndIncrement());
        }
    }
}
