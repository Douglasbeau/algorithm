package tree;

import java.util.LinkedList;

// TODO 非递归遍历树 中序
public class TreeIter {
    void inorder(TreeNode root) {
        if (root == null || root.left == null && root.right == null)
            return;
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.add(root);
        TreeNode cur;
        while (!stack.isEmpty()) {
            cur = stack.peekFirst();
            while(cur.left != null) {
                stack.addFirst(cur.left);
                cur = cur.left;
            }
            do {
                cur = stack.removeFirst();
                System.out.println(cur.val);
            } while (!stack.isEmpty() && cur.right == null);
            if (cur.right != null) {
                stack.addFirst(cur.right);
            }
        }
    }

    public static void main(String[] args) {
        final TreeIter treeIter = new TreeIter();
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(1);
        TreeNode root = new TreeNode(0, t1, t2);
        treeIter.inorder(root);
    }
}
