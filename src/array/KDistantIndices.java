package array;

import java.util.ArrayList;
import java.util.List;

// 收集数组nums的下标i，i是所有key的k近邻下标
public class KDistantIndices {
    public List<Integer> findKDistantIndices(int[] nums, int key, int k) {
        List<Integer> ans = new ArrayList<>();

        int i = 0;
        int j = 0;
        int len = nums.length;
        while(i < len) {
            if (nums[i] != key) {
                i++;
                continue;
            }
            // 遇到k 收近邻index
            j = Math.max(i - k, j);
            int right = Math.min(i + k, len - 1);
            while(j <= right && (nums[j] != key || j == i)) {
                ans.add(j++);
            }

            if (j > len - 1) {
                break;
            }

            i = j;
        }
        return ans;
    }

    public static void main(String[] args) {
        KDistantIndices kDistantIndices = new KDistantIndices();
        int[] arr = new int[] {9,4,0,0,1,3,9};
        List<Integer> result = kDistantIndices.findKDistantIndices(arr, 9, 2);
        System.out.println(result);
    }
}