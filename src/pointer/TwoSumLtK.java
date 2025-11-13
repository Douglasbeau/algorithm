package pointer;

import java.util.Arrays;

// LC 1099 会员题 小于且最接近k的两数和
// 排序加双指针，和大移动r，和小移动l
public class TwoSumLtK {
    public int twoSumLessThanK(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);
        int sum = -1;
        int l = 0;
        int r = n - 1;
        while(l < r) {
            int s = nums[l] + nums[r];
            if (s < k) {
                sum = Math.max(sum, s);
                l++;
            } else {
                r--;
            }
            if (nums[l] > k / 2) // 剪枝，避免元素都很大时 还浪费时间
                break;
        }
        return sum;
    }
}
