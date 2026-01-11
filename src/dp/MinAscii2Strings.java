package dp;

// LC 712 让两个字符串相同，删除字符的最小ASCII和
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
            // 让两个尾巴操作后相同
            for (int j = n2-1; j >= 0; j--) {
                // 1. 相等，则代价为更短（去掉i和j）尾巴相等的代价
                if (s1.charAt(i) == s2.charAt(j)) {
                    dp[i][j] = dp[i+1][j+1];
                    continue;
                }
                // 2. 不相等，删任意一个 + cost(另一个尾巴vs当前少1字符的尾巴)
                dp[i][j] = Math.min(dp[i+1][j] + s1.charAt(i),
                        dp[i][j+1] + s2.charAt(j));
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
