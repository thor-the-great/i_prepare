package recursion;

/**
 * 1079. Letter Tile Possibilities
 * Medium
 *
 * You have a set of tiles, where each tile has one letter tiles[i] printed on it.  Return the number of possible
 * non-empty sequences of letters you can make.
 *
 *
 *
 * Example 1:
 *
 * Input: "AAB"
 * Output: 8
 * Explanation: The possible sequences are "A", "B", "AA", "AB", "BA", "AAB", "ABA", "BAA".
 * Example 2:
 *
 * Input: "AAABBC"
 * Output: 188
 *
 *
 * Note:
 *
 * 1 <= tiles.length <= 7
 * tiles consists of uppercase English letters.
 */
public class LetterTilePossibilities {

    /**
     * Count how many of each letter we have. Then start recursive backtracking. On each recursion iteration check
     * if any of the letters left. If so - use the first untaken one, decrement count and go to the next recursion
     * stack, after that put back the taken count.
     * @param tiles
     * @return
     */
    public int numTilePossibilities(String tiles) {
        int[] count = new int[26];
        for (char ch : tiles.toCharArray()) {
            ++count[ch - 'A'];
        }

        return helper(count);
    }

    int helper(int[] count) {
        int res = 0;
        for (int i = 0; i < 26; i++) {
            if (count[i] == 0)
                continue;
            ++res;
            --count[i];
            res+= helper(count);
            ++count[i];
        }
        return res;
    }
}
