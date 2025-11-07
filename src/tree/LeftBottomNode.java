package tree;

import common.TreeNode;
// find the most left bottom treeNode
public class LeftBottomNode {
    public int findBottomLeftValue(TreeNode root) {
        int nodeDepth = findBLNode(root).val;
        int[] nodeDepth2 = findBLNode2(root);
        assert nodeDepth == nodeDepth2[0];
        return nodeDepth2[0];
    }

    public Info findBLNode(TreeNode root) {
        // root must be non-null
        if (root.right == null && root.left == null) {
            return new Info(root.val, 0);
        }
        // 有左或右
        Info l = null;
        Info r = null;
        if (root.left != null) {
            l = findBLNode(root.left);
        }
        if (root.right != null) {
            r = findBLNode(root.right);
        }
        // 处理左右信息
        if (l != null && r != null) {
            return l.depth >= r.depth ? new Info(l.val, l.depth+1) : new Info(r.val, r.depth+1);
        }
        if (l == null) {
            return new Info(r.val, r.depth+1);
        }
        return new Info(l.val, l.depth+1);
    }
    // 用数组封装 代替Info
    public int[] findBLNode2(TreeNode root) {
        // root must be non-null
        if (root.right == null && root.left == null) {
            return new int[] {root.val, 0};
        }
        // 有左或右
        int[] l = null;
        int[] r = null;
        if (root.left != null) {
            l = findBLNode2(root.left);
        }
        if (root.right != null) {
            r = findBLNode2(root.right);
        }
        // 处理左右信息
        if (l != null && r != null) {
            return l[1] >= r[1] ? new int[]{l[0], l[1] + 1} : new int[]{r[0], r[1] + 1};
        }
        if (l == null) {
            return new int[]{r[0], r[1]+1};
        }
        return new int[]{l[0], l[1] + 1};
    }

    public static void main(String[] args) {
        LeftBottomNode leftBottomNode = new LeftBottomNode();
        TreeNode root = new TreeNode(2);
        TreeNode n1 = new TreeNode(3);
        TreeNode n2 = new TreeNode(1);
        TreeNode n3 = new TreeNode(7);
        TreeNode n4 = new TreeNode(5);
        root.left = n1;
        root.right = n2;
        n2.right = n3;
        n1.left = n4;
        int bottomLeftValue = leftBottomNode.findBottomLeftValue(root);
        System.out.println("got bottom left val : " + bottomLeftValue);
    }
}

class Info {
    int val;
    int depth;
    Info(int val, int depth) {
        this.val = val;
        this.depth = depth;
    }
}