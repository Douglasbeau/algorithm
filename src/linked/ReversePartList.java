package linked;

// LC 92 反转指定范围的链表
public class ReversePartList {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (left == right)
            return head;
        ListNode cur = head;
        ListNode headLast = null;
        int i = left;
        while (i > 1) {
            headLast = cur;
            cur = cur.next;
            --i;
        }
        i = left;
        ListNode pre = headLast;
        // 反转right-left次
        while(i <= right) {
            ListNode tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
            i++;
        }
        // 反转部分的头是pre，尾是headLast后的
        if (headLast != null) {
            headLast.next.next = cur;
            headLast.next = pre;
            return head;
        }
        head.next = cur;
        return pre;
    }
}
