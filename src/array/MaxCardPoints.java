package array;

// LC 1423
// 一副扑克，每次只能从两头拿k张，求最大点数和
// 本质是滑动窗口
public class MaxCardPoints {
    public int maxScore(int[] cardPoints, int k) {
        // 只能拿两边的卡片，可以先拿左边k张，再循环——退手里的右，拿整个序列的右
        int n = cardPoints.length;
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += cardPoints[i];
        }
        if (k == n)
            return sum;
        int ans = sum;
        for (int i = 0; i < k; i++) {
            sum += cardPoints[n - 1 - i] - cardPoints[k - 1 - i];
            ans = Math.max(ans, sum);
        }
        return ans;
    }
}
