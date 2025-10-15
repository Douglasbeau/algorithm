package array;

import java.util.List;

// 3349 判断有无2个k长度的相邻递增子数组
public class AdjacentIncreasingSA {
    // 记录位置法不如官解给的记录长度法简洁。。。
    public boolean hasIncreasingSubarrays(List<Integer> nums, int k) {
        if (k == 1)
            return true;
        int n = nums.size();
        int l = 0;
        int r = 1;
        boolean next = false;
        boolean midUp = false;
        while(r < n) {
            // 递增，
            if (nums.get(r) > nums.get(r-1)) {
                // 第一个串形成
                if (!next && r - l + 1 == k) {
                    next = true;
                    midUp = r + 1 < n && nums.get(r+1) > nums.get(r);
                    l = ++r;
                }
                // 第二个串形成
                else if (r - l + 1 == k) {
                    return true;
                }
            }
            // 不递增，若前面不够则从头来过 若够则第二个串作为第一个
            else {
                if (next && midUp) {
                    midUp = false;
                    l = r;
                } else {
                    l = r;
                    next = false;
                }
            }
            r++;
        }
        return false;
    }
}
