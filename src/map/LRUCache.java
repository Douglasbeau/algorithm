package map;


import java.util.HashMap;
import java.util.Map;

class LRUCache {
    Map<Integer, Integer> cache;
    Map<Integer, Node> nodeMap;
    int cap;
    Node head; // 新key放头部
    Node tail;
    public LRUCache(int capacity) {
        this.cache = new HashMap<>();
        this.cap = capacity;
        this.head = new Node(-1); // dummy node
        this.tail = this.head;
        this.nodeMap = new HashMap<>();
    }

    public int get(int key) {
        if (!cache.containsKey(key))
            return -1;
        Node node = nodeMap.get(key);
        moveToHead(node);
        return cache.get(key);
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            Node node = nodeMap.get(key);
            moveToHead(node);
        } else {
            Node node = new Node(key);
            nodeMap.put(key, node);
            headInsert(node);
        }
        cache.put(key, value);
        if (cache.size() > this.cap) {
            int toRmv = removeTail();
            nodeMap.remove(toRmv);
            cache.remove(toRmv);
        }
    }

    void headInsert(Node node) {
        if (node == head.next)
            return;
        Node oldFirst = head.next;
        this.head.next = node;
        node.pre = head;
        node.next = oldFirst;
        if (oldFirst == null) {
            tail = node;
        } else
            oldFirst.pre = node;
    }

    // only operate linkedList
    int removeTail() {
        int key = tail.key;
        Node oldTail = this.tail;
        this.tail = tail.pre;
        oldTail.pre = null;
        this.tail.next = null;
        return key;
    }

    void moveToHead(Node node) {
        if (node == head.next)
            return;
        if (node == tail) {
            removeTail();
            headInsert(node);
            return;
        }
        Node next = node.next;
        Node pre = node.pre;
        pre.next = node.next;
        next.pre = pre;

        headInsert(node);
    }

    private static class Node {
        int key;
        Node next;
        Node pre;
        Node(int key) {
            this.key = key;
        }
    }

    public static void main(String[] args) {
        final LRUCache lruCache = new LRUCache(3);
        lruCache.put(1, 1); // x
        lruCache.put(2, 2);
        lruCache.put(3, 3);
        lruCache.put(4, 4); // x 1
        System.out.println(lruCache.get(4));
        System.out.println(lruCache.get(3));
        System.out.println(lruCache.get(2)); // 2 3 4
        System.out.println(lruCache.get(1)); // -1
        lruCache.put(5, 5); // 5 2 3
        System.out.println(lruCache.get(1));
        System.out.println(lruCache.get(2));
        System.out.println(lruCache.get(3));
        System.out.println(lruCache.get(4));
        System.out.println(lruCache.get(5));
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
