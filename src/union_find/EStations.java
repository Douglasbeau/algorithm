package union_find;

import java.util.*;

// TODO 并查集 支持删除 支持查最小值
public class EStations {    private static class EStation{
    boolean active = true;
    TreeSet<EStation> set = new TreeSet<>((es1, es2) -> es1.id - es2.id);
    int id;
    EStation(int id) {
        this.id = id;
        set.add(this);
    }
}

    public int[] processQueries(int c, int[][] connections, int[][] queries) {
        Map<Integer, EStation> map = new HashMap<>();
        for (int i = 1; i <= c; i++) {
            EStation es = new EStation(i);
            map.put(i, es);
        }
        for (int i = 0; i < connections.length; i++) {
            int aId = connections[i][0];
            int bId = connections[i][1];
            EStation a = map.get(aId);
            EStation b = map.get(bId);
            aId = a.set.first().id;
            bId = b.set.first().id;
            if (aId < bId) {
                a.set.addAll(b.set);
                b.set = a.set;
            } else {
                b.set.addAll(a.set);
                a.set = b.set;
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < queries.length; i++) {
            if(queries[i][0] == 1) {
                EStation es = map.get(queries[i][1]);
                ans.add(es.active ? es.id : (es.set.isEmpty() ? -1 : es.set.first().id));
                continue;
            }
            int id = queries[i][1];
            EStation es = map.get(id);
            if (!es.active)
                continue;
            es.active = false;
            es.set.remove(es);
        }
        int[] aa = new int[ans.size()];
        int j = 0;
        while(j < aa.length) {
            aa[j] = ans.get(j);
            j++;
        }
        return aa;
    }

    public static void main(String[] args) {
        final EStations eStations = new EStations();
        int[][] conn = new int[2][];
        conn[0] = new int[] {3, 2};
        conn[1] = new int[]{2, 1};
        int[][] queries = new int[3][];
        queries[0] = new int[] {2, 2};
        queries[1] = new int[] {2, 3};
        queries[2] = new int[] {2, 1};
        eStations.processQueries(3, conn, queries);

    }
}

