package prefix_sum;

import java.util.Arrays;

// LC 3381 长度可被k整除的子数组的最大元素和
// key: 滑动窗口 统计x*k窗口内的最大值
public class MaxSumSubArr {
    public long maxSubarraySum(int[] nums, int k) {
        int n = nums.length;
        long[] max = new long[k]; // [0,k-1]为余数的位置的子数组最大值
        Arrays.fill(max, (long)k * Integer.MIN_VALUE);
        long ans = 0;

        int l = 0;
        int r = 0;
        long winSum = 0;
        while(r < n) {
            winSum += nums[r];
            if (r == k - 1) { // formed window
                max[k-1] = winSum;
                ans = winSum;
            }
            if (r >= k) { // remove left
                winSum -= nums[l++];
                int x = r % k;
                max[x] = Math.max(max[x] + winSum, winSum); // 是否接受左边的
                ans = Math.max(ans, max[x]);
            }
            r++;
        }
        return ans;
    }

    public static void main(String[] args) {
        MaxSumSubArr maxSumSubArr = new MaxSumSubArr();
        long l = maxSumSubArr.maxSubarraySum(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}, 3);
        System.out.println(l);
    }
}
