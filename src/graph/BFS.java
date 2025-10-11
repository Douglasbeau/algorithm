package graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

// 宽度优先遍历
public class BFS {
    public static void bfs(BaseGraph graph) {
        LinkedList<Node> q = new LinkedList<>();
        Set<Node> traversed = new HashSet<>();
        for (Node n : graph.nodes.values()) {
            if (traversed.contains(n))
                continue;
            q.add(n);
            traversed.add(n);
            Node cur;
            while (!q.isEmpty()) {
                cur = q.pollFirst();
                System.out.printf("%d,", cur.value);
                for (Node next : cur.nexts) {
                    if (traversed.contains(next))
                        continue;
                    q.add(next);
                    traversed.add(next);
                }
            }
        }
    }

    public static void main(String[] args) {
        final int[][] matrix = new int[7][];
        matrix[0] = new int[] {0, 1, 10};
        matrix[1] = new int[] {0, 2, 20};
        matrix[2] = new int[] {0, 3, 30};
        matrix[3] = new int[] {2, 3, 23};
        matrix[4] = new int[] {2, 1, 21};
        matrix[6] = new int[] {2, 4, 42};
        matrix[5] = new int[] {2, 5, 52};
        final BaseGraph baseGraph = new BaseGraph(matrix);
        bfs(baseGraph);
    }
}
