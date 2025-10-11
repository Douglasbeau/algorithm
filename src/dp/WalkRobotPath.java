package dp;

// 给定机器人当前位置p，机器人能走s步，目标位置是d，机器人可在1-N共N个位置
// 输出有几种方法走到D
public class WalkRobotPath {
    // p是当前位置，s可走步数，D是目标，N限制最大位置
    public int walkMethods(int p, int s, int D, int N) {
        // 行是剩余步数，列是当前位置，dp值是走到D的方法数
//        int[][] dp = new int[s+1][N+1];
        int[] methods = new int[N+1];
        methods[D] = 1;
        // 机器人当前在i位置，当没有步数 s=0 可走时，只有在D时才有1种方法，其他都是0种方法
//        dp[0][D] = 1;
        // 当机器人可以走i步 在位置j时，i-1步j-1位置和i-1步j+1的方法数之和 => 由该位置走到目标的方法
        for (int i=1; i<=s; i++) {
            int[] next = new int[N+1];
            for (int pos=1; pos<=N; pos++) {
                if (pos == N) {
                    next[pos] = methods[N-1];
                    continue;
                }
                if (pos == 1) {
                    next[pos] = methods[2];
                    continue;
                }
                // 普遍位置
                next[pos] = methods[pos+1] + methods[pos-1];
            }
            methods = next;
        }
        return methods[p];
    }

    public static void main(String[] args) {
        WalkRobotPath walkRobotPath = new WalkRobotPath();
        int steps = 4;
        int p = 4;
        int D = 4;
        int N = 10;
        int i = walkRobotPath.walkMethods(p, steps, D, N);
        System.out.println(i);
        int ans = -1;
        System.out.println(-ans);
    }
}
