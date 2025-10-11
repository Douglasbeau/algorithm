package array;

// 一次只能从两端删除1个元素，要删除max、min的最少删除次数
public class RemoveMaxMin {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;
        int mi = 0;
        int ni = 0;
        for (int i = 1; i < n; i++) {
            int cur = nums[i];
            if (cur < nums[ni])
                ni = i;
            if (cur > nums[mi])
                mi = i;
        }
        int big = Math.max(mi, ni);
        int small = mi ^ ni ^ big;
        return Math.min(Math.min(big + 1, n - small), small + 1 + n - big);
    }
}
