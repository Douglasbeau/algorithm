package greedy;

// LC 2654 将所有数置为1的最少操作次数，每次只能选择相邻俩数，将任何一个改成二者的gcd
// 贪心，如果数组不含1，则求最短的gcd为1的子数组，其长度-1就是要得到一个1所需次数，再加n-1就是结果
public class MinOpsForOnes {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int ans = n;
        // 1.如果存在1，非1的数量就是操作数量
        for (int num : nums) {
            if (num == 1) {
                ans--;
            }
        }
        if (ans != n)
            return ans;

        boolean hasAns = false;
        // 2. 求最小子数组，使得公约数为1
        for (int i=0; i<n-1; i++) {
            int gcd = nums[i];

            for (int j=i+1; j<n; j++) {
                gcd = gcd(gcd, nums[j]);
                if (gcd == 1) {
                    ans = Math.min(ans, j - i + 1);
                    hasAns = true;
                    break;
                }
            }
            // 如果从i到终点都没有合适的子数组（gcd=1）那更小的数组更不可能
            if (gcd != 1)
                break;

            // 不可能有更好的答案了
            if (ans == 2)
                break;
        }
        if (!hasAns)
            return -1;
        // 子数组长度-1 + 剩余的
        return ans + n - 2;
    }

    // 公约数
    int gcd(int x, int y) {
        int s = Math.min(x, y);
        y = Math.max(x, y);
        while (y % s != 0) {
            int r = y % s;
            y = s;
            s = r;
        }
        return s;
    }

    public static void main(String[] args) {
        MinOpsForOnes minOpsForOnes = new MinOpsForOnes();
        int i = minOpsForOnes.minOperations(new int[]{27, 6, 36, 8});
        System.out.println(i);
    }
}
