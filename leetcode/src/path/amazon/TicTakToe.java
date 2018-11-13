package path.amazon;

class TicTacToe {

    /** Initialize your data structure here. */
    int[] cols;
    int[] rows;
    int diag;
    int antidiag;
    public TicTacToe(int n) {
        cols = new int[n];
        rows = new int[n];
        diag = 0;
        antidiag = 0;
    }

    /** Player {player} makes a move at ({row}, {col}).
     @param row The row of the board.
     @param col The column of the board.
     @param player The player, can be either 1 or 2.
     @return The current winning condition, can be either:
     0: No one wins.
     1: Player 1 wins.
     2: Player 2 wins. */
    public int move(int row, int col, int player) {
        int x = player == 1 ? 1 : -1;
        int N = rows.length;
        rows[row] += x;
        cols[col] += x;
        if (row == col)
            diag += x;
        if (N - row - 1 == col)
            antidiag += x;
        if (Math.abs(rows[row]) == N || Math.abs(cols[col]) == N || Math.abs(diag) == N || Math.abs(antidiag) == N)
            return player;
        else
            return 0;
    }
}
