package tree;

import common.TreeNode;

// LC 285会员题 BST中序遍历后继结点
// 法二key point: 利用BST特性，遇到大往左 暂记successor，遇到小往右，遇到等于则返回左
public class BSTSuccessor {
    TreeNode ans = null;
    // 法一
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        process(root, p.val);
        return ans;
    }

    void process(TreeNode node, int val) {
        if (this.ans != null)
            return;
        // 左边小，优先
        if (node.left != null)
            process(node.left, val);
        if (this.ans != null) return;
        // 其次自己
        if (node.val > val) {
            this.ans = node;
            return;
        }
        // 最后右边
        if (node.right != null)
            process(node.right, val);
    }
    public TreeNode inorderSuccessor2(TreeNode root, TreeNode p) {
        // 法二、参考题解，利用BST的特性，找到比目标数大的
        TreeNode cur = p.right;
        if (cur != null) {
            while (cur.left != null) {
                cur = cur.left;
            }
            return cur;
        }
        TreeNode successor = null;
        cur = root;
        while (cur != null) {
            if (cur.val == p.val)
                return successor;
            if (cur.val > p.val) {
                successor = cur;
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        return successor;
    }
}
