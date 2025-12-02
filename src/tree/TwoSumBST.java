package tree;

import common.TreeNode;

import java.util.HashSet;
import java.util.Set;

// LC 1214 会员题 两个搜索二叉树 分别选一个节点，能否满足二者之和为target
// TODO 利用BST的特性进行优化
public class TwoSumBST {

    public boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target) {
        Set<Integer> set = new HashSet<>();
        dfs(root1, set);
        return checkSum(root2, set, target);
    }
    void dfs(TreeNode root, Set<Integer> set) {
        if (root == null)
            return;
        set.add(root.val);
        dfs(root.left, set);
        dfs(root.right, set);
    }

    boolean checkSum(TreeNode root, Set<Integer> set, int target) {
        if (root == null)
            return false;
        if (set.contains(target - root.val))
            return true;
        return checkSum(root.left, set, target) || checkSum(root.right, set, target);
    }
}
