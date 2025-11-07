package tree;

import common.TreeNode;
import java.util.ArrayDeque;
import java.util.Deque;

public class MidOrderSuccessor {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        int target = p.val;
        TreeNode pre = null;
        while(!stack.isEmpty()) {
            TreeNode cur = stack.peek();
            // push all left child
            while(cur.left != null) {
                cur = cur.left;
                stack.push(cur);
            }
            // the most left
            cur = stack.pop();
            if (pre != null && pre.val == target)
                return cur;
            // the popped is pre, cur will be assigned later
            pre = cur;
            while(!stack.isEmpty() && cur.right == null) {
                cur = stack.pop();
                if (pre.val == target)
                    return cur;
                pre = cur;
            }
            if (cur.right != null) {
                stack.push(cur.right);
            }
        }
        return null;
    }

    public static void main(String[] args) {
        final TreeNode root = new TreeNode(2);
        final TreeNode l = new TreeNode(1);
        final TreeNode r = new TreeNode(3);
        root.left = l;
        root.right = r;
        final MidOrderSuccessor midOrderSuccessor = new MidOrderSuccessor();
        final TreeNode res = midOrderSuccessor.inorderSuccessor(root, r);
        System.out.println(res == null ? null : res.val);
    }
}
