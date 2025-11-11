package tree;

import common.TreeNode;
import java.util.LinkedList;
import java.util.List;

// LC 94 中序遍历二叉树
public class TreeIter {
    // 法二，比下面自己想的更简洁
    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null)
            return new LinkedList<>();
        LinkedList<Integer> ans = new LinkedList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode cur = root;
        while(!stack.isEmpty() || cur != null) {
            // 优先判断左孩子，有则一直压入，直到cur=null
            if(cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            // 一旦cur为null，就可以弹出栈顶，并且将其右当成cur
            else {
                cur = stack.pop();
                ans.add(cur.val);
                cur = cur.right;
            }
        }
        return ans;
    }
    // 下面是自己的想法
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
        TreeNode t2 = new TreeNode(2);
        TreeNode root = new TreeNode(0, t1, t2);
        t1.left = new TreeNode(3);
        treeIter.inorder(root);

        System.out.println(treeIter.inorderTraversal(root));
    }
}
