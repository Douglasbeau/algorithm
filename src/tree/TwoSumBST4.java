package tree;

import common.TreeNode;

// LC 653 一个BST是否含有俩节点 其和为k
// 解法较多，中序遍历会用到双指针，BST特性可利用，但这免不了dfs。干脆在dfs时check
public class TwoSumBST4 {
    public boolean findTarget(TreeNode root, int k) {
        boolean[] exist = new boolean[20001];
        return dfs(root, exist, k);
    }

    boolean dfs(TreeNode root, boolean[] exist, int k) {
        if (root == null)
            return false;
        int idx = k - root.val + 10000;
        if (idx >=0 && idx <= 20000 && exist[idx]) // 存在遍历的节点值 = k-当前
            return true;
        exist[root.val + 10000] = true;
        return dfs(root.left, exist, k) || dfs(root.right, exist, k);
    }
}
