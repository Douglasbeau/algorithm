package array;

import java.util.Arrays;
import java.util.List;

// 3349 判断有无2个k长度的相邻递增子数组
public class AdjacentIncreasingSA {
    // 法二、记录长度代替记录下标
    public boolean hasIncreasingSubarrays(List<Integer> nums, int k) {
        if (k == 1)
            return true;
        int n = nums.size();
        int l = 0;
        int r = 1;

        for(int i = 1; i < n; i++) {
            if (nums.get(i) > nums.get(i-1)) {
                r++;
                int len = Math.max(r >> 1, Math.min(l, r));
                if (k == len)
                    return true;
            }
            // 不递增，右变成左，新的右长1
            else {
                l = r;
                r = 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        final AdjacentIncreasingSA aj = new AdjacentIncreasingSA();
        System.out.println(aj.hasIncreasingSubarrays(Arrays.asList(11,2), 1));
    }

    // 记录位置法不如官解给的记录长度法简洁。。。
    public boolean hasIncreasingSubarrays01(List<Integer> nums, int k) {
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
