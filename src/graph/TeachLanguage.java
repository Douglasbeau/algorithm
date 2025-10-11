package graph;

import java.util.HashSet;
import java.util.Set;

public class TeachLanguage {
    public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
        int m = languages.length;
        // languages值整理为set
        Set<Integer>[] lSet = new Set[m];
        for (int i = 0; i < m; i++) {
            lSet[i] = toSet(languages[i]);
        }
        // 没有共同语言的好友
        Set<Integer> toConnect = new HashSet<>();
        for (int i = 0; i < friendships.length; i++) {
            int[] fs = friendships[i];
            Set<Integer> first = lSet[fs[0] - 1];
            Set<Integer> second = lSet[fs[1] - 1];
            boolean canTalk = hasCommon(first, second);
            if (!canTalk) {
                toConnect.add(fs[0]-1);
                toConnect.add(fs[1]-1);
            }
        }
        int ans = m;
        for (int i = 1; i <=n; i++) {
            ans = Math.min(ans, teachLanguage(toConnect, lSet, i));
        }
        return ans;
    }

    int teachLanguage(Set<Integer> toConnect, Set<Integer>[] lSet, int i) {
        int cnt = 0;
        for (Integer user : toConnect) {
            if (! lSet[user].contains(i))
                cnt++;
        }
        return cnt;
    }

    Set<Integer> toSet(int[] l) {
        Set<Integer> set = new HashSet<>();
        for (int e : l) {
            set.add(e);
        }
        return set;
    }
    boolean hasCommon(Set<Integer> f, Set<Integer> s) {
        Set<Integer> longer = f.size() >= s.size() ? f : s;
        Set<Integer> shorter = f.size() >= s.size() ? s : f;
        for (Integer i : shorter) {
            if (longer.contains(i))
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        final TeachLanguage teachLanguage = new TeachLanguage();
        final int[][] ls = {{2}, {1, 3}, {1, 2}, {3}};
        final int[][] fss = {{1, 4}, {1, 2}, {3, 4}, {2, 3}};
        final int cnt = teachLanguage.minimumTeachings(3, ls, fss);
        System.out.println(cnt);
    }
}
