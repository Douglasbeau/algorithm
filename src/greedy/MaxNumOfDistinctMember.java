package greedy;

import java.util.Arrays;

// LC 3397
// 对所有元素执行操作：任选一个在范围[-k, k]内的整数加到该元素上，返回操作后最多有多少不同元素
// 思路，让数字尽可能小，记录已用数字
public class MaxNumOfDistinctMember {
    public int maxDistinctElements(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);
        int ans = 1;
        int left = nums[0] - k; // 最小的数
        // 看后面的会不会有空洞 或重复
        for (int i=1; i<n; i++) {
            int delta = nums[i] - left - 1;
            if (delta >= -k && delta <= k) {
                ans++;
                left++;
            }
            else if (delta > k) {
                ans++;
                left = nums[i] - k;
            }
            // 否则delta < -k，left维持不变
        }
        return ans;
    }
}
