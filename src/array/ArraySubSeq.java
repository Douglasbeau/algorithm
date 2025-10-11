package array;

import java.util.Arrays;
// number-of-subsequences-that-satisfy-the-given-sum-condition
public class ArraySubSeq {
    public int numSubseq(int[] nums, int target) {
        int ans = 0;
        // 排序不影响结果，但方便收集结果
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            // 选中nums[i]时，如果nums[j]符合条件，则其左边必符合条件
            for (int j = nums.length - 1; j >= i; j--) {
                if (nums[i] + nums[j] <= target) {
                    int add = power(j - i);
                    ans = (int)(((long)ans + add) % 1000000007);
                    break;
                }
            }
        }

        return ans;
    }

    private int power(int i) {
        int result = 1;
        while(i-- > 0) {
            result = (result << 1) % 1000000007;
        }
        return result;
    }

    public static void main(String[] args) {
        final ArraySubSeq arraySubSeq = new ArraySubSeq();
//        int a = arraySubSeq.numSubseq(new int[] {14,4,6,6,20,8,5,6,8,12,6,10,14,9,17,16,9,7,14,11,14,15,13,11,10,18,13,17,17,14,17,7,9,5,10,13,8,5,18,20,7,5,5,15,19,14},
//                22);
//        System.out.println(a);
        int f = arraySubSeq.binarySearch(new int[] {2,4,4,4}, 0 , 3, 1);
        System.out.println(f);
    }
    private int binarySearch(int[] nums, int l, int r, int n) {
        int index = -1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] <= n) {
                index = mid;
                l = mid + 1;
            } else if (nums[mid] > n) {
                r = mid - 1;
            }
        }
        return index;
    }
}
