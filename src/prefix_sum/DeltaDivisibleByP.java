package prefix_sum;

import java.util.HashMap;
import java.util.Map;
// LC1590 删除一个子数组（不能为整个数组）后，使得和%p==0
// 思路：△=sum(数组)%p 即为sum(要删除的子数组)%p的值。先求前缀和 遍历，看是否存在?=cur-△ % p. 要删除的子数组[?,cur]
public class DeltaDivisibleByP {
    public int minSubarray(int[] nums, int p) {
        int n = nums.length;
        // 前缀和
        int[] preSum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            preSum[i] = (nums[i - 1] + preSum[i - 1]) % p;
        }
        if (preSum[n] == 0)
            return 0;
        int delta = preSum[n];
        // 删除的子数组满足 subsum % p == △
        // [6,3,5,2] 9. {0 6 0 5 7} 删掉△=7  cur-? % p == △ 则?=cur-△ % p
        Map<Integer, Integer> map = new HashMap<>(); // preSum中的位置
        map.put(0, 0);
        int ans = n;
        for (int i = 1; i <= n; i++) {
            int cur = preSum[i];
            int target = (cur - delta + p) % p;
            int pos = map.getOrDefault(target, -1);
            if (pos != -1)
                ans = Math.min(ans, i - pos);
            if (ans == 1)
                return 1;
            map.put(cur, i);
        }

        return ans == n ? -1 : ans;
    }

    public static void main(String[] args) {
        DeltaDivisibleByP d = new DeltaDivisibleByP();
        int a = d.minSubarray(new int[]{3,1,4,3,2}, 6);
        System.out.println(a);
    }
}
