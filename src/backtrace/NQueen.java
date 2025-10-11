package backtrace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NQueen {
    List<List<String>> ans;
    public List<List<String>> solveNQueens(int n) {
        ans = new ArrayList<>();
        // 禁止放q的位置
        boolean[] forbiden = new boolean[n*n];
        // 记录放置q的位置 from 1
        int[] q = new int[n];
        dfs(0, n, forbiden, q);
        return ans;
    }

    void dfs(int curRow, int n, boolean[] forbiden, int[] q) {
        if (curRow == n) {
            List<String> item = new ArrayList(n);
            for (int i = 0; i < n; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < n; j++) {
                    sb.append(q[i] == j+1 ? "Q" : ".");
                }
                item.add(sb.toString());
            }
            ans.add(item);
            return;
        }
        for(int j = 0; j < n; j++) {
            if (forbiden[curRow * n + j])
                continue;
            boolean[] bak = Arrays.copyOf(forbiden, n*n);
            setForbiden(curRow, j, n, forbiden);
            q[curRow] = j+1;
            dfs(curRow + 1, n, forbiden, q);
            System.arraycopy(bak, 0, forbiden, 0, n*n);
            q[curRow] = 0;
        }
    }

    void setForbiden(int curRow, int col, int n, boolean[] forbiden) {
        for (int i = 0; i < n; i++) {
            // curRow行
            forbiden[curRow * n + i] = true;
            // col列
            forbiden[i * n + col] = true;
        }
        // 都从最左或上起始 计算距离
        int dist = Math.min(curRow, col);
        int startRow = curRow - dist;
        int j = col - dist;
        // 往右斜线
        for (int i = startRow; i < n && j< n; i++,j++) {
            forbiden[i * n + j] = true;
        }

        // 从最左或下起始
        dist = Math.min(n - 1 - curRow, col);
        startRow = curRow + dist;
        j = col - dist;
        for (int i = startRow; j < n && i >= 0; j++,i--) {
            forbiden[i * n + j] = true;
        }
    }

    public static void main(String[] args) {
        final NQueen nQueen = new NQueen();
        final List<List<String>> lists = nQueen.solveNQueens(4);
        for (List<String> l : lists) {
            System.out.println(l);
        }
    }
}

