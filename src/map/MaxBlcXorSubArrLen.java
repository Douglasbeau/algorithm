package map;

import java.util.HashMap;
import java.util.Map;

// LC 3755 周赛 最长子数组，要求1.xor为0 2.奇偶数的数量相同
// 使用map记录key: xor-blc，其中blc为 odd数量-even数量
// 再次出现才满足题意，计算len
public class MaxBlcXorSubArrLen {
    public int maxBalancedSubarray(int[] nums) {
        int n = nums.length;
        int xor = 0; // 前缀xor结果
        int blc = 0; //
        int ans = 0;
        Map<Long, Integer> map = new HashMap<>();
        map.put(0L, -1);
        for (int i=0; i<n; i++) {
            xor ^= nums[i];
            if ((nums[i] & 1) == 0) {
                --blc;
            } else {
                ++blc;
            }
            long key = ((long) xor << 32) | (blc & 0xFFFFFFFFL);;
            // 找奇偶数量相同的位置，且xor=0
            if (map.containsKey(key)) {
                ans = Math.max(i - map.get(key), ans);
            } else {
                map.put(key, i);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        MaxBlcXorSubArrLen maxBlcXorSubArrLen = new MaxBlcXorSubArrLen();
        int a = maxBlcXorSubArrLen.maxBalancedSubarray(new int[]{0, 1, 1, 2, 3, 1, 0});
        System.out.println(a);
        int b = maxBlcXorSubArrLen.maxBalancedSubarray(new int[]{1, 1, 0, 0, 2, 3, 1, 0});
        System.out.println(b);
    }
}
