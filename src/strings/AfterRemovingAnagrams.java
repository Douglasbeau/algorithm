package strings;

import java.util.ArrayList;
import java.util.List;

// LC.2273 删除字母异位词后的结果
public class AfterRemovingAnagrams {
    public List<String> removeAnagrams(String[] words) {
        int n = words.length;
        List<String> ans = new ArrayList<>();
        int[] preFreq = null;
        for (int i=0; i<n; i++) {
            String cur = words[i];
            int[] freq = extract(cur);
            // 根据词频判断异位词，是则不要
            if (same(preFreq, freq))
                continue;
            // 与前面不同
            ans.add(cur);
            preFreq = freq;
        }
        return ans;
    }

    boolean same(int[] f1, int[] f2) {
        if (f1 == null)
            return false;
        for (int i=0; i<26;i++) {
            if (f1[i] != f2[i])
                return false;
        }
        return true;
    }

    int[] extract(String s) {
        int[] f = new int[26];
        for (char c : s.toCharArray()) {
            f[c-'a']++;
        }
        return f;
    }
}
