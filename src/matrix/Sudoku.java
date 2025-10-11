package matrix;

import java.util.Arrays;

public class Sudoku {
    public void solveSudoku(char[][] board) {
        int m = board.length;
        int n = board[0].length;
        int[] rows = new int[9];
        int[] cols = new int[9];
        int[][] grids = new int[3][3];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char cur = board[i][j];
                if (cur == '.') continue;
                int mask = 1 << (cur - '1');
                rows[i] |= mask;
                cols[j] |= mask;
                grids[i / 3][j / 3] |= mask;
            }
        }

        boolean r = process(board, 0, 0, rows, cols, grids);
        System.out.println(r);
    }

    // 填row、col及其后所有位置
    private boolean process(char[][] board, int row, int col,
                            int[] rows, int[] cols, int[][] grids) {
        if (row == 9)
            return true;
        if (board[row][col] != '.') {
//            return process(board, col == 8 ? row + 1 : row, (col + 1) % 9, rows, cols, grids);
            int i = row;
            l : for (;i < 9; i++) {
                for (int j = i == row ? col : 0; j < 9; j++) {
                    if (board[i][j] == '.') {
                        row = i;
                        col = j;
                        break l;
                    }
                }
            }
            if (i == 9)
                return true;
        }

        // 尝试在不冲突情况下填数
        for (int v = 0; v < 9; v++) {
            int mask = 1 << v;
            if ((rows[row] & mask) != 0 ||
                    (cols[col] & mask) != 0 ||
                    (grids[row / 3][col / 3] & mask) != 0)
                continue;
            board[row][col] = (char) ('1' + v);
            rows[row] |= mask;
            cols[col] |= mask;
            grids[row / 3][col / 3] |= mask;

            //col == 8 ? row + 1 : row;
            boolean result = process(board, col == 8 ? row + 1 : row, (col + 1) % 9,
                    rows, cols, grids);
            if (result) {
                return true;
            }
            board[row][col] = '.';
            rows[row] ^= mask;
            cols[col] ^= mask;
            grids[row / 3][col / 3] ^= mask;
        }
        return false;
    }

    public static void main(String[] args) {
        final Sudoku sudoku = new Sudoku();
        char[][] board = new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        sudoku.solveSudoku(board);
        for (char[] b : board) {
            System.out.println(Arrays.toString(b));
        }
    }
}
