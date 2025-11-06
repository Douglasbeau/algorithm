package queue;

import java.util.ArrayDeque;

// LC 862. 数组元素有负数，求和>=k的最短子数组
// 关键：双端队列维护满足要求的位置
public class SumKSubArray {
    public int shortestSubarray(int[] nums, int k) {
        int n = nums.length;
        long[] sum = new long[n + 1];
        for (int i=0; i<n; i++) {
            sum[i+1] = sum[i] + nums[i];
        }
        int ans = n + 1;
        ArrayDeque<Integer> q = new ArrayDeque<>(); // 维护下标，对应sum是递增的
        q.offer(0);
        for (int i=1; i<=n; i++) {
            long cur = sum[i];
            // 先收集答案，所有前缀和够小的都是，弹出不会影响i增大后的最优解，因i增大则子数组更长
            while(!q.isEmpty() && cur - sum[q.peekFirst()] >= k) {
                ans = Math.min(ans, i - q.pollFirst());
            }
            // 再剔除比当前前缀和大的，因为后续不会使用它（意味着抛弃负数左边界）
            while(!q.isEmpty() && sum[q.peekLast()] >= cur)
                q.pollLast();
            q.offer(i);
        }
        return ans == n + 1 ? -1 : ans;
    }
}
