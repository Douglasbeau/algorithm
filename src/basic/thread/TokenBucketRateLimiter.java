package basic.thread;
import java.util.concurrent.atomic.AtomicInteger;

// 令牌桶
public class TokenBucketRateLimiter {
    private final AtomicInteger ai;
    private final int limit;
    private final int rate;
    private long lastTime = System.currentTimeMillis();
    public TokenBucketRateLimiter(int init, int size, int rate) {
        this.ai = new AtomicInteger(init);
        this.limit = size;
        this.rate = rate;
    }

    public boolean tryAcquire() {
        return getToken(1);
    }

    public boolean getToken(int i) {
        addTokens();
        while(true) {
            int available = ai.get();
            if (available < i)
                return false;
            boolean res = ai.compareAndSet(available, available  - i);
            if (res)
                return true;
        }
    }

    private void addTokens() {
        long curMs = System.currentTimeMillis();
        int toAdd = (int) ((curMs - lastTime) * rate / 1000);
        if (toAdd < 1)
            return;
        lastTime = curMs;
        while(true) {
            int cnt = ai.get();
            if (cnt >= limit)
                break;
            boolean res = ai.compareAndSet(cnt, Math.min(limit, toAdd + cnt));
            if (res)
                break;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TokenBucketRateLimiter limiter = new TokenBucketRateLimiter(1, 10, 5);
        long s = System.currentTimeMillis();
        int pass = 0;
        for (int i = 0; i < 20; i++) {
            if (limiter.tryAcquire()) {
                System.out.println("请求 " + i + ": 通过");
                pass++;
            } else {
                System.out.println("请求 " + i + ": 被限流");
            }
            Thread.sleep(10); //
        }
        System.out.println((System.currentTimeMillis() - s) + "ms已过，通过：" + pass);
    }
}
