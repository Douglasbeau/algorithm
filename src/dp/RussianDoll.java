package dp;

import java.util.Arrays;

public class RussianDoll {
    public int maxEnvelopes(int[][] envelopes) {
        int n = envelopes.length;
        Arrays.sort(envelopes, (a1, a2) -> a1[0] - a2[0] == 0 ?
                a2[1] - a1[1] : a1[0] - a2[0]);
        int[] dp = new int[n];
        int[] arr = new int[n + 1]; // 子序列长度对应的最小值
        int max = 0;
        for (int i = 0; i < n; i++) {
            int curH = envelopes[i][1];
            // 与前面元素比较 找最大的小于curH的值形成的最长序列
            int pos = prePos(arr, max, curH);
            dp[i] = 1 + pos;
            max = Math.max(max, dp[i]);
            // 如果更小 则更新arr数值
            if (arr[dp[i]] == 0 || arr[dp[i]] > curH)
                arr[dp[i]] = curH;
        }

        return max;
    }
    // 找到小于当前位置数的 最右位置 fertility
    private int prePos(int[] arr, int r, int target) {
        int l = 0;
        int pos = 0;
        while(l <= r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] >= target) {
                r = mid - 1;
            } else {
                pos = mid;
                l = mid + 1;
            }
        }
        return pos;
    }

    public static void main(String[] args) {
        final RussianDoll russianDoll = new RussianDoll();
        int[][] el = new int[][] {{1,15},{7,18},{7,6},{7,100},{2,200},{17,30},{17,45},{3,5},{7,8},{3,6},{3,10},{7,20},{17,3},{17,45}};
        final int i = russianDoll.maxEnvelopes(el);
        System.out.println(i);
    }
}
