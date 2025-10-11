package array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
// Hard  https://leetcode.cn/problems/substring-with-concatenation-of-all-words/
public class StartsOfCombinedStrings {
    public List<Integer> findSubstring(String s, String[] words) {
        // 起始位置，最终答案的length是 s.len/words[0].length
        int wordsCnt = words.length;
        int wordLen = words[0].length();

        // get map: word -> cnt
        Map<String, Integer> wordToCnt = new HashMap<>(wordsCnt, 1);
        for (String word : words) {
            if (wordToCnt.containsKey(word)) {
                wordToCnt.put(word, wordToCnt.get(word) + 1);
            } else {
                wordToCnt.put(word, 1);
            }
        }

        List<Integer> result = new ArrayList<>();
        // loop the s, to split -> arr
        String[] fromS = new String[s.length()/wordLen];
        for (int i=0; i< fromS.length; i++) {
            fromS[i] = s.substring(i*wordLen, i*wordLen + wordLen);
        }

        // loop the fromS and find ele in map, till ele not found or access all
        int i = 0;
        while(i<fromS.length) {
            int wordCountUp = 0;
            Map<String, Integer> cp = new HashMap<>(wordToCnt);

            int head = i * wordLen;
            boolean match = false;
            boolean skip = false;
            while(wordCountUp < wordsCnt && i!= fromS.length) {
                String current = fromS[i];
                Integer cntOfCurrent = cp.get(current);
                // 找不到就下一轮，且不回退i；找到的是0则不跳
                if (!cp.containsKey(current)) {
                    skip = true;
                    i++;
                    match = false;
                    break;
                } else if (cp.get(current).equals(0)) {
                    i++;
                    match = false;
                    break;
                }
                cp.put(current, cntOfCurrent-1);
                i++;
                wordCountUp++;
                match = true;
            }

            // 匹配了就加入，回退step-1
            if (wordCountUp == wordsCnt && match) {
                result.add(head);
                i = i - wordCountUp + 1;
            // 不需要跳过则也这么回退
            } else if(!skip){
                i = i - wordCountUp + 1;
            }

            if (i == fromS.length) {
                break;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        StartsOfCombinedStrings startsOfCombinedStrings = new StartsOfCombinedStrings();
        String s;
        String[] words;
        s = "lingmindraboofooowingdingbarrwingmonkeypoundcake";
        words = new String[]{"fooo","barr","wing","ding","wing"};


        List<Integer> substring = startsOfCombinedStrings.findSubstring(s, words);
        System.out.println(substring);
    }
}
