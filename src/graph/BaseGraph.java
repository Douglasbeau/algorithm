package graph;

import java.util.*;

public class BaseGraph {
    Map<Integer, Node> nodes;
    Set<Edge> edges;
    BaseGraph(int[][] matrix) {
        nodes = new HashMap<>();
        edges = new HashSet<>();
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            Node n1;
            if (!nodes.containsKey(matrix[i][0])) {
                n1 = new Node(matrix[i][0]);
                nodes.put(matrix[i][0], n1);
            }
            n1 = nodes.get(matrix[i][0]);
            Node n2;
            if (!nodes.containsKey(matrix[i][1])) {
                n2 = new Node(matrix[i][1]);
                nodes.put(matrix[i][1], n2);
            }
            n2 = nodes.get(matrix[i][1]);
            n1.nexts.add(n2);
            n2.nexts.add(n1);

            Edge edge = new Edge( matrix[i][2], n1, n2);
            edges.add(edge);
            n1.edges.add(edge);
            n2.edges.add(edge);
        }
    }
}
class Node {
    Integer value;
    List<Node> nexts;
    List<Edge> edges;
    Node(Integer value) {
        this.value = value;
        this.nexts = new ArrayList<>();
        this.edges = new ArrayList<>();
    }
}
class Edge {
    int weight;
    Node from;
    Node to;
    Edge(int weight, Node from, Node to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }
    Edge(Node from, Node to) {
        this.from = from;
        this.to = to;
    }
}