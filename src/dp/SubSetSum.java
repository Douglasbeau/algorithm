package dp;

public class SubSetSum {
    public boolean canPartition(int[] nums) {
        int n = nums.length;
        if (n < 2)
            return false;
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }
        if ((sum & 1) == 1)
            return false;

        int target = sum >> 1;
        boolean[] dp = new boolean[target+1]; // 能否凑够target
        boolean[] pre = new boolean[target+1];

        // 普遍位置，
        for (int num : nums) {
            for (int j = 1; j <= target; j++) {
                // 不采用自己 or 采用自己
                dp[j] = pre[j];
                if (j == num)
                    dp[j] = true;
                else if (j > num)
                    dp[j] = dp[j] || pre[j - num];
            }
            boolean[] tmp = dp;
            dp = pre;
            pre = tmp;
        }

        return dp[target];
    }

    public static void main(String[] args) {
        final SubSetSum subSetSum = new SubSetSum();
        System.out.println(subSetSum.canPartition(new int[] {1, 6, 10, 5}));
    }
}

