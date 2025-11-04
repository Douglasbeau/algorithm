package queue;

import java.util.PriorityQueue;

// LC 3318
// 不简单的简单题，数据量很小而已
// 暴力解，滑动窗口+统计数组前x大值之和，复杂度O(N**2logN)
public class XSumOfKLongSA {
    public int[] findXSum(int[] nums, int k, int x) {
        int n = nums.length;
        int[] ans = new int[n - k + 1];
        int[] freqAndNum = new int[51]; // 存频率值<<7 | num值，于是可区分频率以及数值
        for (int i=0; i<51; i++) {
            freqAndNum[i] = i;
        }
        int l = 0;
        int r = 0;
        while (r < n) {
            int cur = nums[r];
            freqAndNum[cur] += 1 << 7;
            // 窗口够k+1了
            if (r >= k) {
                freqAndNum[nums[l++]] -= 1 << 7;
            }
            // 窗口够k了
            if (r >= k-1) {
                ans[r - k + 1] = xsum(freqAndNum, x);
            }
            r++;
        }
        return ans;
    }

    // 前x高频的数之和 O(N*logN)
    int xsum(int[] freqAndNum, int x) {
        int ans = 0;
        PriorityQueue<Integer> small = new PriorityQueue<>();
        for (int i=freqAndNum.length-1; i >= 0; i--) {
            int f = freqAndNum[i];
            if (small.size() == x) {
                if (small.peek() < f) {
                    small.poll();
                    small.offer(f);
                }
            } else {
                small.offer(f);
            }
        }
        while (!small.isEmpty() && x > 0) {
            int s = small.poll();
            ans += (s >> 7) * (s & 0x7F);
            x--;
        }
        return ans;
    }
}
