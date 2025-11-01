package pointer;

// LC 42 接雨水
// 另外还有动态规划版本（双指针的复杂版），求每个位置左右边的max，放到两个数组中
public class TrapRainWater {
    // 法二、双指针垂直统计 左、右max的低者是短板，移动该侧指针
    public int trap(int[] height) {
        int n = height.length;
        if (n < 3)
            return 0;
        int ans = 0;
        int l = 0;
        int r = n - 1;
        int lMax = height[0];
        int rMax = height[n-1];
        // l,r移动，变高则更新lMax、rMax，变低则能得到高度差?Max - height[?]
        while(l < r) {
            if (lMax <= rMax) {
                l++;
                ans += lMax > height[l] ? lMax - height[l] : 0;
                lMax = Math.max(lMax, height[l]);
                continue;
            }
            r--;
            ans += rMax > height[r] ? rMax - height[r] : 0;
            rMax = Math.max(rMax, height[r]);
        }
        return ans;
    }
    // 法一、单调栈 水平统计
    // public int trap(int[] height) {
    //     int n = height.length;
    //     int[] stack = new int[n];
    //     int si = -1;
    //     int ans = 0;
    //     // 找高于i位置的
    //     for (int i=0; i<n; i++) {
    //         while(si > -1 && height[stack[si]] <= height[i]) {
    //             int pop = stack[si--];
    //             // 注意：stack里存的是下标！
    //             int edge = Math.min(si < 0 ? 0 : height[stack[si]], height[i]);
    //             if (edge > height[pop])
    //                 ans += (edge - height[pop]) * (i - (si < 0 ? -1 : stack[si]) - 1);
    //         }
    //         stack[++si] = i;
    //     }

    //     return ans;
    // }
}
