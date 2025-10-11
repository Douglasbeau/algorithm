package strings;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class CountUniqueChars {
    public int uniqueLetterString(String s) {
        List<Integer>[] index = new ArrayList[26];
        for (int i = 0; i < s.length(); i++) {
            int idx = s.charAt(i) - 'A';
            if (index[idx] == null) {
                index[idx] = new ArrayList<Integer>();
                index[idx].add(-1);
            }
            index[idx].add(i);
        }

        int res = 0;
        for (List<Integer> list : index) {
            if(list == null)
                continue;
            list.add(s.length());
            for (int i = 1; i < list.size() - 1; i++) {
                res += (list.get(i) - list.get(i - 1)) * (list.get(i + 1) - list.get(i));
            }
        }
        return res;
    }

    public static void main(String[] args) {
        CountUniqueChars countUniqueChars = new CountUniqueChars();
        int l = countUniqueChars.uniqueLetterString("LEETCODE");
        System.out.println(l);
    }
}
