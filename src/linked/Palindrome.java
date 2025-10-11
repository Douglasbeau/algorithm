package linked;

// DONE 判断单链表是否为回文
// 法一：用栈
// 法二：使后半部分逆序O(1)的复杂度 head-->mid<--tail
public class Palindrome {
    // 2025-06-28 16:57:51
    public boolean isPalindrome(Node head) {
        if (head.next == null) {
            return true;
        }
        // 找中点/后中点 s
        Node pre = null;
        Node s = head;
        Node f = head;
        while(f != null && f.next != null) {
            pre = s;
            s = s.next;
            f = f.next.next;
        }
        Node reverse = reverse(s);
        Node p1 = head;
        Node p2 = reverse;
        boolean ans = true;
        while(p2 != null) {
            if (p1.value != p2.value)
                ans = false;
            p1 = p1.next;
            p2 = p2.next;
        }

        s = reverse(reverse);
        pre.next = s;
        return ans;
    }

    private Node reverse(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node pre = null;
        Node cur = head;

        while(cur != null) {
            Node next = cur.next;
            cur.next = pre;

            pre = cur;
            cur = next;
        }
        return pre;
    }

    public static void main(String[] args) {
        final Node n1 = new Node(2);
        final Node n2 = new Node(5);
        final Node n3 = new Node(3);
        final Node n4 = new Node(5);
        final Node n5 = new Node(2);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        final Palindrome palindrome = new Palindrome();
        boolean b = palindrome.isPalindrome(n1);
        System.out.println(b);
        System.out.println(n1);
    }
}
