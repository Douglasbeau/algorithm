package tree;

import common.TreeNode;
import java.util.LinkedList;
// 法1. 非递归
// 法2. 递归 每个节点向左右要info
// 法3. 递归 每个节点告诉左右(floor, ceiling)
public class DetermineSearchTree {
    public boolean isValidBST(TreeNode root) {
        if (root.left == null && root.right == null)
            return true;
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.add(root);
        Integer curVal = null;
        Integer preVal;
        TreeNode node;
        while(!stack.isEmpty()) {
            node = stack.peekLast();
            while (node.left != null) { // 有左压入左
                stack.add(node.left);
                node = node.left;
            }
            node = stack.removeLast();
            preVal = curVal;
            curVal = node.val;
            if (preVal != null && curVal.compareTo(preVal) <= 0)
                return false;

            while(node.right == null && !stack.isEmpty()) { // 没右 一直弹出
                node = stack.removeLast();
                preVal = curVal;
                curVal = node.val;
                if (curVal.compareTo(preVal) <= 0)
                    return false;
            }
            if (node.right != null)
                stack.add(node.right); // 有右压入右
        }
        return true;
    }

    // 递归方式
    public Info isValidBST1(TreeNode root) {
        if (root.left == null && root.right == null)
            return new Info(root.val, root.val, true);

        Info leftInfo = null;
        if (root.left != null) {
            leftInfo = isValidBST1(root.left);
            if (!leftInfo.isBT)
                return new Info(0, 0, false);
        }
        Info rightInfo = null;
        if (root.right != null) {
            rightInfo = isValidBST1(root.right);
            if (!rightInfo.isBT)
                return new Info(0, 0, false);
        }

        if (leftInfo != null) {
            if (leftInfo.max >= root.val) {
                return new Info(0, 0, false);
            }
        }
        if (rightInfo != null) {
            if (rightInfo.min <= root.val) {
                return new Info(0, 0, false);
            }
        }
        return new Info(leftInfo == null ? root.val : leftInfo.min,
                rightInfo == null ? root.val : rightInfo.max,
                true);
    }
    private static class Info {
        int min;
        int max;
        boolean isBT;
        Info(int min, int max, boolean isBT) {
            this.min = min;
            this.max = max;
            this.isBT = isBT;
        }
    }

    boolean isValidBST2(TreeNode root, Integer floor, Integer ceiling) {
        if (floor != null && root.val <= floor) {
            return false;
        }
        if (ceiling != null && root.val >= ceiling) {
            return false;
        }

        boolean ans = true;
        if (root.left != null) {
            ans = isValidBST2(root.left, floor, root.val);
        }

        if (root.right != null) {
            ans &= isValidBST2(root.right, root.val, ceiling);
        }

        return ans;
    }
    public static void main(String[] args) {
        TreeNode b = new TreeNode(5);
        TreeNode d = new TreeNode(7);
        TreeNode c = new TreeNode(4, b, d);
        TreeNode root = new TreeNode(3, null, c);
        DetermineSearchTree dst = new DetermineSearchTree();
        boolean ans = dst.isValidBST(root);
        System.out.println(ans);

        System.out.println(dst.isValidBST1(root).isBT);

        System.out.println(dst.isValidBST2(root, null, null));
    }
}
