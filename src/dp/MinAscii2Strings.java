package dp;

public class MinAscii2Strings {
    public int minimumDeleteSum(String s1, String s2) {
        int n1 = s1.length();
        int n2 = s2.length();
        int[][] dp = new int[n1+1][n2+1]; // 开头位置 删除的最小代价
        for (int i = n2-1; i >= 0; i--) {
            dp[n1][i] = s2.charAt(i) + dp[n1][i+1];
        }
        for (int i = n1-1; i>= 0; i--) {
            dp[i][n2] = s1.charAt(i) + dp[i+1][n2];
        }
        for (int i = n1-1; i >= 0; i--) {
            for (int j = n2-1; j >= 0; j--) {
                if (s1.charAt(i) == s2.charAt(j))
                    dp[i][j] = dp[i+1][j+1];
                else
                    dp[i][j] = dp[i+1][j+1] + s1.charAt(i) + s2.charAt(j);
                // del 1
                int cost = Math.min(dp[i+1][j] + s1.charAt(i),
                        dp[i][j+1] + s2.charAt(j));
                dp[i][j] = Math.min(dp[i][j], cost);
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        final MinAscii2Strings minAscii2Strings = new MinAscii2Strings();
        final int i = minAscii2Strings.minimumDeleteSum("sea", "eat");
        System.out.println(i);
    }
}
