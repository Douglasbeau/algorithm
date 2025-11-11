package sorted;

import java.util.Arrays;
import java.util.Random;

public class QuickSort {
    Random random = new Random();
    public void quickSort(int[] nums) {
        process(nums, 0, nums.length - 1);
    }
    private void process(int[] nums, int l, int r) {
        if (l >= r)
            return;
        int rand = random.nextInt(l, r + 1);
        int pivot = nums[rand];
        // partition
        int small = l - 1;
        int i = l;
        int big = r + 1;
        while (i < big) {
            if (nums[i] < pivot) {
                swap(nums, ++small, i++);
            } else if (nums[i] > pivot) {
                swap(nums, i, --big);
            } else {
                i++;
            }
        }
        // sort l,r parts
        process(nums, l, small);
        process(nums, big, r);
    }

    private void swap(int[] nums, int i, int j) {
        if (i == j)
            return;
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();
        int[] nums = {3, 2, 4, 1, 5, 0, 6};
        quickSort.quickSort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
