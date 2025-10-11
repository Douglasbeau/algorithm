package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 返回子序列 非递增
public class MinSubsequenceInNonIncreasingOrder {
    public List<Integer> minSubsequence(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        int target = sum / 2;
        int cnt = 0;
        int subsum = 0;

        // 从后向前，和大于target停
        while (target >= subsum) {
            subsum += nums[nums.length - 1 - cnt++];
        }
        List<Integer> result = new ArrayList<>(cnt);
        for (int i=0; i<cnt; i++) {
            result.add(nums[nums.length - 1 - i]);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = new int[] {4, 3, 10, 9, 8};
        MinSubsequenceInNonIncreasingOrder m = new MinSubsequenceInNonIncreasingOrder();
        List<Integer> integers = m.minSubsequence(arr);
        System.out.println(integers);
    }
}
