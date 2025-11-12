package tree;

import common.TreeNode;

import java.util.ArrayList;
import java.util.List;

// LC 113 根到叶子的路径和
public class PathSum2 {
    List<List<Integer>> ans;
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        ans = new ArrayList<>();
        if (root == null)
            return ans;
        List<Integer> list = new ArrayList<>();
        process(root, targetSum, list);
        return ans;
    }
    // 往下到的节点，剩余targetSum
    void process(TreeNode node, int remainder, List<Integer> list) {
        list.add(node.val);
        int r = remainder - node.val;
        if (node.left == null && node.right == null) {
            if (r == 0) {
                ans.add(new ArrayList<>(list));
            }
            list.remove(list.size() - 1);
            return;
        }

        if (node.left != null)
            process(node.left, r, list);
        if (node.right != null)
            process(node.right, r, list);
        list.remove(list.size() - 1);
    }
}
