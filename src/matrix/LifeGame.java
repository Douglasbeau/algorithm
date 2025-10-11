package matrix;

public class LifeGame {
    int[][] board;
    int m;
    int n;
    public void gameOfLife(int[][] board) {
        this.board = board;
        m = board.length;
        n = board[0].length;
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                int cnt = countLive(i, j);
                if (board[i][j] == 0 && cnt == 3) {
                    board[i][j] = -1; // 0->-1
                    continue;
                }
                if (board[i][j] == 1) {
                    if (cnt < 2 || cnt > 3)
                        board[i][j] = 2;
                }
            }
        }
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (board[i][j] == -1)
                    board[i][j] = 1;
                else if (board[i][j] == 2)
                    board[i][j] = 0;
            }
        }
    }

    int countLive(int i, int j) {
        int ans = 0;
        if (i > 0) {
            if (board[i-1][j] > 0)
                ans++;
            if (j > 0 && board[i-1][j-1] > 0)
                ans++;
            if (j < n-1 && board[i-1][j+1] > 0)
                ans++;
        }
        if (i < m-1) {
            if (board[i+1][j] > 0)
                ans++;
            if (j > 0 && board[i+1][j-1] > 0)
                ans++;
            if (j < n-1 && board[i+1][j+1] > 0)
                ans++;
        }
        if (j > 0 && board[i][j-1] > 0)
            ans++;
        if (j < n-1 && board[i][j+1] > 0)
            ans++;
        return ans;
    }
}

