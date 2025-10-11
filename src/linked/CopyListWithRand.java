package linked;

// DONE 克隆Node中有额外指针的单链表
// 法一：直接用map克隆节点，额外空间复杂度高 —— 笔试
// 法二：1 - 1' - 2 - 2'... 再分离 —— 面试
public class CopyListWithRand {
    static class NodeRand{
        public int value;
        public NodeRand rand;
        public NodeRand next;
        NodeRand (int value) {
            this.value = value;
        }

        static void printRandNodes(NodeRand node) {
            if (node == null || node.next == null) {
                System.out.println(node);
                return;
            }
            NodeRand cur = node;
            while (cur.next != null) {
                System.out.print(cur.value + "->");
                cur = cur.next;
            }
            System.out.println(cur.value);
            cur = node;
            while (cur != null) {
                if (cur.rand != null)
                    System.out.printf("%d -> %d\t", cur.value, cur.rand.value);
                cur = cur.next;
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        NodeRand a = new NodeRand(1);
        NodeRand b = new NodeRand(2);
        NodeRand c = new NodeRand(3);
        NodeRand d = new NodeRand(4);
        a.next = b;
        b.next = c;
        c.next = d;

        c.rand = b;
        a.rand = d;
        d.rand = a;
        NodeRand.printRandNodes(a);
        NodeRand copy = cloneList(a);
        NodeRand.printRandNodes(copy);
    }

    // a -> b -> c -> d
    // a a' b b' c c' 当前节点的复制节点的rand -> 其rand的复制（next）节点
    public static NodeRand cloneList(NodeRand head){
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            NodeRand copy = new NodeRand(head.value);
            if (head.rand == head) {
                copy.rand = copy;
            }
            return copy;
        }
        // 1. copy to insert at the next
        NodeRand cur = head;
        while (cur != null) {
            NodeRand copy = new NodeRand(cur.value);
            NodeRand next = cur.next;
            cur.next = copy;
            copy.next = next;
            cur = cur.next.next;
        }
        // 2. set rand
        cur = head;
        while (cur != null) {
            NodeRand copy = cur.next;
            if (cur.rand != null)
                copy.rand = cur.rand.next;
            cur = cur.next.next;
        }
        // 3. split
        cur = head;
        NodeRand result = cur.next;
        while (true) {
            NodeRand copy = cur.next;
            cur.next = copy.next;
            if (cur.next == null)
                break;
            copy.next = cur.next.next;
            cur = cur.next;
        }
        return result;
    }
}
