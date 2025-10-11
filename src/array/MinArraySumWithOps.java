package array;

// op1表示「n/2操作的数量，op2表示-k操作的数量，仅当n>=k时，返回对数组nums执行op1（和或）op2这两种操作后，可能的最小和
// TODO 空间优化为二维
public class MinArraySumWithOps {
    private int K;
    private int[] nums;

    public int minArraySum(int[] nums, int k, int op1, int op2) {
        this.K = k;
        this.nums = nums;
        int n = nums.length;
        int[][][] dp = new int[n+1][op1+1][op2+1];
        return min(0, op1, op2, dp);
    }

    // 对index~尾，应用op1、op2后返回最小和
    private int min(int index, int op1, int op2, int[][][] dp) {
        for (int i = nums.length - 1; i >= 0; i--) {
            for (int j = 0; j <= op1; j++) {
                for (int k = 0; k <= op2; k++) {
                    int minSum = nums[i] + dp[i+1][j][k];
                    int afterOp;
                    if(j > 0) {
                        afterOp = (nums[i] + 1) >> 1;
                        minSum = Math.min(minSum, afterOp + dp[i+1][j-1][k]);
                    }
                    if (k > 0 && nums[i] >= K) {
                        afterOp = nums[i] - K;
                        minSum = Math.min(minSum, afterOp + dp[i+1][j][k-1]);
                    }
                    if (j > 0 && k > 0) {
                        if ((nums[i] + 1) >> 1 >= K) {
                            afterOp = ((nums[i] + 1) >> 1) - K;
                            minSum = Math.min(minSum, afterOp + dp[i+1][j-1][k-1]);
                        }
                        if (nums[i] >= K) {
                            afterOp = (nums[i] - K + 1) >> 1;
                            minSum = Math.min(minSum, afterOp + dp[i+1][j-1][k-1]);
                        }
                    }
                    dp[i][j][k] = minSum;
                }
            }
        }
        return dp[index][op1][op2];
    }
    public static void main(String[] args) {
        final MinArraySumWithOps ma = new MinArraySumWithOps();
        final int i = ma.minArraySum(new int[]{2, 4, 5}, 3, 2, 2);
        System.out.println(i);
    }
}
