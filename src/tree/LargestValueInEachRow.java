package tree;

import java.util.*;

// 找每一层节点的最大值
public class LargestValueInEachRow {
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        // 遍历每一层，记录最大值，怎么知道谁是层最前/后一个？-- 一个flag就够了
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        TreeNode nextTail = root;
        TreeNode cur;
        int maxInCurRow = Integer.MIN_VALUE;
        while (!queue.isEmpty()) {
            cur = queue.removeFirst();

            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }

            maxInCurRow = Math.max(maxInCurRow, cur.val);

            // 本行末尾则记录下一行的末尾
            if (cur == nextTail) {
                nextTail = queue.peekLast();
                result.add(maxInCurRow);
                maxInCurRow = Integer.MIN_VALUE;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        LargestValueInEachRow largestValueInEachRow = new LargestValueInEachRow();
        TreeNode root = new TreeNode(2);

        TreeNode n1 = new TreeNode(3);
        TreeNode n2 = new TreeNode(1);

        TreeNode n3 = new TreeNode(7);
        TreeNode n4 = new TreeNode(5);
        TreeNode n5 = new TreeNode(8);

        TreeNode n6 = new TreeNode(10);
        TreeNode n7 = new TreeNode(2);
        root.left = n1;
        root.right = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n3.right = n6;
        n4.left = n7;
        List<Integer> integers = largestValueInEachRow.largestValues(root);
        System.out.println(integers);
    }
}
