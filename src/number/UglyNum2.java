package number;

// LC 264 丑数II 求指定序号的丑数
public class UglyNum2 {
    // 法二、考虑每个数的生成都是 (2,3,5)之一*已存在的数，不会回退，故维护三个指针，用谁，谁右移
    public int nthUglyNumber2(int n) {
        int p2 = 0;
        int p3 = 0;
        int p5 = 0;

        int[] nums = new int[n];
        nums[0] = 1;
        for (int i=1; i<n; i++) {
            int n2 = nums[p2] * 2;
            int n3 = nums[p3] * 3;
            int n5 = nums[p5] * 5;
            nums[i] = Math.min(n2, Math.min(n3, n5));

            if (nums[i] == n2) {
                ++p2;
            }
            if (nums[i] == n3) {
                ++p3;
            }
            if (nums[i] == n5) {
                ++p5;
            }
        }
        return nums[n-1];
    }
    public int nthUglyNumber(int n) {
        // 每次找跟3个数分别相乘 积大于末项的数，最小积作为新项
        // 1,
        // 1, 2
        // 1, 2, 3,
        // 1, 2, 3, 4,
        // 1, 2, 3, 4, 5
        int[] factors = new int[]{2, 3, 5};
        int[] nums = new int[n];
        nums[0] = 1;
        for (int i = 1; i < n; i++) {
            int min = Integer.MAX_VALUE;
            for (int f : factors) {
                int l = 0;
                int r = i - 1;
                while (l < r) {
                    int mid = l + (r - l) / 2;
                    if (nums[mid] * f > nums[i-1]) {
                        r = mid;
                    } else {
                        l = mid + 1;
                    }
                }
                min = Math.min(min, nums[r] * f);
                if (min == nums[i-1] + 1)
                    break;
            }
            nums[i] = min;
        }
        return nums[n-1];
    }

    public static void main(String[] args) {
        UglyNum2 uglyNum2 = new UglyNum2();

        System.out.println(uglyNum2.nthUglyNumber(8));
        System.out.println(uglyNum2.nthUglyNumber2(8));
    }
}
