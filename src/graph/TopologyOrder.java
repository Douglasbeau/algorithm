package graph;

import java.util.*;

public class TopologyOrder {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] ans = new int[numCourses];
        int p = 0;
        Node[] nodes = new Node[numCourses];
        for (int i = 0; i < numCourses; i++) {
            nodes[i] = new Node(i);
        }
        // 建立拓扑关系
        for (int i = 0; i < prerequisites.length; i++) {
            int from = prerequisites[i][1];
            int to = prerequisites[i][0];
            Node fn = nodes[from];
            Node tn = nodes[to];
            fn.addNext(tn);
        }

        Node[] q = new Node[numCourses];
        int l = 0;
        int r = 0;
        // 收集所有in=0的节点
        for (Node n : nodes) {
            if (n.in == 0)
                q[r++] = n;
            ans[p++] = n.val;
        }
        while (r > l) {
            Node cur = q[l++];
            if (cur.nexts == null)
                continue;
            for (Node n: cur.nexts) {
                if (n.in == 0) {
                    ans[p++] = n.val;
                    q[r++] = n;
                }
            }
        }
        return p == numCourses ? ans : new int[]{};
    }
    private static class Node {
        int val;
        int in;
        List<Node> nexts;
        Node(int val) {
            this.val = val;
            this.in = 0;
        }
        void addNext(Node n) {
            if (nexts == null)
                nexts = new ArrayList<>();
            nexts.add(n);
            n.in++;
        }
    }

    public static void main(String[] args) {
        final TopologyOrder topologyOrder = new TopologyOrder();
        topologyOrder.findOrder(2, new int[][]{{0, 1}});
    }
}
