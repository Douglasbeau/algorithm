package queue;

import java.util.Arrays;
import java.util.LinkedList;

public class SlideWindowMax {
    public int[] slideMax(int[] arr, int win) {
        LinkedList<Integer> q = new LinkedList<>();
        int[] max = new int[arr.length - win + 1];
        int l = 0, r = 0;
        q.add(r);
        while (r < arr.length) {
            // 去除较小数的下标
            while (!q.isEmpty() && arr[r] >= arr[q.peekLast()]) {
                q.pollLast();
            }
            // 添加自己
            q.add(r);
            // 形成窗口则记录
            if (r - l + 1 == win) {
                max[l] = arr[q.peekFirst()];
                l++;
            }
            // 去掉窗口外的
            if (l > q.peekFirst()) {
                q.pollFirst();
            }
            r++;
        }
        return max;
    }

    public static void main(String[] args) {
        final SlideWindowMax slideWindowMax = new SlideWindowMax();
        final int[] ints = slideWindowMax.slideMax(new int[]{4, 5, 8, 2, 3, 1, 9}, 3);
        System.out.println(Arrays.toString(ints));
    }
}
