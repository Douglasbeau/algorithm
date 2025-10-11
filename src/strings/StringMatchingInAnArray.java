package strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

// 找出单词字符串中所有的、是别的单词子串的单词
public class StringMatchingInAnArray {
    public List<String> stringMatching(String[] words) {
        List<String> result = new ArrayList<>();
        // 按照长度排序，单词只能是后面词的子串
        Arrays.sort(words, Comparator.comparingInt(String::length));
        String cur;
        String other;
        // 遍历每一个word，在其后找 自己是谁的子串，找到就continue
        for(int i=0; i< words.length - 1; i++) {
            cur = words[i];
            for(int j=i+1; j< words.length; j++) {
                other = words[j];
                if (other.length() == cur.length())
                    continue;
                if (other.contains(cur)) {
                    result.add(cur);
                    break;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        StringMatchingInAnArray sm = new StringMatchingInAnArray();
        List<String> strings = sm.stringMatching(new String[]{"ad", "ab", "vabcv", "ac"});
        System.out.println(strings);
    }
}
