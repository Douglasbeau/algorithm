package dp;

import java.util.Arrays;

public class DeleteNum {
    public int deleteAndEarn(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int[] del = new int[n];
        del[0] = nums[0];
        int[] noDel = new int[n];
        // i删or不删
        int delNum = -1;
        for (int i = 1; i < n; i++) {
            if (nums[i] == delNum + 1) { // 被连带删除
                del[i] = del[i-1];
                noDel[i] = noDel[i-1];
            } else if (nums[i] == delNum){ // 已经删过
                del[i] = del[i-1] + nums[i];
                noDel[i] = del[i-1];
            } else {
                // 删除
                del[i] = del[i-1] + nums[i];
                delNum = nums[i];

                noDel[i] = noDel[i-1];
            }
        }
        return Math.max(del[n-1], noDel[n-1]);
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 2, 3, 3, 3, 4};
        final DeleteNum deleteNum = new DeleteNum();
        final int i = deleteNum.deleteAndEarn(nums);
        System.out.println(i);
    }
}
