package tree;

// 剪掉值为零 的所有叶子
public class BinaryTreePruning {
    public TreeNode pruneTree(TreeNode root) {
        return afterPrune(root);
    }

    private TreeNode afterPrune(TreeNode treeNode) {
        // 先剪枝，再判断是否符合条件
        // 剪枝后的左树
        if (treeNode.left != null) {
            treeNode.left = afterPrune(treeNode.left);
        }
        // 剪枝后的右树
        if (treeNode.right != null) {
            treeNode.right = afterPrune(treeNode.right);
        }
        // 当自己是val=0的叶子节点时，返回
        if (treeNode.val == 0 && treeNode.left == null && treeNode.right == null) {
            return null;
        }
        return treeNode;
    }
    public int deepestLeavesSum(TreeNode root) {
        return process(root, 1);
    }

    // given node and depth, return its deepestLeavesSum
    private int process(TreeNode head, int depth) {
        // no child, then return this val and depth
        if(head.left == null && head.right == null) {
            return (head.val << 4) | depth;
        }
        int leftInfo = 0;
        int rightInfo = 0;
        if(head.left != null) {
            leftInfo = process(head.left, depth+1);
        }
        if(head.right != null) {
            rightInfo = process(head.right, depth+1);
        }
        int leftVal = leftInfo >> 4;
        int leftDepth = leftInfo & 0xF;
        int rightVal = rightInfo >> 4;
        int rightDepth = rightInfo & 0xF;
        if (leftDepth > rightDepth)
            return leftInfo;
        if (leftDepth < rightDepth)
            return rightInfo;
        return ((leftVal + rightVal) << 4) | leftDepth;
    }
    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        TreeNode n1 = new TreeNode(0);
        TreeNode n2 = new TreeNode(1);
        TreeNode n3 = new TreeNode(0);
        TreeNode n4 = new TreeNode(0);
//        root.left = n1;
//        root.right = n2;
//        n2.right = n3;
//        n1.left = n4;
        BinaryTreePruning pr = new BinaryTreePruning();
        TreeNode treeNode = pr.pruneTree(root);
        System.out.println(treeNode);
    }
}
