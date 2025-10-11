package linked;

import java.util.Random;

public class Node {
    private static final Random rand = new Random(43);

    public int value;
    public Node next;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node cur = this;
        while (cur.next != null) {
            sb.append(cur.value).append("->");
            cur = cur.next;
        }
        sb.append(cur.value);
        return sb.toString();
    }

    public static Node randLinkedList(int num) {
        if (num == 0)
            return null;
        Node head = new Node(rand.nextInt(3*num));
        Node cur = head;
        for (int i=0; i<num-1; i++) {
            Node n = new Node(rand.nextInt(3*num));
            cur.next = n;
            cur = n;
        }
        return head;
    }
    public static void main(String[] args) {
        //
        Node a = new Node(1);
        Node b = new Node(3);
        Node c = new Node(4);
        Node d = new Node(6);

        a.next = b;
        b.next = c;
        c.next = d;
        System.out.println(a);
    }
}
