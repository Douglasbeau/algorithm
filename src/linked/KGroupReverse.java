package linked;

public class KGroupReverse {
    public ListNode reverseKGroup(ListNode head, int k) {
        if(k == 1 || head.next == null)
            return head;
        ListNode p = head;
        ListNode newHead = null;
        ListNode preTail = null;
        while(p != null) {
            ListNode groupTail = p; // 旋转后
            int i = k;
            while (i != 0 && p != null) {
                p = p.next;
                i--;
            }
            if (i != 0) {
                preTail.next = groupTail;
                break;
            }
            ListNode rev = reverse(groupTail, p);
            if (newHead == null) {
                newHead = rev;
            }
            if (preTail != null) {
                preTail.next = rev;
            }
            preTail = groupTail;
        }
        return newHead;
    }
    ListNode reverse(ListNode head, ListNode end) {
        ListNode cur = head;
        ListNode pre = null;
        while (cur != end) {
            ListNode tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        return pre;
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        final KGroupReverse kGroupReverse = new KGroupReverse();
        final ListNode listNode = kGroupReverse.reverseKGroup(n1, 2);
        ListNode p = listNode;
        while (p != null) {
            System.out.printf("%d,", p.val);
            p = p.next;
        }
        System.out.println();
    }
}
