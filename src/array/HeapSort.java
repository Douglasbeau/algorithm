package array;

// heap sort with all numbers provided at first
public class HeapSort {
    public int[] sortArray(int[] nums) {
        // heap sort
        int n = nums.length;
        // big root, by heapify
        for (int i=n-1; i>=0; i--) {
            heapify(nums, n - 1, i);
        }
        int tail = n - 1;
        // del root n times, moved to tail
        for (int i=0; i<n; i++) {
            swap(nums, 0, tail);
            heapify(nums, --tail, 0);
        }
        return nums;
    }

    void heapify(int[] nums, int lastIdx, int i) {
        while (2 * i + 1 <= lastIdx) {
            int l = i << 1 | 1;
            int r = l + 1;
            int big = r > lastIdx || nums[l] >= nums[r] ? l : r;
            if (nums[big] > nums[i]) {
                swap(nums, i, big);
                i = big;
            } else {
                break;
            }
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
