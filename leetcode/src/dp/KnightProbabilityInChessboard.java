package dp;

/**
 * 688. Knight Probability in Chessboard
 * Medium
 *
 * On an NxN chessboard, a knight starts at the r-th row and c-th column and attempts to make exactly K moves. The
 * rows and columns are 0 indexed, so the top-left square is (0, 0), and the bottom-right square is (N-1, N-1).
 *
 * A chess knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal
 * direction, then one square in an orthogonal direction.
 *
 *
 *
 *
 *
 *
 *
 * Each time the knight is to move, it chooses one of eight possible moves uniformly at random (even if the piece
 * would go off the chessboard) and moves there.
 *
 * The knight continues moving until it has made exactly K moves or has moved off the chessboard. Return the
 * probability that the knight remains on the board after it has stopped moving.
 *
 *
 *
 * Example:
 *
 * Input: 3, 2, 0, 0
 * Output: 0.0625
 * Explanation: There are two moves (to (1,2), (2,1)) that will keep the knight on the board.
 * From each of those positions, there are also two moves that will keep the knight on the board.
 * The total probability the knight stays on the board is 0.0625.
 *
 *
 * Note:
 *
 * N will be between 1 and 25.
 * K will be between 0 and 100.
 * The knight always initially starts on the board.
 */
public class KnightProbabilityInChessboard {

    int[] dr = new int[] {-1, -2, -2, -1, 1, 2, 2, 1};
    int[] dc = new int[] {-2, -1, 1, 2, 2, 1, -1, -2};

    /**
     * Idea - keep the probability from previous step in the grid, use it to count probability of the next step
     * (as dpNext[r][c] = dp[r][c] / 8.0). Keep shifts for 8 possible moves in array, keep trying doing those steps.
     * At the end it's  DP solution
     * @param N
     * @param K
     * @param r
     * @param c
     * @return
     */
    public double knightProbability(int N, int K, int r, int c) {
        double[][] dp = new double[N][N];
        dp[r][c] = 1.0;

        for (;K >0; K--) {
            double[][] dpCur = new double[N][N];
            for(int rr = 0; rr < N; rr++) {
                for (int cc = 0; cc < N; cc++) {
                    //making 8 moves
                    for (int d = 0; d < 8; d++) {
                        //check if we are still on the board
                        int newR = rr + dr[d], newC = cc + dc[d];
                        if ((newR < N && newR >= 0)
                                && (newC < N && newC >= 0)) {
                            dpCur[newR][newC] += dp[rr][cc] / 8.0;
                        }
                    }
                }
            }
            //at the end of move make current state as prev
            dp = dpCur;
        }

        //now collect all probabilities
        double res = 0.0;
        for(int rr = 0; rr < N; rr++) {
            for (int cc = 0; cc < N; cc++) {
                res+= dp[rr][cc];
            }
        }

        return res;
    }
}
