package greedy;

// LC 1578
// 让相邻气球不同色的最低代价
public class ColorfulRope {
    public int minCost(String colors, int[] neededTime) {
        int ans = 0;
        int n = neededTime.length;
        int pre = 0;
        char preColor = colors.charAt(0);
        for (int cur=1; cur<n; cur++) {
            // 遇到重复，可以移除cur，或者pre
            char color = colors.charAt(cur);
            if (color != preColor) {
                // 不用移除，更新pre即可
                pre = cur;
                preColor = color;
                continue;
            }

            // 移除代价低的，如果是pre，还要更新位置
            if (neededTime[pre] >= neededTime[cur]) {
                ans += neededTime[cur];
            } else {
                ans += neededTime[pre];
                pre = cur;
            }
        }
        return ans;
    }
}
