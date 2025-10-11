package array;
// index tree
public class NumArray {
    int[] sums;
    int[] nums;
    int n;
    public NumArray(int[] nums) {
        this.n = nums.length;
        this.nums = nums;
        this.sums = new int[n + 1];
        for (int i = n; i > 0; i--) {
            int num = nums[i - 1];
            int j = i;
            while (j <= n) {
                sums[j] += num;
                j += getRightOne(j);
            }
        }
    }

    public void update(int index, int val) {
        int delta = val - nums[index];
        nums[index] = val;
        int i = index + 1;
        while(i <= n) {
            sums[i] += delta;
            i += getRightOne(i);
        }
    }

    public int sumRange(int left, int right) {
        if (left == right)
            return nums[left];
        return preSum(right + 1) - preSum(left);
    }
    private int getRightOne(int i) {
        return i & (-i);
    }

    private int getLeftOne(int i) {
        int ans = 1;
        while(i != 1) {
            i >>= 1;
            ans <<= 1;
        }
        return ans;
    }

    private int preSum(int i) {
        int ans = 0;
        while(i != 0) {
            ans += sums[i];
            int rightOne = i & (-i);
            i ^= rightOne;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] a = new int[] {-28,-39,53,65,11,-56,-65,-39,-43,97};
        final NumArray numArray = new NumArray(a);
        assert numArray.sumRange(5, 6) == -121;
        numArray.update(9, 27);
        assert  numArray.sumRange(2, 3) == 118;
        assert  numArray.sumRange(6, 7) == -104;
        numArray.update(1, -82);
        numArray.update(3, -72);
        assert  numArray.sumRange(3, 7) == -221;
        assert  numArray.sumRange(1, 8) == -293;
    }
}


