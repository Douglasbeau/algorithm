package array;

import java.util.Arrays;

public class MaxOrSubArr {
    public int[] smallestSubarrays(int[] nums) {
        int n = nums.length;
        if (n == 1)
            return new int[] {1};
        int[] bitPos = new int[31]; // 每位1出现的 最左num的idx
        Arrays.fill(bitPos, -1);

        int[] ans = new int[n];
        for (int i = n-1; i >= 0; i--) {
            int cur = nums[i];
            int include = i; // 最右包含谁
            for (int j = 0; j < 31; j++) {
                if (((cur >> j) & 1) == 0) {
                    if (bitPos[j] != -1)
                        include = Math.max(bitPos[j], include);
                } else { // 当前位是1，更新bitPos
                    bitPos[j] = i;
                }
            }
            ans[i] = include - i + 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        final MaxOrSubArr maxOrSubArr = new MaxOrSubArr();
        int[] a = maxOrSubArr.smallestSubarrays(new int[]{5, 2, 3, 9, 2, 6, 1, 1, 7, 7});
        System.out.println(Arrays.toString(a));
    }
}

