package union_find;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

// 并查集
// 关注连通性，不关注边权重
public class UnionFind<V> {
    private final Map<V, Node<V>> map = new HashMap<>();
    private final Map<Node<V>, Integer> size = new HashMap<>();
    private final Map<Node<V>, Node<V>> parents = new HashMap<>();

    public UnionFind(V[] values) {
        for (V v : values) {
            Node<V> node = new Node<>(v);
            map.put(v, node);
            size.put(node, 1);
            parents.put(node, node);
        }
    }
    public int maxGroupSize() {
        int max = 0;
        for (Integer i : size.values()) {
            max = Math.max(max, i);
        }
        return max;
    }
    public boolean sameUnion(V a, V b) {
        Node<V> nodeA = map.get(a);
        Node<V> nodeB = map.get(b);
        return findParent(nodeA) == findParent(nodeB);
    }
    //
    private Node<V> findParent(Node<V> node) {
        LinkedList<Node<V>> stack = new LinkedList<>();
        while (parents.get(node) != node) {
            stack.push(node);
            node = parents.get(node);
        }
        if (!stack.isEmpty())
            stack.pop(); // direct child
        while (!stack.isEmpty()) {
            parents.put(stack.pop(), node);
        }
        return node;
    }
    //
    public void union(V a, V b) {
        if (sameUnion(a, b)) {
            return;
        }
        Node<V> nodeA = map.get(a);
        Node<V> nodeB = map.get(b);
        Node<V> ap = findParent(nodeA);
        Node<V> bp = findParent(nodeB);
        final Integer aSize = size.get(ap);
        final Integer bSize = size.get(bp);

        Node<V> big = aSize >= bSize ? ap : bp;
        Node<V> small = aSize >= bSize ? bp : ap;
        parents.put(small, big);
        size.put(big, aSize + bSize);
        size.remove(small);
    }

    public int groupSize(V v) {
        Node<V> node;
        if ((node = map.get(v)) == null)
            return -1;
        Node<V> p = findParent(node);
        return size.get(p);
    }

    public int size() {
        return size.size();
    }

    public static class Node<V> {
        V value;
        public Node (V value) {
            this.value = value;
        }
    }
}
