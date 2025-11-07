package tree;

import common.TreeNode;
import java.util.ArrayList;
import java.util.List;

public class PrintBT {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode() {}
     *     TreeNode(int val) { this.val = val; }
     *     TreeNode(int val, TreeNode left, TreeNode right) {
     *         this.val = val;
     *         this.left = left;
     *         this.right = right;
     *     }
     * }
     */
    int H;

    public List<List<String>> printTree(TreeNode root) {
        int r = height(root);
        int c = (1 << r) - 1;
        H = r - 1;
        System.out.printf("r=%d, c=%d%n", r, c);
        List<List<String>> res = new ArrayList<>(r);
        for (int i = 0; i < r; i++) {
            List<String> row = new ArrayList<>(c);
            for (int j = 0; j < c; j++) {
                row.add("");
            }
            res.add(row);
        }
        fill(root, 0, (c - 1) / 2, res);
        return res;
    }

    private void fill(TreeNode node, int hCur, int col, List<List<String>> result) {
        if (node == null)
            return;
        result.get(hCur).set(col, String.valueOf(node.val));
        fill(node.left, hCur + 1, col - (1 << (H - hCur - 1)), result);
        fill(node.right, hCur + 1, col + (1 << (H - hCur - 1)), result);
    }

    private int height(TreeNode head) {
        if (head == null)
            return 0;
        int hl = height(head.left);
        int hr = height(head.right);
        return 1 + Math.max(hl, hr);
    }


    public static void main(String[] args) {
        PrintBT printBT = new PrintBT();
        TreeNode node = new TreeNode(2);
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(4);
        TreeNode node3 = new TreeNode(5);
        TreeNode node4 = new TreeNode(6);
        node.left = node1;
        node.right = node2;
//        node1.left = node3;
        node1.right = node4;
        List<List<String>> lists = printBT.printTree(node);
        lists.forEach(System.out::println);
    }
}
