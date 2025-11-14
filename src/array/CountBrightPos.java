package array;

// LC 2237 会员题 计算满足requirement数组的位置的数量
// 差分数组、前缀和，在(l,r+1)更新值(+1, -1)，再求前缀和
public class CountBrightPos {
    public int meetRequirement(int n, int[][] lights, int[] requirement) {
        int[] value = new int[n+1];
        // mark the lighting border, (l, r+1)
        for (int[] light: lights) {
            value[Math.max(0, light[0] - light[1])]++;
            value[Math.min(n - 1, light[0] + light[1]) + 1]--;
        }
        int ans = value[0] >= requirement[0] ? 1 : 0;
        // pre-sum and compare with requirement
        for (int i=1; i<n; i++) {
            value[i] += value[i-1];
            if (value[i] >= requirement[i])
                ans++;
        }

        return ans;
    }
}
