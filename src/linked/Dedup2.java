package linked;

// LC 82 删除所有重复值
public class Dedup2 {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode dummy = new ListNode(111);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = head;
        // find until diff value
        while(cur != null) {
            int val = cur.val;
            do {
                cur = cur.next;
            }
            while (cur != null && cur.val == val);
            // d -> 1 -> 2
            if (pre.next.next == cur) {
                pre = pre.next;
            }
            // d -> 1 -> 1 -> 2
            else {
                pre.next = cur;
            }
        }
        return dummy.next;
    }
}
