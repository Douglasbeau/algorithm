package array;

public class AngryBoss {
    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        int n = customers.length;
        int[] presum = new int[n+1]; // 不满意的
        int origin = 0; // 原本满意的
        for (int i = 0; i < n; i++) {
            if (grumpy[i] == 0) {
                presum[i+1] = presum[i] + customers[i];
            } else {
                origin += customers[i];
                presum[i+1] = presum[i];
            }
        }
        int delta = 0;
        // 窗口右边界
        for (int i = minutes; i <= n; i++) {
            delta = Math.max(delta, presum[i] - presum[i - minutes]);
        }
        return delta + origin;
    }

    public static void main(String[] args) {
        final AngryBoss angryBoss = new AngryBoss();
        final int i = angryBoss.maxSatisfied(new int[]{1, 0, 1, 2, 1, 1, 7, 5}, new int[]{0, 1, 0, 1, 0, 1, 0, 1}, 3);
        System.out.println(i);
    }
}
