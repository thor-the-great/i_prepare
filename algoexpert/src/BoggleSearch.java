/**
 * Boggle Search New
 *     Multi Dimensional Arrays DFS Search Algorithms Recursion
 * You're given a 2D Boggle Board which contains an m x n matrix of chars - char[][] board, and a String - word. Write
 * a method - boggleSearch that searches the Boggle Board for the presence of the input word. Words on the board can
 * be constructed with sequentially adjacent letters, where adjacent letters are horizontal or vertical neighbors
 * (not diagonal). Also, each letter on the Boggle Board must be used only once.
 *
 * Example:
 *
 * Input Board :
 * {
 *     {A, O, L},
 *     {D, E, L},
 *     {G, H, I},
 * }
 * Word: "HELLO"
 * Output: true
 */
public class BoggleSearch {
    public static boolean boggleSearch(char[][] board, String word){

        for(int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                if (helper(board, r, c, word, ""))
                    return true;
            }
        }

        return false;
    }

    static boolean helper(char[][] board, int r, int c, String word, String cur) {
        if (cur.length() >= word.length())
            return false;

        if (board[r][c] == '&')
            return false;
        cur += board[r][c];
        if (word.equals(cur))
            return true;

        char t = board[r][c];
        board[r][c] = '&';

        if (r > 0) {
            if (helper(board, r - 1, c, word, cur))
                return true;
        }
        if (c > 0) {
            if (helper(board, r, c - 1, word, cur))
                return true;
        }

        if (r < board.length - 1) {
            if (helper(board, r + 1, c, word, cur))
                return true;
        }
        if (c < board[0].length - 1) {
            if (helper(board, r, c + 1, word, cur))
                return true;
        }
        board[r][c] = t;
        return false;
    }
}
