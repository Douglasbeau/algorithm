package tree;

public class WrongSearchTree {
    TreeNode[] nodes = new TreeNode[4];
    int p = 0;
    public void recoverTree(TreeNode root) {
        traverse(root, null);
        if (p == 2) {
            int tmp = nodes[0].val;
            nodes[0].val = nodes[1].val;
            nodes[1].val = tmp;
        } else { // p == 4
            int tmp = nodes[0].val;
            nodes[0].val = nodes[3].val;
            nodes[3].val = tmp;
        }
    }
    // 中序遍历，提供前驱，返回末尾节点
    TreeNode traverse(TreeNode root, TreeNode pre) {
        if (root == null) {
            return null;
        }
        TreeNode leftLast = traverse(root.left, pre);
        if (leftLast == null) {
            if (pre != null && pre.val > root.val) {
                nodes[p++] = pre;
                nodes[p++] = root;
            }
        } else if (leftLast.val > root.val) {
            nodes[p++] = leftLast;
            nodes[p++] = root;
        }
        TreeNode right = traverse(root.right, root);

        return right == null ? root : right;
    }

    public static void main(String[] args) {
        TreeNode left = new TreeNode(3, null, new TreeNode(2));
        TreeNode root = new TreeNode(1, left, null);
        final WrongSearchTree wrongSearchTree = new WrongSearchTree();
        wrongSearchTree.recoverTree(root);
    }
}
