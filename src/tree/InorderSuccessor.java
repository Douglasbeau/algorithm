package tree;

// LC 510 有父节点引用，不给root，找中序的后继
public class InorderSuccessor {
    public Node inorderSuccessor(Node node) {
        // 1. has right, then the most left children
        Node cur = node.right;
        if (node.right != null) {
            while(cur.left != null) {
                cur = cur.left;
            }
            return cur;
        }
        cur = node;
        Node p = cur.parent;
        // 2. no right, then find the parent that has the children
        while (p != null && p.left != cur) {
            cur = p;
            p = cur.parent;
        }
        return p;
    }
}
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
}
