package dp;

import java.util.Arrays;

//LC 1751
// 从左往右尝试，i~L-1会议
// 1. 参加i，则依赖最小的大于end[i]的，二分找到它，k--
// 2. 不参加i，则依赖i+1，k不变
public class MaxValueAttendEvents {
    int[][] events;
    int k;
    public int maxValue(int[][] events, int k) {
        this.events = events;
        this.k = k;
        Arrays.sort(events, (a1, a2) -> a1[0] - a2[0]); // start 小到大
        return dp();
    }

    int dp() {
        int len = events.length;
        // 从r到len-1选k个会议 最大价值
        int[][] dp = new int[k+1][len+1];
        // 只有最后一个会议可选
        for (int i=1; i<=k; i++) {
            dp[i][len-1] = events[len-1][2];
        }
        // 只能选i~last中一个会议
        for (int i=len-1; i>=0; i--) {
            dp[1][i] = Math.max(dp[1][i+1], events[i][2]);
        }
        // 选1~k个会 最多k个
        // 从第j个会开始 到最后一个，dp表示最大value
        int[] nexts = new int[len]; // 避免重复找前一个meeting
        for (int i=2; i<=k; i++) {
            for (int j=len-2; j>=0; j--) {
                // 找到可选的会议
                if (nexts[j] == 0)
                    nexts[j] = find(j);
                int x = nexts[j];
                if (x < 0) { // 没有开始更晚的
                    dp[i][j] = dp[i][j+1];
                } else {
                    dp[i][j] = Math.max(dp[i-1][x] + events[j][2], dp[i][j+1]);
                }
            }
        }
        int ans = 0;
        for (int i=1; i<=k; i++) {
            ans = Math.max(dp[i][0], ans);
        }
        return ans;
    }

    // i后 开始时间大于events[i][1]的第一个i
    int find(int i) {
        int l = i + 1;
        int r = events.length - 1;
        int curEnd = events[i][1];
        int pos = -1;
        while(l <= r) {
            int mid = l + (r - l)/2;
            if (events[mid][0] > curEnd) {
                pos = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return pos;
    }

    public static void main(String[] args) {
        final MaxValueAttendEvents mv = new MaxValueAttendEvents();
        int[][] events = new int[][] {{87,95,42},{3,42,37},{20,42,100}, {53, 84, 80}, {10, 88, 38}, {25, 80, 57}, {18, 38, 33}};
        final int i = mv.maxValue(events, 3);
        System.out.println(i);
    }
}
