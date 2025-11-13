package tree;

import common.TreeNode;

import java.util.ArrayList;
import java.util.List;

// LC 431 会员题 多叉树<->二叉树 互相转化
public class NAryToBT {
    // Definition for a Node.
    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    class Codec {
        // Encodes an n-ary tree to a binary tree.
        public TreeNode encode(Node root) {
            if (root == null)
                return null;
            // 长子在左，其余挂长子下
            // 连接左右孩子之前，递归将其序列化
            TreeNode head = new TreeNode(root.val);
            if (root.children.isEmpty())
                return head;
            TreeNode other = null;
            List<Node> children = root.children;

            for (Node child : children) {
                if (head.left == null) {
                    head.left = encode(child);
                    other = head.left;
                } else {
                    other.right = encode(child);
                    other = other.right;
                }
            }
            return head;
        }

        // Decodes your binary tree to an n-ary tree.
        public Node decode(TreeNode root) {
            if (root == null)
                return null;
            List<Node> list = new ArrayList<>();
            Node head = new Node(root.val, list);
            if (root.left == null)
                return head;
            // 收集左子及其右边，故上面判断说明无左子则无children，右子都是父直接管理
            TreeNode node = root.left;
            while (node != null) {
                list.add(decode(node));
                node = node.right;
            }
            return head;
        }
    }

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(root));
}
