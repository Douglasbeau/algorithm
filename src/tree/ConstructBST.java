package tree;

import common.TreeNode;
import common.ListNode;

// LC 109 有序链表构建平衡二叉搜索树，中点+递归
public class ConstructBST {
    public TreeNode sortedListToBST(ListNode head) {
        return construct(head, null);
    }

    // end is excluded
    TreeNode construct(ListNode head, ListNode end) {
        if (head == end)
            return null;
        ListNode mid = middle(head, end);
        TreeNode th = new TreeNode(mid.val);
        // link l,r
        TreeNode left = construct(head, mid);
        TreeNode right = construct(mid.next, end);
        th.left = left;
        th.right = right;
        return th;
    }

    // right middle
    ListNode middle(ListNode node, ListNode end) {
        ListNode slow = node;
        ListNode fast = node;
        while (fast != end && fast.next != end) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
