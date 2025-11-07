package tree;

import common.TreeNode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// LC 437
public class BTPathSum {
    int ans = 0;
    List<Integer> preSum;
    Map<Integer, Integer> map;
    public int pathSum(TreeNode root, int targetSum) {
        preSum = new ArrayList<>();
        map = new HashMap<>();
        preSum.add(0, 0);
        process(root, 1, targetSum);
        return ans;
    }
    void process(TreeNode node, int level, int targetSum){
        if (node == null)
            return;
        // 先计算前缀和
        int sum = preSum.get(level - 1) + node.val;
        Integer delta = sum - targetSum;
        if (map.getOrDefault(delta, 0) != 0) {
            ans += map.get(delta);
        }
        // 给子树使用
        preSum.add(sum);
        map.put(sum, map.getOrDefault(sum, 0) + 1);
        process(node.left, level + 1, targetSum);
        process(node.right, level + 1, targetSum);
        map.put(sum, map.get(sum) - 1);
        preSum.remove(level);
    }
}
