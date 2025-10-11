package queue;

import java.util.*;

public class TopKFrequentWord {
    public List<String> topKFrequent(String[] words, int k) {
        // 统计词频
        Map<String, Integer> freq = new HashMap<>();
        for (String w : words) {
            freq.put(w, freq.getOrDefault(w, 0) + 1);
        }
        // 频率低的 字典序高的在小根堆上面
        PriorityQueue<WordFreq> small = new PriorityQueue<>((w1, w2)->
                w1.f - w2.f == 0 ? w2.word.compareTo(w1.word) : w1.f - w2.f);
        List<String> ans = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : freq.entrySet()) {
            WordFreq wf = new WordFreq(entry.getValue(), entry.getKey());
            if(small.size() == k) { // 够k个
                if (wf.f > small.peek().f || wf.f == small.peek().f
                        && wf.word.compareTo(small.peek().word) < 0) {
                    small.poll();
                    small.offer(wf);
                }
            } else {
                small.offer(wf);
                ans.add(null);
            }
        }
        for (int i = k - 1; i >= 0; i--) {
            ans.set(i, small.poll().word);
        }
        return ans;
    }

    private static class WordFreq {
        int f;
        String word;
        WordFreq(int f, String word) {
            this.f = f;
            this.word = word;
        }
    }

    public static void main(String[] args) {
        final TopKFrequentWord topKFrequentWord = new TopKFrequentWord();
        final List<String> strings = topKFrequentWord.topKFrequent(new String[]{"i", "love", "leetcode", "i", "love", "coding"}, 2);
        System.out.println(strings);
    }
}
