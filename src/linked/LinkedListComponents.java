package linked;

import common.ListNode;// LC 817 链表组件个数
public class LinkedListComponents {
    public int numComponents(ListNode head, int[] nums) {
        int[] pos = new int[10001];
        for (int i = 0; i < nums.length; i++) {
            pos[nums[i]] = i + 1;
        }
        ListNode h = head;
        int ans = 0;
        boolean found = false;
        while (h != null) {
            // 没发现就继续
            if (pos[h.val] == 0) {
                found = false;
                h = h.next;
                continue;
            }
            // 第一次发现就记录
            if (!found) {
                found = true;
                ans++;
            }
            // 连续发现则只看下一个
            h = h.next;
        }
        return ans;
    }
}
