/**
 * 1138. Alphabet Board Path
 * Medium
 *
 *
 * Share
 * On an alphabet board, we start at position (0, 0), corresponding to character board[0][0].
 *
 * Here, board = ["abcde", "fghij", "klmno", "pqrst", "uvwxy", "z"], as shown in the diagram below.
 *
 *
 *
 * We may make the following moves:
 *
 * 'U' moves our position up one row, if the position exists on the board;
 * 'D' moves our position down one row, if the position exists on the board;
 * 'L' moves our position left one column, if the position exists on the board;
 * 'R' moves our position right one column, if the position exists on the board;
 * '!' adds the character board[r][c] at our current position (r, c) to the answer.
 * (Here, the only positions that exist on the board are positions with letters on them.)
 *
 * Return a sequence of moves that makes our answer equal to target in the minimum number of moves.  You may return
 * any path that does so.
 *
 *
 *
 * Example 1:
 *
 * Input: target = "leet"
 * Output: "DDR!UURRR!!DDD!"
 * Example 2:
 *
 * Input: target = "code"
 * Output: "RR!DDRR!UUL!R!"
 *
 *
 * Constraints:
 *
 * 1 <= target.length <= 100
 * target consists only of English lowercase letters.
 */
public class AlphabetBoardPath {

    static int uCode = 'u' - 'a';

    /**
     * Idea - calculate the board as codes of chars. Special case is 'z' - to to travel to 'u' and then from 'u' to
     * 'z'
     * @param target
     * @return
     */
    public String alphabetBoardPath(String target) {
        if (target == null || target.length() == 0)
            return "";
        //store previous location so we know starting point for every next character
        int prev = 0;
        StringBuilder sb = new StringBuilder();
        for (char ch : target.toCharArray()) {
            //code of next character
            int code = ch - 'a';
            if (code != prev) {
                //special case for 'z' - need to go to 'u' first, then from 'u' to 'z'
                if (ch == 'z') {
                    travelToChar(sb, uCode, prev);
                    travelToChar(sb, code, uCode);
                } else
                    travelToChar(sb, code, prev);
            }
            //type the character
            sb.append('!');
            //save state before next iteration
            prev = code;
        }

        return sb.toString();
    }

    void travelToChar(StringBuilder sb, int code, int prev) {
        //first move in rows
        int rowDif = (code / 5) - (prev / 5);
        for (int i = 0; i < Math.abs(rowDif); i++) {
            sb.append(rowDif > 0 ? 'D' : 'U');
        }
        //then in columns
        int colDif = (code % 5) - (prev % 5);
        for (int i = 0; i < Math.abs(colDif); i++) {
            sb.append(colDif > 0 ? 'R' : 'L');
        }
    }
}
