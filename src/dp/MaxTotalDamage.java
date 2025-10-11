package dp;

import java.util.Arrays;

// LC 3186
// 用一个伤害值v的咒语，则与v相差1、2的咒语都不能用
public class MaxTotalDamage {
    public long maximumTotalDamage(int[] power) {
        int n = power.length;
        if (n == 1)
            return power[0];
        Arrays.sort(power);
        long[] use = new long[n]; // 使用第i个咒语 达到的最大上海
        long[] skip = new long[n]; // 不使用第i个
        use[0] = power[0];
        for (int i=1; i<n; i++) {
            int cur = power[i];
            if (cur == power[i-1]) {
                use[i] = use[i-1] + cur;
                skip[i] = skip[i-1];
                continue;
            }
            skip[i] = Math.max(skip[i-1], use[i-1]);
            // 使用当前的，累加上左边<cur-2的位置的值
            int pre = find(power, i-1, cur - 2);
            // 当前使用，pre的位置既可以使用，也可以不使用，用值较大的
            use[i] = cur + (pre < 0 ? 0 : Math.max(skip[pre], use[pre]));
        }
        return Math.max(use[n-1], skip[n-1]);
    }
    // < target的最右位置
    private int find(int[] arr, int end, int target) {
        int l = 0;
        while (l < end) {
            int mid = l + (end - l + 1) / 2;
            if (arr[mid] < target) {
                l = mid;
            } else {
                end = mid - 1;
            }
        }
        // 可能最左边也不满足<target
        return arr[l] < target ? l : -1;
    }
}
