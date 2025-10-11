package dp;

import java.util.*;
import java.util.stream.Collectors;

// TODO LC weekly-contest-460 minimum-jumps
public class MinSkipTimes {
    public int minJumps(int[] nums) {
        int n = nums.length;
        if (n < 2)
            return 0;
        int[] dp = new int[n]; // i位置到n-1需要的最小次数
        dp[n-1] = 0;

        // 获取nums中所有质数（value为跳跃次数）以及其中合数的存在于nums中的因子
        TreeMap<Integer, Integer> primeTimes = new TreeMap<>();
        Map<Integer, Set<Integer>> composites = new HashMap<>();
        checkPrimes(nums, primeTimes, composites);

        if (primeTimes.containsKey(nums[n-1])) {
            primeTimes.put(nums[n-1], 0);
        } else if (composites.containsKey(nums[n-1])) { // 合数
            updateTimes(primeTimes, composites.get(nums[n-1]), 0);
        }

        // 难点是dp依赖左右两个位置，尝试提前给质数赋值
        for (int i = n-2; i >= 0; i--) {
            dp[i] = dp[i+1] + 1;
            final int num = nums[i];
            if (num == 1) {
                continue;
            }
            // 合数，只能为左边提供便利
            if (composites.containsKey(num)) {
                updateTimes(primeTimes, composites.get(num), dp[i]);
                continue;
            }

            // 质数，可以更新map，也可以更新dp
            int times = primeTimes.get(num);
            if (times == -1 || times > dp[i]) {
                primeTimes.put(num, dp[i]);
            }
            else if (times + 1 < dp[i]) {
                dp[i] = times + 1;
            }
        }
        return dp[0];
    }

    // 获取nums中合数的质因子集合，获取质数的map
    void checkPrimes(int[] nums, TreeMap<Integer, Integer> primesMap, Map<Integer, Set<Integer>> composites) {
        int[] copy = Arrays.copyOf(nums, nums.length);
        Arrays.sort(copy);
        for (int i = 0; i < copy.length; i++) {
            int cur = copy[i];
            if (cur == 1 || primesMap.containsKey(cur) || composites.containsKey(cur))
                continue;
            boolean is = isPrime(cur);
            if (is) {
                primesMap.put(cur, -1);
                continue;
            }
            composites.put(cur, new HashSet<>());
            for (Integer j : primesMap.keySet()) { // 用现有的因子分解当前数
                if (j * 2 > cur)
                    break;
                if (cur % j == 0) {
                    composites.get(cur).add(j);
                }
            }
        }
    }

    private boolean isPrime(int n) {
        if (n == 2)
            return true;
        for (int i = 2; i <= n / 2; i++) {
            if (n % i == 0)
                return false;
        }
        return true;
    }

    void updateTimes(Map<Integer, Integer> map, Set<Integer> factors, int times) {
        for (Integer f : factors) {
            if (map.get(f) == -1 || map.get(f) > times)
                map.put(f, times);
        }
    }

    public static void main(String[] args) {
        final MinSkipTimes minSkipTimes = new MinSkipTimes();
        final int i = minSkipTimes.minJumps(new int[]{2,2,4,6,8,1});
        System.out.println(i);
    }
}

