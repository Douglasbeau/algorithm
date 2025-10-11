package array;

// 利用前缀和归并，发现差值为k的个数
// TODO 用map记录遍历的值的个数，初始有(0,1) 查k-n[i]个数，累加，将n[i]
public class SubArraySumK {
    int k;
    public int subarraySum(int[] nums, int k) {
        this.k = k;
        int[] sums = new int[nums.length + 1];
        sums[0] = 0;
        for (int i = 1; i < sums.length; i++) {
            sums[i] += sums[i-1] + nums[i-1];
        }
        // 前缀和中右减去左是k的个数
        return process(sums, 0, nums.length);
    }

    int process(int[] sums, int l, int r) {
        if (l == r)
            return 0;
        int mid = l + (r - l) / 2;
        // 左边
        // 右边排列好
        return process(sums, l, mid) +
                process(sums, mid + 1, r) +
                merge(sums, l, r, mid);
    }

    private int merge(int[] sums, int l, int r, int mid) {
        int ans = 0;
        int[] help = new int[r - l + 1];
        int i = l, j = mid + 1;
        // 检查数据差值
        while (i <= mid && j <= r) {
            if (sums[i] <= sums[j] - k) {
                if (sums[i] < sums[j] - k) {
                    i++;
                    continue;
                }
                int sameLeft = 1;
                int sameRight = 1;
                i++;
                j++;
                while (i <= mid && sums[i - 1] == sums[i]) {
                    i++;
                    sameLeft++;
                }
                while (j <= r && sums[j - 1] == sums[j]) {
                    j++;
                    sameRight++;
                }
                ans += sameLeft * sameRight;
            } else {
                j++;
            }
        }
        i = l;
        j = mid + 1;

        int p = 0;
        while(i <= mid && j <= r) {
            if (sums[i] <= sums[j]) {
                help[p++] = sums[i++];
            } else {
                help[p++] = sums[j++];
            }
        }
        while (i <= mid) {
            help[p++] = sums[i++];
        }
        while (j <= r) {
            help[p++] = sums[j++];
        }

        for (i = 0; i <= r - l; i++) {
            sums[i + l] = help[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = new int[] {1, -1, 0};
        final SubArraySumK subArraySumK = new SubArraySumK();
        final int i = subArraySumK.subarraySum(arr, 0);
        System.out.println(i);
    }
}
