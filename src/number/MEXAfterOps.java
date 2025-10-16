package number;

// LC 2598
// 数组每个元素都可以加 k*value，任意次操作后的，缺失的数最大会是多少
public class MEXAfterOps {
    public int findSmallestInteger(int[] nums, int value) {
        int n = nums.length;
        if (value == 1)
            return n;
        int[] freq = new int[value]; // 余数
        for (int i = 0; i < n; i++) {
            int cur = nums[i];
            if (cur >= 0) {
                freq[cur % value]++;
            } else {
                int p = -(cur - value + 1) / value; // 往下取整
                freq[p * value + cur]++;
            }
        }

        int min = freq[0];
        int minPos = 0;
        for (int i = 1; i < value; i++) {
            if (freq[i] < min) {
                min = freq[i];
                minPos = i;
            }
        }

        // 不管value是否大于n，都可以用最小频率*value计算，因为大于时必有min=0
        return min * value + minPos;
    }
}
