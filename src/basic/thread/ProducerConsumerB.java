package basic.thread;

import java.util.LinkedList;
public class ProducerConsumerB {
    private final LinkedList<Integer> buffer = new LinkedList<>();
    private final int capacity = 5;
    private final Object producerLock = new Object();
    private final Object consumerLock = new Object();
    private boolean isProducing = true;
    public void produce() throws InterruptedException {
        int value = 0;
        while (true) {
            synchronized (producerLock) {
                while (buffer.size() == capacity) {
                    producerLock.wait();
                }
                System.out.println("Producer produced: " + value);
                buffer.add(value++);
                consumerLock.notify();
                Thread.sleep(1000); // Simulating some time for production
            }
        }
    }
    public void consume() throws InterruptedException {
        while (true) {
            synchronized (consumerLock) {
                while (buffer.isEmpty()) {
                    consumerLock.wait();
                }
                int consumedValue = buffer.removeFirst();
                System.out.println("Consumer consumed: " + consumedValue);
                producerLock.notify();
                Thread.sleep(1000); // Simulating some time for consumption
            }
        }
    }
    public static void main(String[] args) {
        ProducerConsumerB producerConsumer = new ProducerConsumerB();

        Thread producerThread = new Thread(() -> {
            try {
                producerConsumer.produce();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        Thread consumerThread = new Thread(() -> {
            try {
                producerConsumer.consume();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        producerThread.start();
        consumerThread.start();
    }
}