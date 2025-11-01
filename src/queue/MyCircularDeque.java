package queue;

public class MyCircularDeque {
    private final int arrSize;
    private final int[] arr;
    private int head;
    private int tail;

    public MyCircularDeque(int k) {
        arrSize = k + 1;
        arr = new int[arrSize];
    }

    public boolean insertFront(int value) {
        if (isFull())
            return false;
        head = (head - 1 + arrSize) % arrSize;
        arr[head] = value;
        return true;
    }

    public boolean insertLast(int value) {
        if (isFull())
            return false;
        arr[tail] = value;
        tail = (tail + 1) % arrSize;
        return true;
    }

    public boolean deleteFront() {
        if (isEmpty())
            return false;
        head = (head + 1) % arrSize;
        return true;
    }

    public boolean deleteLast() {
        if (isEmpty())
            return false;
        tail = (tail - 1 + arrSize) % arrSize;
        return true;
    }

    public int getFront() {
        return isEmpty() ? -1 : arr[head];
    }

    public int getRear() {
        return isEmpty() ? -1 : arr[(tail - 1 + arrSize) % arrSize];
    }

    public boolean isEmpty() {
        return tail == head;
    }

    public boolean isFull() {
        return (tail + 1 - head) % arrSize == 0;
    }

    public static void main(String[] args) {
        MyCircularDeque cd = new MyCircularDeque(5);
        cd.insertLast(1);
        cd.insertLast(2);
        cd.insertLast(3);
        cd.insertLast(4);
        System.out.println(0b01^0b10);
        int i = cd.countOnes(7);
        System.out.println(i);
    }
    private  int countOnes(int num) {
        int cnt = 0;
        while(num != 0) {
            cnt = cnt + (num & 1);
            num = num >>1;
        }
        return cnt;
    }
}
