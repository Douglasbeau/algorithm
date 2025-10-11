package queue;

import java.util.LinkedList;

// 初始化时给定Window的size，每次调用next(integer) 都计算最近size个的均值
public class MovingAverage {
    int size;
    int preSum = 0;
    LinkedList<Integer> queue = new LinkedList<>();;
    MovingAverage(int size) {
        this.size = size;
    }

    public double next(int val) {
        queue.add(val);
        // 如果queue.size大于窗口，则弹出最早的
        if (queue.size() > size) {
            preSum = preSum - queue.removeFirst();
        }
        preSum = preSum + val;
        return (double)preSum / queue.size();
    }

    public static void main(String[] args) {
        MovingAverage movingAverage = new MovingAverage(3);
        int[] arr = new int[] {2, 3, 4, 5, 6};
        for (int i = 0; i < arr.length; i++) {
            double s = movingAverage.next(arr[i]);
            System.out.println(s);
        }

    }
}
