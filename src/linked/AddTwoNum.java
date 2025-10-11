package linked;


class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
// 两数以链表形式相加
public class AddTwoNum {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode lCur = l1;
        ListNode rCur = l2;

        int digitSum;
        int digitGen;
        boolean hasAc = false;

        ListNode result = new ListNode(); // 空节点 是否可以不用空节点？？
        ListNode cur = result;

        while(lCur != null || rCur != null) {
            int lv = 0;
            int rv = 0;
            if(lCur != null){
                lv = lCur.val;
            }
            if(rCur != null) {
                rv = rCur.val;
            }
            // 计算digit sum
            digitSum = lv + rv + (hasAc ? 1 : 0);
            digitGen = digitSum % 10;
            hasAc = digitSum > 9;

            // 和作为下个节点值
            cur.next = new ListNode(digitGen);
            cur = cur.next;
            // l1, l2 的下一个
            if (lCur != null)
                lCur = lCur.next;
            if (rCur != null)
                rCur = rCur.next;
        }
        if(hasAc) {
            cur.next = new ListNode(1);
        }
        return result.next;
    }
    public ListNode addTwoNumbersWithoutEmptyNode(ListNode l1, ListNode l2) {
        ListNode lCur = l1;
        ListNode rCur = l2;

        int digitSum;
        int digitGen;
        boolean hasAc = false;

        ListNode result = null; //
        ListNode cur = null;

        while(lCur != null || rCur != null) {
            int lv = lCur == null ? 0 : lCur.val;
            int rv = rCur == null ? 0 : rCur.val;
            // 计算digit sum
            digitSum = lv + rv + (hasAc ? 1 : 0);
            digitGen = digitSum % 10;
            hasAc = digitSum > 9;
            ListNode newNode = new ListNode(digitGen);
            // 和作为下个节点值
            if (result == null) { // 只进来一次
                result = newNode;
                cur = result;
            } else {
                cur.next = newNode;
                cur = cur.next;
            }
            // l1, l2 的下一个
            if (lCur != null)
                lCur = lCur.next;
            if (rCur != null)
                rCur = rCur.next;
        }
        if(hasAc) {
            cur.next = new ListNode(1);
        }
        return result;
    }
}
