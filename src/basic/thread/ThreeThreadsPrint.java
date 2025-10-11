package basic.thread;

import java.util.concurrent.Semaphore;

// semaphore
public class ThreeThreadsPrint {
    public static void main(String[] args) {
        final Semaphore s1 = new Semaphore(1);
        final Semaphore s2 = new Semaphore(0);
        final Semaphore s3 = new Semaphore(0);
        int time = 4;
        new Thread(() -> {
            for (int i = 0; i < time; i++) {
                try {
                    s1.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.printf("%c", '1' + i);
                s2.release();
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < time; i++) {
                try {
                    s2.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.printf("%c", 'a' + i);
                s3.release();
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < time; i++) {
                try {
                    s3.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.printf("%c\n", 'A' + i);
                s1.release();
            }
        }).start();
    }
}
