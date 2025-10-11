package array;
//
public class MyCircularQueue {
    private final int[] arr;
    private int head = 0;
    private int tail = 0;
    private final int length;
    public MyCircularQueue(int k) {
        length = k + 1;
        arr = new int[length];
    }

    public boolean enQueue(int value) {
        if (isFull())
            return false;
        arr[tail] = value;
        tail = (tail + 1) % length;
        return true;
    }

    public boolean deQueue() {
        if (isEmpty())
            return false;
        head = (head + 1) % length;
        return true;
    }

    public int Front() {
        if (isEmpty())
            return -1;
        return arr[head];
    }

    public int Rear() {
        if (isEmpty())
            return -1;
        return arr[(tail + length - 1) % length];
    }

    public boolean isEmpty() {
        return head == tail;
    }

    public boolean isFull() {
        return (head - tail + length) % length == 1;
    }

    public static void main(String[] args) {
        MyCircularQueue myQueue = new MyCircularQueue(4);
//        System.out.println(myQueue.isEmpty());

        myQueue.enQueue(2);
        myQueue.enQueue(4);
//        System.out.println(myQueue.Front());
//        System.out.println(myQueue.Rear());
        myQueue.enQueue(4);
        System.out.println(myQueue.enQueue(5));
        System.out.println(myQueue.enQueue(5));

        System.out.println(myQueue.isEmpty());
        System.out.println(myQueue.isFull());

        myQueue.deQueue();
        myQueue.deQueue();

        System.out.println(myQueue.Front());
        System.out.println(myQueue.Rear());
        myQueue.deQueue();


        System.out.println(myQueue.deQueue());
        System.out.println(myQueue.deQueue());

    }
}
