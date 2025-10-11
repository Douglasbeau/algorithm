package strings;

import java.util.Arrays;
import java.util.List;

// 用字符串数组作为前缀数组，替换含前缀的单词为前缀
public class PrefixTreeReplaceWords {
    public static void main(String[] args) {
        PrefixTreeReplaceWords prefixTreeReplaceWords = new PrefixTreeReplaceWords();
        List<String> dic = Arrays.asList("do", "req", "de", "ret");
        String sentence = "does he retry the request";
        String s = prefixTreeReplaceWords.replaceWords(dic, sentence);
        System.out.println(s);
    }
    public String replaceWords(List<String> dictionary, String sentence) {
        PrefixNode root = new PrefixNode();
        for (String s : dictionary) {
            root.addString(s);
        }
        // 替换sentence
        String[] words = sentence.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<words.length; i++) {
            String prefix = root.getPrefix(words[i]);
            if (prefix != null) {
                sb.append(prefix);
            } else {
                sb.append(words[i]);
            }
            sb.append(" ");
        }

        return sb.toString().trim();
    }

    private static class PrefixNode {
        short pass;
        short end;
        PrefixNode[] nodes;
        // 将s加入前缀树
        public void addString(String s) {
            if (s == null || s.length() == 0)
                return;
            PrefixNode cur = this;
            pass++;
            for (char c : s.toCharArray()) {
                int index = c - 'a';
                if (cur.nodes == null) {
                    cur.nodes = new PrefixNode[26];
                }
                // add node
                if (cur.nodes[index] != null) {
                    cur.nodes[index].pass++;
                } else {
                    PrefixNode newNode = new PrefixNode();
                    newNode.pass = 1;
                    cur.nodes[index] = newNode;
                }
                cur = cur.nodes[index];
            }
            // 结尾的end要加一
            cur.end++;
        }

        // 获取一个字符串的前缀部分
        public String getPrefix(String toCheck) {
            if (toCheck == null)
                return null;
            PrefixNode cur = this;
            for (int i=0; i<toCheck.length(); i++) {
                if (cur == null) {
                    return null;
                }
                int index = toCheck.charAt(i) - 'a';
                // 只有当end不为零时，返回prefix
                if (cur.end != 0) {
                    return toCheck.substring(0, i);
                }
                cur = cur.nodes[index];
            }
            return null;
        }
    }
}
