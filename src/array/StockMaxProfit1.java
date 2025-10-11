package array;

// nums数组表示股价，买卖一次，最大收益是多少

import util.RandomArrayGenerator;

import java.util.Arrays;
import java.util.Comparator;

public class StockMaxProfit1 {
    public static void main(String[] args) {
        int[] nums = RandomArrayGenerator.generate(12, 40);

        System.out.println(Arrays.toString(nums));
        int r = calcMaxProfit(nums);
        System.out.println(r);
    }
    public static int calcMaxProfit(int[] nums) {
        int right = Integer.MIN_VALUE;
        int left = nums[0];
        int result = Integer.MIN_VALUE;
        for (int i=1; i<nums.length; i++){
            // 当num大时，右需要更新
            if (nums[i]>right) {
                right = nums[i];
                result = Math.max(result, right-left);
            }
            // 当num小时，左需要更新
            if (nums[i]<left) {
                left = nums[i];
                right = Integer.MIN_VALUE;
            }
        }
        result = result>0 ? result: -1;
        return result;
    }

}
