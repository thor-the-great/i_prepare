package random_problems;

/**
 * 999. Available Captures for Rook
 * Easy
 *
 * On an 8 x 8 chessboard, there is one white rook.  There also may be empty squares, white bishops, and black pawns.
 * These are given as characters 'R', '.', 'B', and 'p' respectively. Uppercase characters represent white pieces, and
 * lowercase characters represent black pieces.
 *
 * The rook moves as in the rules of Chess: it chooses one of four cardinal directions (north, east, west, and south),
 * then moves in that direction until it chooses to stop, reaches the edge of the board, or captures an opposite
 * colored pawn by moving to the same square it occupies.  Also, rooks cannot move into the same square as other
 * friendly bishops.
 *
 * Return the number of pawns the rook can capture in one move.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: [[".",".",".",".",".",".",".","."],[".",".",".","p",".",".",".","."],[".",".",".","R",".",".",".","p"],
 * [".",".",".",".",".",".",".","."],[".",".",".",".",".",".",".","."],[".",".",".","p",".",".",".","."],
 * [".",".",".",".",".",".",".","."],[".",".",".",".",".",".",".","."]]
 * Output: 3
 * Explanation:
 * In this example the rook is able to capture all the pawns.
 * Example 2:
 *
 *
 *
 * Input: [[".",".",".",".",".",".",".","."],[".","p","p","p","p","p",".","."],[".","p","p","B","p","p",".","."],
 * [".","p","B","R","B","p",".","."],[".","p","p","B","p","p",".","."],[".","p","p","p","p","p",".","."],
 * [".",".",".",".",".",".",".","."],[".",".",".",".",".",".",".","."]]
 * Output: 0
 * Explanation:
 * Bishops are blocking the rook to capture any pawn.
 * Example 3:
 *
 *
 *
 * Input: [[".",".",".",".",".",".",".","."],[".",".",".","p",".",".",".","."],[".",".",".","p",".",".",".","."],
 * ["p","p",".","R",".","p","B","."],[".",".",".",".",".",".",".","."],[".",".",".","B",".",".",".","."],
 * [".",".",".","p",".",".",".","."],[".",".",".",".",".",".",".","."]]
 * Output: 3
 * Explanation:
 * The rook can capture the pawns at positions b5, d6 and f5.
 *
 *
 * Note:
 *
 * board.length == board[i].length == 8
 * board[i][j] is either 'R', '.', 'B', or 'p'
 * There is exactly one cell with board[i][j] == 'R'
 *
 */
public class AvailableCapturesForRook {
    int rows;
    int cols;

    public int numRookCaptures(char[][] board) {
        rows = board.length;
        cols = board[0].length;

        int rookR = -1, rookC = -1;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (board[r][c] == 'R') {
                    rookR = r;
                    rookC = c;
                    break;
                }
            }
        }

        int res = 0;
        int[][] dir = new int[][] {{-1, 0}, { 1, 0},  {0, -1}, { 0, 1}};

        for (int[] d : dir) {
            int r = rookR, c = rookC;
            while(isValid(r + d[0], c + d[1])) {
                r += d[0];
                c += d[1];
                char val = board[r][c];
                if ( val == 'B')
                    break;
                if (val == 'p') {
                    res++;
                    break;
                }
            }
        }

        return res;
    }

    boolean isValid(int r, int c) {
        if (r >=0 && r < rows && c >=0 && c < cols)
            return true;

        return false;
    }
}
