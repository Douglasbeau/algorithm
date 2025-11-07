//package tree;
//
//import java.util.*;
//
//// LC.1233 字典树的应用
//public class RemoveSubFolders {
//    public List<String> removeSubfolders(String[] folder) {
//        Node root = new Node();
//        for (String f : folder) {
//            Node cur = root;
//            String[] seg = f.split("/");
//            for (int i = 1; i < seg.length; i++) {
//                // 已经存在以当前结尾的
//                if (cur.exists(seg[i]) && cur.getNode(seg[i]).end)
//                    break;
//                // 不包含当前seg 则新建
//                Node newNode;
//                if ((newNode = cur.getNode(seg[i])) == null) {
//                    newNode = new Node();
//                    cur.add(seg[i], newNode);
//                }
//                // 标记结尾
//                if (i == seg.length - 1)
//                    newNode.end = true;
//                cur = newNode;
//            }
//        }
//        List<String> ans = new ArrayList<>();
//        dfs2(root, "", ans);
//        return ans;
//    }
//
//    // 法一、递归
//    void dfs(Node node, String pre, List<String> ans) {
//        if (node.end){
//            ans.add(pre);
//            return;
//        }
//        for (Map.Entry<String, Node> entry : node.nexts.entrySet()) {
//            dfs(entry.getValue(), pre + "/" + entry.getKey(), ans);
//        }
//    }
//
//    // 法二、非递归
//    void dfs2(Node node, String pre, List<String> ans) {
//        LinkedList<Pair<String, Node>> stack = new LinkedList<>();
//        Set<Node> set = new HashSet<>();
//        stack.add(Pair.of(pre, node));
//        set.add(node);
//        Pair<String, Node> cur;
//
//        while(!stack.isEmpty()) {
//            cur = stack.removeLast();
//            String tmp = pre;
//            if (pre.length() > 0)
//                pre = pre.substring(0, pre.lastIndexOf('/')); // 弹出要去掉
//            if (cur.snd.end) {
//                System.out.println(tmp);
//                ans.add(tmp);
//                continue;
//            }
//
//            for (Map.Entry<String, Node> entry : cur.snd.nexts.entrySet()) {
//                if (set.contains(entry.getValue()))
//                    continue;
//                set.add(entry.getValue());
//
//                stack.add(cur);
//                stack.add(Pair.of(entry.getKey(), entry.getValue()));
//                pre = tmp + "/" + entry.getKey();
//                break;
//            }
//        }
//    }
//
//    private static class Node {
//        boolean end;
//        Map<String, Node> nexts;
//
//        boolean exists(String s) {
//            return nexts != null && nexts.containsKey(s);
//        }
//        void add(String s, Node node) {
//            if (nexts == null)
//                nexts = new HashMap<>();
//            nexts.put(s, node);
//        }
//        Node getNode(String s) {
//            if(nexts == null)
//                return null;
//            return nexts.get(s);
//        }
//    }
//
//    public static void main(String[] args) {
//        final RemoveSubFolders removeSubFolders = new RemoveSubFolders();
//        final List<String> l = removeSubFolders.removeSubfolders(new String[]{"/a/b", "/a/b/e", "/a/c", "/a/c/d"});
//        System.out.println(l);
//    }
//}
