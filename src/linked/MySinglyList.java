package linked;

// LC 707 设计链表
public class MySinglyList {
    Node head;
    Node tail;
    int size;

    public MySinglyList() {
        head = new Node(); // dummy
        tail = head;
    }

    public int get(int index) {
        if (index >= size)
            return -1;
        Node cur = head.next;
        while (index-- != 0) {
            cur = cur.next;
        }
        return cur.val;
    }

    public void addAtHead(int val) {
        Node first = new Node(val);
        first.next = head.next;
        head.next = first;
        if (size == 0)
            tail = first;
        size++;
    }

    public void addAtTail(int val) {
        tail.next = new Node(val);
        tail = tail.next;
        size++;
    }

    public void addAtIndex(int index, int val) {
        if (index > size)
            return;
        if (index == size) {
            addAtTail(val);
            return;
        }
        Node pre = head;
        Node insert = new Node(val);
        while(index-- != 0) {
            pre = pre.next;
        }
        insert.next = pre.next;
        pre.next = insert;
        size++;
    }

    public void deleteAtIndex(int index) {
        if (index >= size)
            return;
        Node pre = head;
        while (index-- != 0) {
            pre = pre.next;
        }
        if (pre.next == tail)
            tail = pre;
        pre.next = pre.next.next;
        size--;
    }

    private static class Node{
        int val;
        Node next;
        Node(int val) {
            this.val = val;
        }
        Node(){}
    }
}
