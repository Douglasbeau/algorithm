package array;

import java.util.List;

// LC 3350. 检测相邻递增子数组 II
// 相邻、且长度一样2个子数组，每个最大长度
public class AdjacentIncreasing2 {
    public int maxIncreasingSubarrays(List<Integer> nums) {
        int n = nums.size();
        int up1 = 0;
        int up2 = 1;
        int ans = 1;
        int i = 1;
        while (i < n) {
            if (nums.get(i) > nums.get(i-1)) {
                up2++;
                // up, count
                ans = Math.max(Math.max(ans, up2/2), Math.min(up2, up1));
            } else {
                // not up, no need to calc
                up1 = up2;
                up2 = 1;
            }
            i++;
        }
        return ans;
    }
}
