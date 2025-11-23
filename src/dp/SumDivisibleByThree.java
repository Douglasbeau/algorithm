package dp;


// LC 1262 可被三整除的最大和
// 记录现有的三种sum（按%3结果分），遇到一个数，就根据其种类 更新sum
public class SumDivisibleByThree {
    public int maxSumDivThree(int[] nums) {
        int r0 = 0;
        int r1 = 0;
        int r2 = 0;// 5 1 2 2
        for (int num : nums) {
            if (num % 3 == 0) { // 1, 3 => 0,1,0 +3
                r0 += num;
                if(r1 != 0)
                    r1 += num;
                if (r2 != 0)
                    r2 += num;
            } else if (num % 3 == 1) { // 2 1
                int tmp0 = r0;
                if (r2 != 0)
                    r0 = Math.max(r0, r2 + num);
                if (r1 != 0)
                    r2 = Math.max(r2, r1 + num);
                r1 = Math.max(r1, num + tmp0);
            } else { // 2
                int tmp0 = r0;
                if (r1 != 0)
                    r0 = Math.max(r0, r1 + num);
                if (r2 != 0)
                    r1 = Math.max(r1, r2 + num);
                r2 = Math.max(r2, tmp0 + num);
            }
        }
        return r0;
    }
    public static void main(String[] args) {
        SumDivisibleByThree sumDivisibleByThree = new SumDivisibleByThree();
        System.out.println(sumDivisibleByThree.maxSumDivThree(new int[]{1, 4, 3, 5}));
    }
}
