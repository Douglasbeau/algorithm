package union_find;

import java.util.ArrayList;
import java.util.List;

public class IslandNum {
    public int numIslands(char[][] grid) {
        int m = grid.length; // 高
        int n = grid[0].length; // 长
        int grids = m * n;
        // （i，j）元素的位置 从零开始 i*n + j
        UnionFind uf = new UnionFind(grids);
        for (int i = 0; i < m; i++) {
            int base = i * n;
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != '1')
                    continue;
                int pos = base + j;
                uf.add(pos);
                // 上
                if (i - 1 >= 0 && grid[i-1][j] == '1')
                    uf.union(pos, pos - n);
                // 左
                if (j - 1 >= 0 && grid[i][j-1] == '1')
                    uf.union(pos, pos - 1);
            }
        }
        return uf.cnt;
    }
    private static class UnionFind {
        int[] parent;
        int[] size;
        int cnt;
        int[] stack;
        int si = -1;
        UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            stack = new int[n];
            cnt = 0;
        }
        void add(int i) {
            parent[i] = i;
            size[i] = 1;
            cnt++;
        }

        void union(int a, int b) {
            int ap = findParent(a);
            int bp = findParent(b);
            if (ap == bp)
                return;
            if (size[ap] >= size[bp]) {
                parent[bp] = ap;
                size[bp] = 0;
                size[ap] += size[bp];
            } else {
                parent[ap] = bp;
                size[ap] = 0;
                size[bp] += size[ap];
            }
            cnt--;
        }

        int findParent(int a) {
            while(a != parent[a]) {
                stack[++si] = a; // 收集沿途非parent点
                a = parent[a];
            }
            while (si > -1) {
                parent[stack[si--]] = a;
            }
            return a;
        }
    }

    public static void main(String[] args) {
        final IslandNum islandNum = new IslandNum();
        char o = '0';
        char i = '1';
        int r = islandNum.numIslands(new char[][] {{i, i, o, o, o}, {i, i, o, o, o}, {o, o, i, o, o}, {o, o, o, i, i}});
        System.out.println(r);
    }
}

