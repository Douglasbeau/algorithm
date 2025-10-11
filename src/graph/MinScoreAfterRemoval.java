package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// LC 2322
// 无向无环连通图，切成3个连通子图，求最小的maxXor - minXor
public class MinScoreAfterRemoval {
    int ans = Integer.MAX_VALUE;
    // nums[i]为i号节点的val，edges[][0]和edges[][1]是边两端的节点编号
    public int minimumScore(int[] nums, int[][] edges) {
        // 每个节点的边集，i是节点编号，对应的set连接它的节点列表
        List<Set<Integer>> edgeList = new ArrayList<>();
        int sum = 0;
        for (int num: nums) {
            sum ^= num;
            edgeList.add(new HashSet<>());
        }

        for (int[] edge : edges) {
            edgeList.get(edge[0]).add(edge[1]);
            edgeList.get(edge[1]).add(edge[0]);
        }

        // 每个边都可以作为第一切点，以0号节点为根，由它出发遍历每个边
        dfs(0, -1, edgeList, nums, sum);
        return ans;
    }

    // 以cur出发点遍历，跳过出发点pre，删除每一个边
    int dfs(int cur, int pre, List<Set<Integer>> edgeList, int[] nums, int sum) {
        int branch = nums[cur];
        for (int v : edgeList.get(cur)) {
            if (v == pre)
                continue;
            branch ^= dfs(v, cur, edgeList, nums, sum);
        }
        for (int v : edgeList.get(cur)) {
            if (v == pre) { // 取2个连通图的1个遍历
                dfs2(v, cur, branch, cur, edgeList, nums , sum);
            }
        }
        return branch;
    }

    int dfs2(int cur, int pre, int oth, int anc, List<Set<Integer>> edgeList, int[] nums, int sum) {
        int branch = nums[cur];
        for (int v : edgeList.get(cur)) {
            if (v == pre)
                continue;
            branch ^= dfs2(v, cur, oth, anc, edgeList, nums, sum);
        }
        if (pre == anc) {
            return branch;
        }
        ans = Math.min(ans, calc(oth, branch, sum ^ oth ^ branch));
        return branch;
    }

    private int calc(int one, int two, int three) {
        return Math.max(one, Math.max(two, three)) - Math.min(Math.min(one, two), three);
    }

    public static void main(String[] args) {
        final MinScoreAfterRemoval minScoreAfterRemoval = new MinScoreAfterRemoval();
        int[] nodes = new int[] {1, 5, 5, 4, 11};
        int[][] edges = new int[][] {{0, 1}, {1, 2}, {1, 3}, {3, 4}};
        int a = minScoreAfterRemoval.minimumScore(nodes, edges);
        System.out.println(a);
    }
}
