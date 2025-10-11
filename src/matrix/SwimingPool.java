package matrix;

// LeetCode 778
// 二分+遍历、并查集、Dijkstra都行
public class SwimingPool {
    int[][] grid;
    int n;
    public int swimInWater(int[][] grid) {
        this.grid = grid;
        this.n = grid.length;
        if (n == 1)
            return 0;
        int LEN = n * n;
        int t = -1;
        // boolean[] visited = new boolean[n*n]; // 低水位
        // while(grid[0][0] > t) {
        //     t++;
        // }
        // visited[0] = true;
        // boolean end = false;
        // //传染算法：
        // // 从谁开始？每个visited
        // // 谁是visited？左上先
        // // 怎么传染？上下左右 <=t 的
        // // 什么时候结束？传染到右下
        // while (true) {
        //     for (int i=0; i<visited.length; i++) {
        //         if(visited[i]) {
        //             end = infect(visited, i/n, i%n, t);
        //             if (end)
        //                 return t;
        //         }
        //     }
        //     t++;
        // }
        // 法一、无优化的并查集
        // UnionFind uf = new UnionFind(n * n);
        // do {
        //     t++;
        //     union(uf, n, t);
        // } while(! uf.sameUnion(0, n*n-1));
        // ！由题意可以优化并查集——既然每格子高度，不同，则可以建立高度：位置映射，每次t++只一个union四周
        int[] index = new int[LEN];
        for (int i=0; i<n; i++) {
            int base = i * n;
            for (int j=0; j<n; j++){
                index[grid[i][j]] = base + j;
            }
        }
        UnionFind uf = new UnionFind(n * n);
        while(!uf.sameUnion(0, LEN-1)) {
            t++;
            int idx = index[t];
            int i = idx / n;
            int j = idx % n;
            if (i > 0 && grid[i-1][j] <= t)
                uf.union(idx, idx - n);
            if (i < n - 1 && grid[i+1][j] <= t)
                uf.union(idx, idx + n);
            if (j > 0 && grid[i][j-1] <= t)
                uf.union(idx, idx - 1);
            if (j < n - 1 && grid[i][j+1] <= t)
                uf.union(idx, idx + 1);
        }
        return t;
    }
    // 每个visited的向
    boolean infect(boolean[] visited, int i, int j, int t) {
        if (i == n-1 && j == n-1)
            return true;
        int base = i * n + j;
        boolean res;
        if (i > 0 && grid[i-1][j] <= t && !visited[base-n]) {
            visited[base-n] = true;
            res = infect(visited, i-1, j, t);
            if(res)
                return true;
        }
        if (i < n-1 && grid[i+1][j] <= t && !visited[base+n]) {
            visited[base+n] = true;
            res = infect(visited, i+1, j, t);
            if(res)
                return true;
        }
        if (j > 0 && grid[i][j-1] <= t && !visited[base-1]) {
            visited[base-1] = true;
            res = infect(visited, i, j-1, t);
            if(res)
                return true;
        }
        if (j < n - 1 && grid[i][j+1] <= t && !visited[base+1]) {
            visited[base+1] = true;
            res = infect(visited, i, j+1, t);
            if(res)
                return true;
        }
        return false;
    }
    // 法一、并查集
    void union(UnionFind uf, int n, int t) {
        for (int i=0; i<n; i++) {
            int base = i * n;
            for (int j=0; j<n; j++) {
                int cur = base + j;
                // 与<=t的四周连通 当前高度==t
                if (grid[i][j] == t) {
                    if (i >0 && grid[i-1][j] <= t) {
                        uf.union(cur, cur - n);
                    }
                    if (i < n-1 && grid[i+1][j] <= t) {
                        uf.union(cur, cur + n);
                    }
                    if (j > 0 && grid[i][j-1] <= t) {
                        uf.union(cur, cur - 1);
                    }
                    if (j < n - 1 && grid[i][j+1] <= t) {
                        uf.union(cur, cur + 1);
                    }
                }
            }
        }
    }

    private static class UnionFind{
        private int[] sizes;
        private int[] parents;
        private int[] stack;
        private int si;

        UnionFind(int capacity) {
            this.sizes = new int[capacity];
            this.parents = new int[capacity];
            this.stack = new int[capacity];
            this.si = -1;
            for (int i=0; i<capacity; i++) {
                parents[i] = i;
                sizes[i] = 1;
            }
        }
        boolean sameUnion(int a, int b) {
            int ap = getParent(a);
            int bp = getParent(b);
            return ap == bp;
        }
        int getParent(int a) {
            while(a != parents[a]) {
                stack[++si] = a;
                a = parents[a];
            }
            while(si > -1) {
                int child = stack[si--];
                parents[child] = a;
            }
            return a;
        }
        void union(int a, int b) {
            int ap = getParent(a);
            int bp = getParent(b);
            if (ap == bp)
                return;
            int as = sizes[ap];
            int bs = sizes[bp];
            if (as >= bs) {
                parents[bp] = ap;
                sizes[ap] += bs;
                sizes[bp] = 0;
            } else {
                parents[ap] = bp;
                sizes[bp] += as;
                sizes[ap] = 0;
            }
        }
    }

}