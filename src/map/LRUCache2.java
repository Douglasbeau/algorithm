package map;

import java.util.HashMap;
import java.util.Map;

// Node封装k、v、pre、next
public class LRUCache2 {
    private final int cap;
    private final Node head;
    private final Node tail;
    private int size;
    private final Map<Integer, Node> map;
    public LRUCache2(int cap) {
        this.cap = cap;
        this.head = new Node(); // dummy
        this.tail = new Node();
        head.next = tail;
        tail.pre = head;
        this.map = new HashMap<>();
    }
    public int get(int key) {
        if (!map.containsKey(key))
            return -1;
        Node n = map.get(key);
        moveToHead(n);
        return n.val;
    }

    public void put(int key, int val) {
        Node node;
        if (map.containsKey(key)) {
            node = map.get(key);
            node.val = val;
        } else {
            node = new Node(key, val);
            map.put(key, node);
            if (size == cap) {
                removeLast();
            } else {
                size++;
            }
        }
        moveToHead(node);
    }

    private void moveToHead(Node n) {
        if (n == head.next)
            return;
        // 已存在于链表的节点，先脱离
        if (n.next != null) {
            n.pre.next = n.next;
            n.next.pre = n.pre;
        }
        // 放head后
        Node first = head.next;
        first.pre = n;
        n.next = first;
        head.next = n;
        n.pre = head;
    }

    private void removeLast() {
        Node last = tail.pre;
        last.pre.next = tail;
        tail.pre = last.pre;
        map.remove(last.key);
    }

    private static class Node {
        int key;
        int val;
        Node pre;
        Node next;
        Node(){}
        Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
}
