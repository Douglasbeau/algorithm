package tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

// 二叉树的最大宽度，即两节点最大距离
//    o
//  o    o
//o        o
public class MaxWidthOfBT {
    public int widthOfBinaryTree(TreeNode root) {
        // use queue to traverse via BFS. Mark the leftest and rightest node with 2 numbers
        // the number of Node: 2*p for left, 2*p+1 for right, and root is 1
        LinkedList<TreeNode> queue = new LinkedList<>();
        Map<TreeNode, Integer> nodeNumMap = new HashMap<>();
        queue.add(root);
        nodeNumMap.put(root, 1);
        int l;
        int r;
        int result = 1;
        TreeNode cur;
        TreeNode right = root;
        while (!queue.isEmpty()) {
            cur = queue.removeFirst();
            // enqueue the left and right children
            if (cur.left != null) {
                queue.add(cur.left);
                nodeNumMap.put(cur.left, nodeNumMap.get(cur) << 1);
            }
            if (cur.right != null) {
                queue.add(cur.right);
                nodeNumMap.put(cur.right, (nodeNumMap.get(cur) << 1) | 1);
            }
            // when arrive at the rightest, calc the width
            if (cur == right) {
                if (queue.isEmpty())
                    break;
                right = queue.peekLast();
                r = nodeNumMap.get(right);
                l = nodeNumMap.get(queue.peekFirst());
                result = Math.max(result, r - l + 1);
            }
        }
        return result;
    }

    private static boolean haveCommonDivisor(int a, int b) { // a >= b
        int tmp;
        while(b != 0) {
            tmp = a;
            a = b; // a变成b
            b = tmp % b; // b更小
        }
        return a != 1;
    }
    public static void main(String[] args) {
        System.out.println(haveCommonDivisor(6,28));
    }
}
