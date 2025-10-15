package basic.thread;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

// xt 面试
public class ProducerConsumerC {
    private static final ReentrantLock LOCK = new ReentrantLock();
    final static Condition NOT_EMPTY = LOCK.newCondition();
    final static Condition NOT_FULL = LOCK.newCondition();
    private static final int SIZE = 100;

    public static void main(String[] args) {
        LinkedList<String > queue = new LinkedList<>();
        Producer producer = new Producer(queue);
        producer.produce("xx");
        Runnable r = () -> {
            Consumer consumer = new Consumer(queue);
            // use
            System.out.println(consumer.consume());
            System.out.println(consumer.consume());
        };
        final Thread consumer = new Thread(r, "consumer");
        consumer.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        producer.produce("yy");

    }
    private static class Producer {
        LinkedList<String> queue;
        Producer(LinkedList<String> queue) {
            this.queue = queue;
        }
        public void produce(String msg) {
            LOCK.lock();
            try {
                while (queue.size() >= SIZE) {
                    try {
                        NOT_FULL.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                queue.add(msg);
                NOT_EMPTY.signal();
            } finally {
                LOCK.unlock();
            }
        }
    }
    private static class Consumer {
        LinkedList<String> queue;
        Consumer(LinkedList<String> queue) {
            this.queue = queue;
        }
        public String consume() {
            LOCK.lock();
            try {
                while (queue.isEmpty()) {
                    try {
                        NOT_EMPTY.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                String msg = queue.removeFirst();
                NOT_FULL.signal();
                return msg;
            } finally {
                LOCK.unlock();
            }
        }
    }
}
