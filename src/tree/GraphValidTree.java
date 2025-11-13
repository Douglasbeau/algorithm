package tree;

import java.util.ArrayList;
import java.util.List;

// LC 261 会员题 以图判树
// 树的特点是全连通，边数+1=点数
public class GraphValidTree {
    public boolean validTree(int n, int[][] edges) {
        int es = edges.length;
        if (n - 1 != es)
            return false;
        List[] children = new ArrayList[n];
        // node -> children
        for (int[] edge : edges) {
            int x = edge[0];
            int y = edge[1];
            if (children[x] == null) {
                children[x] = new ArrayList<>();
            }
            children[x].add(y);
            if (children[y] == null) {
                children[y] = new ArrayList<>();
            }
            children[y].add(x);
        }
        boolean[] visited = new boolean[n];
        // 遍历得到个数
        int cnt = dfs(children, 0, visited);
        return cnt == n;
    }

    int dfs(List[] children, int cur, boolean[] visited) {
        visited[cur] = true;
        List list = children[cur];
        if (list == null)
            return 1;
        int cnt = 1;
        for (int i=0; i<list.size(); i++) {
            Integer ch = (Integer)list.get(i);
            if (visited[ch])
                continue;
            cnt += dfs(children, ch, visited);
        }

        return cnt;
    }
}
