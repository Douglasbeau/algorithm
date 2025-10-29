package array;

// LC 3354 在nums选一个元素为0的位置和方向D（左右），往D找到非0数则减一，换另一个方向找非零数减一
// 问把所有数清零有多少种选择（初始位置和方向）方案
// 思路：左右累加和相同，则两种方案，相差一则一种方案。
public class DecrementLeftRightNums {
    public int countValidSelections(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int num: nums) {
            sum += num;
        }
        int ans = 0;
        int presum = 0;
        int half = sum >> 1;
        // 找presum为half出现的位置 且nums[i]为0
        for (int i=0; i<n; i++) {
            if (nums[i] != 0) {
                presum += nums[i];
                continue;
            }
            if ((sum & 1) == 0 && presum == half) {
                ans += 2;
                continue;
            }
            if ((sum & 1) == 1 && (presum == half + 1 || presum == half)) {
                ans++;
            }
        }
        return ans;
    }
}
