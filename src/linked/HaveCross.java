package linked;

import common.ListNode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// TODO 两个链表，只知道俩头节点，怎么判断是否相交？是则返回交点
// 首先判断每个个有无环，其次俩判断相交
public class HaveCross {
    public static void main(String[] args) {
        Map<String, List<Integer>> sToL = new HashMap<>();
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        sToL.put("a", list);
        for (String i: new String[]{"a", "b", "c"}){
            sToL.computeIfAbsent(i, s -> new ArrayList<>())
                    .add(0);
        }
        sToL.values().forEach(System.out::println);

    }
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                break;
            }
        }
        if (fast == null || fast.next == null)
            return null;
        // 回到开头
        slow = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }
}
