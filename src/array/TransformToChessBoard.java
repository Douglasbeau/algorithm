package array;

public class TransformToChessBoard {
    public int movesToChessboard(int[][] board) {
        // 每行、每列的1、0的个数绝对不会变，所以可以统计行、列1数确认能否成功。
        // 行列交换本质上独立。
        // n为奇数时，需要每行、列1的总数为n/2或n/2+1
        int n = board.length;
        int numOf1sInALine = 0;
        int numOf0sInALine = 0;

        int numOfRowsWithMore1s = 0;
        int numOfColsWithMore1s = 0;
        int binNumRow = 0;
        int preBinNumRow = 0;
        for (int i=0; i<n; i++) {
            if (i>0)
                preBinNumRow = binNumRow;
            binNumRow = 0;
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 1) {
                    numOf1sInALine++;
                } else {
                    numOf0sInALine++;
                }
                binNumRow = (binNumRow << 1) | board[i][j];
            }
            if (binNumRow != preBinNumRow && (binNumRow & preBinNumRow) != 0)
                return -1;
            // num of 0 = num of 1
            if (badLine(numOf1sInALine, numOf0sInALine, n))
                return -1;
            if (numOf0sInALine > numOf1sInALine)
                numOfRowsWithMore1s++;
            numOf1sInALine = 0;
            numOf0sInALine = 0;
        }
        if(n%2 != 0 && !(numOfRowsWithMore1s == n/2 || numOfRowsWithMore1s == n/2+1))
            return -1;

        int binNumCol = 0;
        for(int j=0; j<n; j++) {
            for (int i = 0; i < n; i++) {
                if (board[i][j] == 1) {
                    numOf1sInALine++;
                } else {
                    numOf0sInALine++;
                }
                if (j==0)
                    binNumCol = (binNumCol << 1) | board[i][j];
            }
            // num of 0 = num of 1
            if(badLine(numOf1sInALine, numOf0sInALine, n))
                return -1;
            if(numOf0sInALine > numOf1sInALine)
                numOfColsWithMore1s++;
            numOf1sInALine = 0;
            numOf0sInALine = 0;
        }
        if(n%2 != 0 && numOfRowsWithMore1s != numOfColsWithMore1s )
            return -1;
        return process(binNumRow, n) + process(binNumCol, n);
    }

    private int process(int num, int n) {
        int oneAndZero = 0;
        int b = 1;
        for(int i=0; i<n; i++) {
            oneAndZero = (oneAndZero << 1) | b;
            b = 1-b;
        }
        int zeroAndOne = (~oneAndZero) & ((1 << n) -1);
        int disagreeNum;
        if(n % 2 == 1 && countOnes(num) == n/2) {
            disagreeNum = countOnes(zeroAndOne ^ num);
            return disagreeNum >> 1;
        } else if (n % 2 == 1 && countOnes(num) == n/2 + 1) {
            disagreeNum = countOnes(oneAndZero ^ num);
            return disagreeNum >> 1;
        }
        disagreeNum = countOnes(oneAndZero ^ num);
        return Math.min(disagreeNum, n-disagreeNum) >> 1;
    }

    private  int countOnes(int num) {
        int cnt = 0;
        while(num != 0) {
            cnt = cnt + (num & 1);
            num = num >>1;
        }
        return cnt;
    }

    private boolean badLine(int numOf1s, int numOf0s, int n) {
        if(n % 2 == 0 && !(numOf0s == n/2 && numOf1s == n/2)) {
            return true;
        }
        return n % 2 == 1 && !(numOf1s == n / 2 || numOf1s == n / 2 + 1);
    }

    public static void main(String[] args) {
        int[][] a = {
                {0, 1, 0, 0, 1},
                {1, 0, 1, 1, 0},
                {1, 0, 1, 1, 0},
                {0, 1, 0, 0, 1},
                {1, 0, 1, 1, 0}
        };
        int[][] a1 = {
                {0, 0, 1, 1},
                {1, 1, 0, 0},
                {0, 1, 0, 1},
                {1, 0, 1, 0}
        };
        int[][] a2 = {
                {1, 0},
                {0, 1}
        };
        TransformToChessBoard transformToChessBoard = new TransformToChessBoard();
        int i = transformToChessBoard.movesToChessboard(a2);
        System.out.println(i);
    }
}
