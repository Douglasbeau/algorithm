package array;

import java.util.*;

public class SumOfThree {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        int len = nums.length;
        Set<Integer> first = new HashSet<>();
        Set<String> tri = new HashSet<>(); // 小到大
        for (int i = 0; i < len; i++) {
            int cur = nums[i];
            if (first.contains(cur))
                continue;
            first.add(cur);
            Set<Integer> set = new HashSet<>();
            // 找两数和为-cur的
            for (int j=i+1; j<len; j++) {
                int find = -cur - nums[j];
                if (set.contains(find)) {
                    String key = getkey(cur, find, nums[j]);
                    if (!tri.contains(key)) {
                        tri.add(key);
                        ans.add(Arrays.asList(cur, nums[j], find));
                    }
                }
                set.add(nums[j]);
            }
        }
        return ans;
    }

    String getkey(int a, int b, int c) {
        int tmp;
        if (a > b) {
            tmp = a;
            a = b;
            b = tmp;
        }
        if (a > c) {
            tmp = a;
            a = c;
            c = tmp;
        }
        if (b > c) {
            tmp = b;
            b = c;
            c = tmp;
        }
        return a + "," + b + "," + c;
    }

    public static void main(String[] args) {
        final SumOfThree sumOfThree = new SumOfThree();
        final List<List<Integer>> lists = sumOfThree.threeSum(new int[]{1,0,1});
        System.out.println(lists);
    }
}
