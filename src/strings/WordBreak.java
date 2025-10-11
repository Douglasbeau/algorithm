package strings;

import java.util.Arrays;
import java.util.List;

// LC 139
public class WordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        boolean[] dp = new boolean[n]; // i位置的是否匹配
        int i = 0;
        while(i < s.length()) {
            for (String w : wordDict) {
                int wl = w.length();
                if (wl > n - i)
                    continue;
                // 以当前word为前缀否？是则继续
                if (w.equals(s.substring(i, i + wl))) {
                    dp[i + wl - 1] = true;
                }
            }
            while(i < n && !dp[i])
                i++;
            i++;
        }
        return dp[n-1];
    }

    public static void main(String[] args) {
        List<String> dict = Arrays.asList("cats","dog","sand","and","cat","an");
        final WordBreak wordBreak = new WordBreak();
        long start = System.currentTimeMillis();
        final boolean res = wordBreak.wordBreak("catsandogcat", dict);
        System.out.println("time cost: " + (System.currentTimeMillis() - start));
        System.out.println(Integer.MAX_VALUE + 2);

    }
}
