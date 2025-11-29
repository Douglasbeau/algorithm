package prefix_sum;

// LC 974 和可被k整除的子数组
public class SumDivisibleByK {
    public int subarraysDivByK(int[] nums, int k) {
        int n = nums.length;
        int ans = 0;
        int[] record = new int[k]; // 前缀和%k 余数出现的次数
        record[0] = 1;
        int sum = 0;
        for (int num : nums) {
            sum += num;
            int mod = (sum % k + k) % k;
            // 之前出现过几次，则加上几次
            int time = record[mod];
            ans += time;
            record[mod]++;
        }
        return ans;
    }

    public static void main(String[] args) {
        SumDivisibleByK sumDivisibleByK = new SumDivisibleByK();
        int i = sumDivisibleByK.subarraysDivByK(new int[]{5, 4, 1, 5}, 5);
        System.out.println(i);
    }
}
