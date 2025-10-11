package strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

// 输入一个字符串，输出 在字典中是否有与之只相差一个字符的字符串
public class MagicDictionary {
//    String[][] dic = new String[101][];
//    List<List<String>> dic = new ArrayList<>(100);
    List[] dic = new ArrayList[100];

    public MagicDictionary() {}

    public void buildDict(String[] dictionary) {
        for(String word : dictionary) {
            int pos = word.length()-1;
            if (dic[pos] == null) {
                dic[pos] = new ArrayList<>();
            }
            dic[pos].add(word);
        }
    }

    public boolean search(String searchWord) {
        int posToSearch = searchWord.length() - 1;
        if (dic[posToSearch] == null) {
            return false;
        }
        for (Object s : dic[posToSearch]) {
            if (onlyOneCharDiff((String)s, searchWord)) {
                return true;
            }
        }
        return false;
    }

    private boolean onlyOneCharDiff(String s, String searchWord) {
        int countDiff = 0;
        for(int i=0; i< s.length(); i++) {
            if (s.charAt(i) != searchWord.charAt(i)) {
                countDiff++;
            }
            if (countDiff > 1) {
                return false;
            }
        }
        return countDiff == 1;
    }

    public static void main(String[] args) {
        MagicDictionary md = new MagicDictionary();
        md.buildDict(new String[] {"hello", "hell", "ssj"});

        System.out.println(md.search("hell"));
        System.out.println(md.search("helj"));
        System.out.println(md.search("ssq"));
        System.out.println(md.search("s"));
//        md.onlyOneCharDiff("hell", "helj");
    }
}
