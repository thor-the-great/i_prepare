package random_problems;

/**
 * 1275. Find Winner on a Tic Tac Toe Game
 * Easy
 *
 * Tic-tac-toe is played by two players A and B on a 3 x 3 grid.
 *
 * Here are the rules of Tic-Tac-Toe:
 *
 * Players take turns placing characters into empty squares (" ").
 * The first player A always places "X" characters, while the second player B always places "O" characters.
 * "X" and "O" characters are always placed into empty squares, never on filled ones.
 * The game ends when there are 3 of the same (non-empty) character filling any row, column, or diagonal.
 * The game also ends if all squares are non-empty.
 * No more moves can be played if the game is over.
 * Given an array moves where each element is another array of size 2 corresponding to the row and column of the grid
 * where they mark their respective character in the order in which A and B play.
 *
 * Return the winner of the game if it exists (A or B), in case the game ends in a draw return "Draw", if there are
 * still movements to play return "Pending".
 *
 * You can assume that moves is valid (It follows the rules of Tic-Tac-Toe), the grid is initially empty and A will play first.
 *
 *
 *
 * Example 1:
 *
 * Input: moves = [[0,0],[2,0],[1,1],[2,1],[2,2]]
 * Output: "A"
 * Explanation: "A" wins, he always plays first.
 * "X  "    "X  "    "X  "    "X  "    "X  "
 * "   " -> "   " -> " X " -> " X " -> " X "
 * "   "    "O  "    "O  "    "OO "    "OOX"
 * Example 2:
 *
 * Input: moves = [[0,0],[1,1],[0,1],[0,2],[1,0],[2,0]]
 * Output: "B"
 * Explanation: "B" wins.
 * "X  "    "X  "    "XX "    "XXO"    "XXO"    "XXO"
 * "   " -> " O " -> " O " -> " O " -> "XO " -> "XO "
 * "   "    "   "    "   "    "   "    "   "    "O  "
 * Example 3:
 *
 * Input: moves = [[0,0],[1,1],[2,0],[1,0],[1,2],[2,1],[0,1],[0,2],[2,2]]
 * Output: "Draw"
 * Explanation: The game ends in a draw since there are no moves to make.
 * "XXO"
 * "OOX"
 * "XOX"
 * Example 4:
 *
 * Input: moves = [[0,0],[1,1]]
 * Output: "Pending"
 * Explanation: The game has not finished yet.
 * "X  "
 * " O "
 * "   "
 *
 *
 * Constraints:
 *
 * 1 <= moves.length <= 9
 * moves[i].length == 2
 * 0 <= moves[i][j] <= 2
 * There are no repeated elements on moves.
 * moves follow the rules of tic tac toe.
 */
public class FindWinnerTicTakToeGame {

    /**
     * Use 2 arrays to track rows and cols, then numbers for main and second diagonals.
     * @param moves
     * @return
     */
    public String tictactoe(int[][] moves) {
        //not possible to complete game in less than 5 moves
        if (moves.length < 5)
            return "Pending";
        //declare arrays to store results
        int[] rows = new int[6], cols = new int[6];
        int[] diag1 = new int[2], diag2 = new int[2];
        //start checking moves one by one
        for (int i = 0; i < moves.length; i++) {
            int[] move = moves[i];
            //whos move is this
            boolean aMove = (i % 2 == 0);
            //store row and col for checking if player has won
            int r = aMove ? move[0] : move[0] + 3;
            int c = aMove ? move[1] : move[1] + 3;
            //if we reach 3 in any row or column return winning player
            if (++rows[r] == 3)
                return aMove ? "A" : "B";
            if (++cols[c] == 3)
                return aMove ? "A" : "B";
            //check main diagonal
            if (move[0] == move[1]) {
                if (++diag1[aMove ? 0 : 1] == 3)
                    return aMove ? "A" : "B";
            }
            //check second diagonal
            if (2 - move[0] == move[1]) {
                if (++diag2[aMove ? 0 : 1] == 3)
                    return aMove ? "A" : "B";
            }
        }
        //if we reach this point then no moves lead to a win. check if we use all moves
        return (moves.length < 9) ? "Pending" : "Draw";
    }
}
