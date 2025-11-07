package tree;

import common.TreeNode;
import java.util.LinkedList;

// 添加一行到tree指定行
public class AddOneRowToTree {
    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        if (depth == 1) {
            TreeNode newRoot = new TreeNode(val);
            newRoot.left = root;
            return newRoot;
        }
        // 按行遍历到第depth-1层
        LinkedList<TreeNode> queue = new LinkedList<>();
        TreeNode last = root;

        queue.add(root);
        int d = 1;
        TreeNode cur;
        // 将第 depth-1 行压入队列
        while (depth > d + 1) {
            cur = queue.removeFirst();
            // 取出一个，压入其左右子节点
            if (cur.left != null) {
                queue.offer(cur.left);
            }
            if (cur.right != null) {
                queue.offer(cur.right);
            }
            // 到最右节点了
            if (cur == last){
                last = queue.peekLast();
                d++;
            }
        }
        for (TreeNode t : queue) {
            TreeNode left = new TreeNode(val);
            left.left = t.left;
            t.left = left;

            TreeNode right = new TreeNode(val);
            right.right = t.right;
            t.right = right;
        }
        return root;
    }

    // 法二：使用递归
    public TreeNode addOneRow2(TreeNode root, int val, int depth) {
        if (depth == 1) {
            TreeNode newRoot = new TreeNode(val);
            newRoot.left = root;
            return newRoot;
        }
        dfs(root, 1, val, depth);
        return root;
    }

    private void dfs(TreeNode curNode, int curDepth, int val, int depth) {
        if (depth-1 == curDepth) {
            TreeNode l = new TreeNode(val);
            l.left = curNode.left;
            curNode.left = l;
            TreeNode r = new TreeNode(val);
            r.right = curNode.right;
            curNode.right = r;
            return;
        }
        if (curNode.left != null)
            dfs(curNode.left, curDepth+1, val, depth);
        if (curNode.right != null)
            dfs(curNode.right, curDepth+1, val, depth);
    }

}
