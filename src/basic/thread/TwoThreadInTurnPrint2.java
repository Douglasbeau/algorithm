package basic.thread;

import java.util.concurrent.Semaphore;

// 更通用的交替打印
public class TwoThreadInTurnPrint2 {
    public static void main(String[] args) {
        new Solution(10, 5).solve();
    }

    private static class Solution {
        Thread[] threads;
        Solution(int ts, int each) {
            Semaphore[] ss = new Semaphore[ts];
            ss[0] = new Semaphore(1);
            for (int i = 1; i < ts; i++) {
                ss[i] = new Semaphore(0);
            }

            this.threads = new Thread[ts];
            for (int i = 0; i < ts; i++) {
                threads[i] = new Printer(i, ss[i], ss[(i+1)%ts], each, ts);
            }
        }
        void solve() {
            for (Thread t : threads) {
                t.start();
            }
        }
    }

    // 可以任意线程数，只要定义好线程数（步长）和数量限制
    private static class Printer extends Thread {
        int base; // from 1
        Semaphore aq;
        Semaphore rel;
        int cnt;
        int step;

        Printer(int idx, Semaphore aq, Semaphore rel, int cnt, int step) {
            this.base = idx + 1;
            this.aq = aq;
            this.rel = rel;
            this.cnt = cnt;
            this.step = step;
        }

        @Override
        public void run() {
            for (int i = 0; i < cnt; i++) {
                try {
                    aq.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(base + step * i + " - by " + Thread.currentThread().getName());
                rel.release();
            }
        }
    }
}
