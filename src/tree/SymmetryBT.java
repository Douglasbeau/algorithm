package tree;

import common.TreeNode;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;

// LC 101 对称二叉树
public class SymmetryBT {
    // 法一
    public boolean isSymmetric(TreeNode root) {
        if(root == null)
            return true;
        return check(root.left, root.right);
    }
    boolean check(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null)
            return true;
        if (t1 == null || t2 == null)
            return false;
        if (t1.val != t2.val)
            return false;
        return check(t1.left, t2.right) && check(t1.right, t2.left);
    }

    // 法二
    public boolean isSymmetric1(TreeNode root) {
        if(root == null || root.left == null && root.right == null)
            return true;
        return checkWithLevelOrder(root);
    }

    private boolean checkWithLevelOrder(TreeNode root) {
        LinkedList<TreeNode> q = new LinkedList<>();
        q.add(root);
        TreeNode cur;
        int size = 1;
        ArrayList<Integer> level = new ArrayList<>();
        while (!q.isEmpty()) {
            cur = q.removeFirst();
            if (cur != null) {
                q.add(cur.left);
                q.add(cur.right);
                level.add(cur.val);
            } else {
                level.add(null);
            }
            size--;
            if (size == 0) {
                for (int i = 0; i < level.size() >> 1; i++) {
                    if (!Objects.equals(level.get(i), level.get(level.size() - 1 - i)))
                        return false;
                }
                size = q.size();
                level.clear();
            }
        }
        return true;
    }


    public static void main(String[] args) {
        final SymmetryBT symmetryBT = new SymmetryBT();
        TreeNode l31 = new TreeNode(2);
        TreeNode l32 = new TreeNode(2);

        TreeNode t1 = new TreeNode(2, null, l31);
        TreeNode t2 = new TreeNode(2, l32, null);
        TreeNode root = new TreeNode(1, t1, t2);

        final boolean symmetric = symmetryBT.isSymmetric1(root);
        System.out.println(symmetric);

        System.out.println(symmetryBT.isSymmetric(root));
    }
}
