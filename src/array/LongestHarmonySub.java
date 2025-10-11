package array;

import java.util.Arrays;

// 双指针找最长和谐子序列的长度 <=> 排序后最长和谐子序列长 <=> n和n+1最长长度
// 注意双指针更新的时机，长度计算
public class LongestHarmonySub {
    public int findLHS(int[] nums) {
        if(nums.length <= 1) {
            return 0;
        }
        Arrays.sort(nums);
        int ans = 0;

        int i = 0, j = 1;

        while(j < nums.length) {
            boolean h = false;
            int nextI = j;
            while (j < nums.length && nums[j] - nums[i] <= 1) {
                if(!h && nums[j] - nums[i] == 1) {
                    nextI = j;
                    h = true;
                }
                j++;
            }
            if (h) {
                ans = Math.max(ans, j - i);
            }
            i = nextI;
        }
        return ans;
    }

    public static void main(String[] args) {
        final LongestHarmonySub lhs = new LongestHarmonySub();
        final int n = lhs.findLHS(new int[]{5, 6, 6,6,6, 8, 8, 8});
        System.out.println(n);
    }
}
