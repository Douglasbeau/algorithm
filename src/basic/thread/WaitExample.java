package basic.thread;

public class WaitExample {
    public static void main(String[] args) {
        Object lock = new Object();
        final Thread thread = new Thread(() -> {
            sleep(100);
            synchronized (lock) { // later
                System.out.println("other thread got lock");
                lock.notify();
                sleep(3000);
                System.out.println("lock released by other thread");
            }

        });
        thread.start();
        sleep(10);
        System.out.println(thread.getState());

        try {
            synchronized(lock) { // earlier
                System.out.println("main thread got lock...");
                lock.wait(500); // 超时会如何，试图再次获取锁
                System.out.println("execute from here after wait");
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println("interrupted main");
            e.printStackTrace();
        }
        System.out.println("Lock acquired!");
    }

    private static void sleep(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}