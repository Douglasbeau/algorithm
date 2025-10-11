package tree;

// 最长路径
public class LongestUnivalPath {
    int max = 0;
    public int longestUnivaluePath(TreeNode root) {
        NodeInfo result = process(root);
        return max;
    }

    private NodeInfo process(TreeNode node) {
        if (node == null) {
            return null;
        }
        // 左边、右边的信息
        NodeInfo l = process(node.left);
        NodeInfo r = process(node.right);

        NodeInfo myInfo = new NodeInfo();
        // 1. 节点值跟我一样，累加self 2. 节点值跟我不一样，统计max
        if (node.left != null)
            if (node.left.val == node.val) {
                myInfo.lenNoPass = l.lenNoPass + 1;
            }

        if (node.right != null)
            if (node.right.val == node.val) {
                myInfo.lenPass = myInfo.lenNoPass + 1 + r.lenNoPass;
                myInfo.lenNoPass = Math.max(myInfo.lenNoPass, 1 + r.lenNoPass);
            }
        max = Math.max(max, myInfo.lenPass);

        return myInfo;
    }
    static class NodeInfo{
        int lenPass; // 穿过自己
        int lenNoPass; // 不穿过自己
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(1);
        TreeNode node3 = new TreeNode(1);
        TreeNode node4 = new TreeNode(1);
        node1.left = node2;
//        node2.left = node3;
//        node2.right = node4;

        LongestUnivalPath lp = new LongestUnivalPath();
        System.out.println(lp.longestUnivaluePath(node1));
    }
}
