package array;

// LC 2154 返回第一个找不到的数字，找到original就把它乘以2作为新的要找的
public class DoubleFoundValue {
    public int findFinalValue(int[] nums, int original) {
        int n = nums.length;
        boolean[] exist = new boolean[n];
        for (int i=0; i<n; i++) {
            if (nums[i] % original != 0)
                continue;
            int d = nums[i] / original;
            if ((d & d-1) != 0)
                continue;
            int idx = 0;
            while(d > 1) {
                d >>= 1;
                idx++;
            }
            exist[idx] = true;
        }
        int i = 0;
        for (; i<n; i++) {
            if (!exist[i])
                break;
        }
        return original * (1 << i);
    }

    public static void main(String[] args) {
        DoubleFoundValue v = new DoubleFoundValue();
        System.out.println(v.findFinalValue(new int[]{12, 6, 18, 2}, 6));;
    }
}
