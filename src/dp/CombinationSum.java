package dp;

import java.util.Arrays;

public class CombinationSum {
    public int combinationSum4(int[] nums, int target) {
        int n = nums.length;
        Arrays.sort(nums);
        int[][] dp = new int[n+1][target+1]; // 从i位置到最后 能凑成j的方法数
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }
        for (int i = n-1; i >= 0; i--) {
            for (int j = 1; j <= target; j++) {
                // 消除m个nums[i]，
                for (int m = 0; m * nums[i] <= j; m++)
                    dp[i][j] += dp[i+1][j - m * nums[i]];
            }
        }
        return dp[0][target];
    }

    public static void main(String[] args) {
        final CombinationSum combinationSum = new CombinationSum();
        int[] nums = new int[] {1, 2, 3};
        combinationSum.combinationSum4(nums, 4);
    }
}
