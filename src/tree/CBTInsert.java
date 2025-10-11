package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// 每次增加节点都返回 插入节点的父节点的值
class CBTInserter {
    private final List<TreeNode> nodes;

    public CBTInserter(TreeNode root) {
        nodes = new ArrayList<>();
        // 层次遍历加入
        addToNodes(root);
    }

    public int insert(int val) {
        int size = nodes.size();
        int parent = (size - 1) >> 1;
        TreeNode newNode = new TreeNode(val);
        nodes.add(newNode);

        TreeNode p = nodes.get(parent);
        if (size % 2 == 0) {
            p.right = newNode;
        } else {
            p.left = newNode;
        }
        return nodes.get(parent).val;
    }

    public TreeNode get_root() {
        return nodes.get(0);
    }

    private void addToNodes(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode treeNode = queue.pollFirst();
            nodes.add(treeNode);
            if (treeNode.left != null) {
                queue.offer(treeNode.left);
            }
            if (treeNode.right != null) {
                queue.offer(treeNode.right);
            }
        }
    }
}

