package queue;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

// 滑动窗口中最值大问题
// arr：1 2 3 3 4 3 2 5 5 2 6 7
// size是窗口大小，要求，返回一个数组，包含滑动窗口内的最大值
public class WindowMax {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 3, 4, 3, 2, 5, 5, 2, 6, 7, 5, 4, 3, 4};
        int size = 3;
        int[] result = get_max_arr(arr, size);
        System.out.println(Arrays.toString(result));
        result = maxSlidingWindow(arr, size);
        System.out.println(Arrays.toString(result));
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] ans = new int[n - k + 1];
        LinkedList<Integer> max = new LinkedList<>(); // 从尾巴加入大值
        int l = 0;
        int r = 0;//包含
        int i = 0;
        while (r < n) {
            while (!max.isEmpty() && nums[max.peekLast()] <= nums[r]) {
                max.pollLast();
            }
            max.add(r);

            if (r - l + 1 == k) { // 形成窗口
                ans[i++] = nums[max.peekFirst()];
                l++;
                if (l > max.peekFirst()) {
                    max.pollFirst();
                }
            }
            r++;
        }
        return ans;
    }

    private static int[] get_max_arr(int[] arr, int size) {
        if (arr == null || arr.length < size) {
            return new int[0];
        }
        int[] result = new int[arr.length - size+1];
        LinkedList<Integer> mono = new LinkedList<>();
        mono.add(arr[0]);
        for (int i=1; i<arr.length; i++) {
            //窗口还没形成
            if (i<size) {
                if (mono.peekFirst() < arr[i]){
                    mono.pop();
                    mono.add(arr[i]);
                    result[0] = arr[i];
                }
                continue;
            }
            // 窗口形成
            // 左出队列，是头则头出；
            if(arr[i-size] == mono.peek()) {
                mono.pop();
            }
            // arr[i]不大则进队列尾（干掉小的尾部），否则代替队列头
            if (arr[i] <= mono.peek()) {
                while (mono.peekLast() < arr[i]) {
                    mono.removeLast();
                }
                mono.add(arr[i]);
            } else {
                mono.pop();
                mono.addFirst(arr[i]);
            }

            result[i-size+1] = mono.peek();
        }
        return result;
    }
}
