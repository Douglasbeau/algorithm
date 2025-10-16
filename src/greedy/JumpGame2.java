package greedy;

// LC 45 跳跃到终点 最少次数
public class JumpGame2 {
    public int jump(int[] nums) {
        int n = nums.length;
        if (n <= 1)
            return 0;

        //贪心，目标总是 目标处下个目标更远的位置
        int i = 0;
        int ans = 0;
        while (i < n) {
            if (i == n - 1) // already last pos
                return ans;
            if (nums[i] + i >= n - 1) // can reach last pos
                return ++ans;

            int nextPos = nums[i] + i;
            i++; // 根据题意，总能跳到最后
            int max = nums[i] + i;
            for (int j = i+1; j <= nextPos; j++) {
                if (nums[j] + j >= max) {
                    i = j;
                    max = nums[j] + j;
                }
            }
            ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
        final JumpGame2 jumpGame2 = new JumpGame2();
        int[] nums = new int[]{3, 4, 1, 5, 0, 1, 1, 1};
        int a = jumpGame2.jump(nums);
        System.out.println(a);
    }
}
