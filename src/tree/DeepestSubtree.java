package tree;

import common.TreeNode;

// LC 865 含所有最深节点的最小子树
public class DeepestSubtree {
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        // 比较左、右子树的深度，相等则当前
        int l = depth(root.left, 0);
        int r = depth(root.right, 0);
        if (l == r)
            return root;
        return subtreeWithAllDeepest(l >= r ? root.left : root.right);
    }
    // 当前节点，深度，最大深度
    int depth(TreeNode node, int d) {
        if (node == null) {
            return d;
        }
        return 1 + Math.max(depth(node.left, d+1), depth(node.right, d+1));
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        root.right = right;
        right.left = new TreeNode(4);
        DeepestSubtree ds = new DeepestSubtree();
        System.out.println(ds.subtreeWithAllDeepest(root).val);
    }
}
