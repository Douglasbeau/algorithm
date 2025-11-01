package linked;

// LC 3217 删除所有val在nums中的节点
public class RemoveNodes {
    public ListNode modifiedList(int[] nums, ListNode head) {
        int[] exist = new int[3200]; // 32bit * 3200 够 10^5
        for (int num : nums) {
            int idx = num >> 5;
            int offset = num & 0x1F;
            exist[idx] |= 1 << offset;
        }
        ListNode newHead = null;
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            int v = cur.val;
            // 要删除
            if ((exist[v>>5] & (1 << (v & 0x1F))) != 0) {
                // 删除头结点，则新头仍未定
                if (pre == null) {

                } else { // 删除非头节点
                    pre.next = cur.next;
                }
            } else if (newHead == null) { // 第一次碰到不删除的节点
                newHead = cur;
                pre = cur;
            } else{
                pre = cur;
            }
            cur = cur.next;
        }
        return newHead;
    }

    public static void main(String[] args) {
        RemoveNodes removeNodes = new RemoveNodes();
        removeNodes.modifiedList(new int[]{1}, new ListNode(1, new  ListNode(2)));
    }
}
