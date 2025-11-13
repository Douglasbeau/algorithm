package tree;

import java.util.ArrayList;
import java.util.List;

public class SerDeSerNAry {
    // Encodes a tree to a single string.
    public String serialize(Node root) {
        if (root == null)
            return null;
        if (root.children == null || root.children.isEmpty())
            return String.valueOf(root.val);
        StringBuilder sb = new StringBuilder();
        sb.append(root.val).append("(");
        for (Node c : root.children) {
            sb.append(serialize(c)).append(" ");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(")");
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if (data == null || "".equals(data))
            return null;
        int i = 0;
        while(i < data.length()) {
            if (data.charAt(i) == '(')
                break;
            i++;
        }
        int val = Integer.parseInt(data.substring(0, i));
        if (i == data.length())
            return new Node(val, new ArrayList<>());
        List<Node> nodes = collect(data, i+1).nodes;
        Node head = new Node(val, nodes);
        return head;
    }
    // return children
    Info collect(String data, int i) {
        // if (i == data.length())
        //     return new Info(i, new ArrayList<>());
        List<Node> nodes = new ArrayList<>();
        while(i < data.length() - 1) {
            char c = data.charAt(i);
            if (c == ')')
                break;
            int v = 0;
            // 一个数字
            while(c >= '0' && c <= '9') {
                v = v * 10 + c - '0';
                c = data.charAt(++i);
            }
            if (data.charAt(i) != '(') {
                nodes.add(new Node(v, new ArrayList<>()));
                if (data.charAt(i) == ' ')
                    i++;
            }
            else {
                Info info = collect(data, i + 1);
                nodes.add(new Node(v, info.nodes));
                i = info.next;
                if(i < data.length() && data.charAt(i) == ' ')
                    i++;
            }
        }
        return new Info(i + 1, nodes);
    }

    class Info {
        int next;
        List<Node> nodes;
        Info(int next, List<Node> nodes) {
            this.next = next;
            this.nodes = nodes;
        }
    }
    private static class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }
}
