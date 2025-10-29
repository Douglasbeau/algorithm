package array;

import java.util.Arrays;

public class TwoSumII {
    public int[] twoSum(int[] numbers, int target) {
        int n = numbers.length;
        int l = 0;
        int r = n - 1;
        while (l < r) {
            int sum = numbers[l] + numbers[r];
            if (sum == target) {
                return new int[]{l + 1, r + 1};
            }
            if (sum < target)
                l++;
            else
                r--;
        }
        return new int[]{};
    }

    public static void main(String[] args) {
        final TwoSumII twoSumII = new TwoSumII();
        final int[] ints = twoSumII.twoSum(new int[]{2, 4, 5}, 7);
        System.out.println(Arrays.toString(ints));
    }
}
