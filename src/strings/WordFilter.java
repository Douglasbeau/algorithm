package strings;

// 有前后缀找单词
public class WordFilter {
    private final PrefixTree tree;
    public WordFilter(String[] words) {
        tree = new PrefixTree();
        // generate the prefix tree
        for (int i=0; i< words.length; i++) {
            String toAdd = words[i];
            toAdd = toAdd + "{" + toAdd;
            for(int j=0; j<words[i].length(); j++) {
                tree.addString(toAdd.substring(j), i);
            }
        }
    }

    public int f(String pref, String suff) {
        String toSearch = suff + "{" + pref;
        int index;
        PrefixTree cur = tree;

        for(int i=0; i<toSearch.length(); i++) {
            index = toSearch.charAt(i) - 'a';
            if (cur ==null || cur.next[index] == null) {
                return -1;
            }
            cur = cur.next[index];
        }
        return cur.index;
    }

    public static void main(String[] args) {
        String[] s = new String[] {"app", "ad", "boy"};
        WordFilter wordFilter = new WordFilter(s);
        int i = wordFilter.f("bo", "boy");
        System.out.println(i);
    }

    private static class PrefixTree {
        int index = -1;
        PrefixTree[] next;
        
        public void addString(String word, int i) {
            PrefixTree cur = this;
            char[] chars = word.toCharArray();
            // 遍历chars生成tree
            for (char aChar : chars) {
                int index = aChar - 'a';
                // 不曾有过后面的路径
                if (cur.next == null) {
                    cur.next = new PrefixTree[27];
                }
                // 不曾有过该字母
                if (cur.next[index] == null) {
                    cur.next[index] = new PrefixTree();
                }
                cur.index = i;
                cur = cur.next[index];
            }
            cur.index = i;
        }
    }
}
