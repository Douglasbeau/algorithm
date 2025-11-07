package array;

import java.util.Arrays;

// heap sort with all numbers provided at first
public class HeapSort {
    public static void main(String[] args) {
        HeapSort heapSort = new HeapSort();
        int[] a = new int[]{11, 21, 30, 4, 15, 6, 7, 8, 9};
        heapSort.sortArray(a);
        System.out.println(Arrays.toString(a));
    }
    public int[] sortArray(int[] nums) {
        // heap sort
        int n = nums.length;
        // big root, by heapify
        for (int i=n-1; i>=0; i--) {
            heapify(nums, n-1, i);
        }
        int tail = nums.length - 1;
        // del root n times, moved to tail
        for (int i=0; i<n; i++) {
            swap(nums, 0, tail);
            heapify(nums, --tail, 0);
        }
        return nums;
    }

    // 小值往下沉
    void heapify(int[] nums, int lastIdx, int i) {
        while (2 * i + 1 <= lastIdx) {
            int l = i << 1 | 1;
            int r = l + 1;
            int big = r > lastIdx || nums[l] >= nums[r] ? l : r;
            if (nums[big] <= nums[i])
                break;
            swap(nums, i, big);
            i = big;
        }
    }

    void swap(int[] nums, int i, int j) {
        if (i == j)
            return;
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
