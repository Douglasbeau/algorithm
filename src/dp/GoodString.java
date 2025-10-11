package dp;

import java.util.Arrays;

// 构建指定长度[l,h]的字符串，每次可以拼one个1或zero个0，求有多少构建方式
// 记忆化搜索改为动态规划 DONE!
class GoodString {
    public int countGoodStrings(int low, int high, int zero, int one) {
        int[] dp = new int[high + 1];
        Arrays.fill(dp, -1);
        dp[0] = 1;
        int result = 0;
        for (int i=low; i<=high; i++) {
            result = (result + count(i, zero, one, dp)) % 1000000007;
        }
        return result;
    }
    public int count(int strLen, int zero, int one, int[] dp) {
        if (strLen < 0) {
            return 0;
        }
        if (dp[strLen] != -1) {
            return dp[strLen];
        }
        int result = (count(strLen - zero, zero, one, dp) +
                count (strLen - one, zero, one, dp)) % 1000000007;

        dp[strLen] = result;
        return result;
    }

    public int countGoodStr2(int low, int high, int zero, int one) {
        int[] dp = new int[high + 1];
        dp[0] = 1;

        int result = 0;
        int start = Math.min(zero, one); // no need consider number<0
        for(int i = start; i<=high; i++) {
            int cnt = 0;
            if (i >= zero) {
                cnt += dp[i - zero];
            }
            if (i >= one) {
                cnt = (cnt + dp[i - one]) % 1000000007;
            }
            dp[i] = cnt;
            if (i >= low && i<= high) {
                result = (result + dp[i]) % 1000000007;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        GoodString goodString = new GoodString();
        long start1 = System.currentTimeMillis();
//        int i = goodString.countGoodStrings(200, 200, 10, 1);
        int i = goodString.countGoodStrings(1, 100000, 2, 1);
//        int i = goodString.countGoodStrings(2, 3, 1, 2);
        System.out.println("time cost: " + (System.currentTimeMillis() - start1));
        System.out.println("result: " + i);

        long start2 = System.currentTimeMillis();
//        int j = goodString.countGoodStr2(200, 200, 10, 1);
                int j = goodString.countGoodStr2(1, 100000, 2, 1);
//      int j = goodString.countGoodStr2(2, 3, 1, 2);
        System.out.println("time cost: " + (System.currentTimeMillis() - start2));
        System.out.println("result: " + j);
    }
}