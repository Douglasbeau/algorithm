package divide_conquer;

import java.util.Arrays;

// LC 259 三数之和（i，j，k不同位置）<target，求三数位置有多少种可能
// 法一、利用单调性二分，并且设置flag避免冗余检查（如果选了第一个数，但没有任何结果，则整体结束；如果选了第二个数没有结果，则更新第一个数）
public class ThreeSumSmaller {
    // 法二、选一个数，双指针选另外俩，k满足条件，则k左边至j+1都可以作为第三个数
    public int threeSumSmaller2(int[] nums, int target) {
        int ans = 0;
        int n = nums.length;
        Arrays.sort(nums);
        for (int i=0; i<n-2; i++) {
            boolean done = true;
            int remainder = target - nums[i];
            int j = i + 1;
            int k = n - 1;
            while(j < k) {
                if (remainder > nums[j] + nums[k]) {
                    ans += k - j;
                    j++;
                    done = false;
                } else {
                    k--;
                }
            }
            if (done)
                break;
        }
        return ans;
    }
    public int threeSumSmaller(int[] nums, int target) {
        int ans = 0;
        int n = nums.length;
        Arrays.sort(nums);
        for (int i=0; i<n-2; i++) {
            int remainder = target - nums[i];
            boolean done = true;
            // find 2 sum < remainder
            for (int j=i+1; j<n-1; j++) {
                boolean flag = false;
                int next = remainder - nums[j];
                // binary search for < next
                int l = j + 1;
                int r = n - 1;
                while(l < r) {
                    int mid = l + (r - l + 1) / 2;
                    if (nums[mid] >= next) {
                        r = mid - 1;
                    } else {
                        l = mid;
                    }
                }
                // if the most left is >= next
                if (nums[l] < next) {
                    ans += l - j;
                    flag = true;
                    done = false;
                }
                if (!flag)
                    break;
            }
            if (done)
                break;
        }
        return ans;
    }
}
