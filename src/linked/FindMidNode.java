package linked;

// DONE 找链表的中点，针对偶数，中点有不同情况
public class FindMidNode {
    public static void main(String[] args) {
        Node node = Node.randLinkedList(8);
        System.out.println(node);
        Node midAndPre = findMidAndPre(node);
        System.out.println(midAndPre == null ? null : midAndPre.value);

        Node mn = findMidAndNext(node);
        System.out.println(mn == null ? null : mn.value);
    }

    public static Node findMidAndPre(Node head) {
        // 0或1个节点
        if (head == null || head.next == null) {
            return head;
        }
        Node s = head;
        Node fast = head;
        Node pre = null;
        while (fast != null && fast.next != null) {
            pre = s;
            s = s.next;
            fast = fast.next.next;
        }
        // 到达终点，要么fast到了最后一个节点，要么到了倒数第二个
        if (fast == null) { // event
            return pre;
        }
        // odd
        return s;
    }

    public static Node findMidAndNext(Node head) {
        // 0或1个节点
        if (head == null || head.next == null) {
            return head;
        }
        Node s = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            s = s.next;
            fast = fast.next.next;
        }
        // 到达终点，要么fast到了最后一个节点，要么到了倒数第二个
        return s;
    }
}
