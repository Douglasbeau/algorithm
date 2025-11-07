package tree;

import common.TreeNode;
// TODO 判断二叉树是否平衡
public class IsBT {
    private static class Info{
        public boolean isBt;
        public int height;
        public Info(boolean isBt, int height) {
            this.isBt = isBt;
            this.height = height;
        }
    }
    public static boolean isBt(TreeNode n) {
        Info info = checkTree(n);
        return info.isBt;
    }

    // 法一
    public static Info checkTree(TreeNode n) {
        if (n == null) {
            return new Info(true, 0);
        }

        Info leftInfo = checkTree(n.left);
        if (!leftInfo.isBt) {
            return new Info(false, -1);
        }
        Info rightInfo = checkTree(n.right);
        if (!rightInfo.isBt) {
            return new Info(false, -1);
        }

        // l and r are balanced, check height
        int height = 1 + Math.max(leftInfo.height, rightInfo.height);
        if (Math.abs(leftInfo.height - rightInfo.height) > 1) {
            return new Info(false, height);
        }
        return new Info(true, height);
    }

    // 法二
    public static int checkTreeH(TreeNode n) {
        if (n == null) {
            return 0;
        }
        int lh = checkTreeH(n.left);
        if (lh == -1) {
            return -1;
        }
        int rh = checkTreeH(n.right);
        if (rh == -1) {
            return -1;
        }
        if (Math.abs(rh - lh) > 1) {
            return -1;
        }
        return 1 + Math.max(rh, lh);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        root.left = n1;
        n1.left = n2;

        // n2.left = n3;
        n1.right = n3;

        root.right = n4;
        n4.left = n5;

        System.out.println(isBt(root));
        System.out.println(checkTreeH(root));
    }
}
