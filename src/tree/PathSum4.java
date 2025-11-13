package tree;


import java.util.ArrayList;
import java.util.List;

// LC 666 会员题，每个三位数都可以表示一个节点（深度、位置、值），求所有路径值之和
public class PathSum4 {
    public int pathSum(int[] nums) {
        int n = nums.length;
        List<int[]> levels = new ArrayList<>(); // pre-sum
        // 确定父子关系
        for (int num : nums) {
            int depth = num / 100;
            int pos = num / 10 % 10;
            int v = num % 10;
            if (levels.size() < depth) {
                levels.add(new int[1 << depth - 1]);
            }
            if (depth == 1) {
                levels.get(0)[0] = v;
                continue;
            }
            int pre = levels.get(depth - 2)[pos-1 >> 1];
            int[] last = levels.get(depth - 1);
            last[pos-1] = v + pre;
        }
        int ls = levels.size();
        int ans = 0;
        for (int leaf : levels.get(ls-1)) {
            ans += leaf;
        }

        // 累加叶子节点
        for (int i = 0; i < ls - 1; i++) {
            int[] level = levels.get(i);
            int[] next = levels.get(i+1);
            for (int j = 0; j<level.length; j++) {
                int cur = level[j];
                if (cur == 0)
                    continue;
                if (next[j<<1] == 0 && next[j<<1|1] == 0)
                    ans += cur;
            }
        }
        return ans;
    }
}
