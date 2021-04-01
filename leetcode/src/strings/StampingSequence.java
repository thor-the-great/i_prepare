package strings;

/**
 * 936. Stamping The Sequence
Hard

You want to form a target string of lowercase letters.

At the beginning, your sequence is target.length '?' marks.  You also have a stamp of lowercase letters.

On each turn, you may place the stamp over the sequence, and replace every letter in the sequence with the corresponding 
letter from the stamp.  You can make up to 10 * target.length turns.

For example, if the initial sequence is "?????", and your stamp is "abc",  then you may make "abc??", "?abc?", "??abc" 
in the first turn.  (Note that the stamp must be fully contained in the boundaries of the sequence in order to stamp.)

If the sequence is possible to stamp, then return an array of the index of the left-most letter being stamped at each turn.  
If the sequence is not possible to stamp, return an empty array.

For example, if the sequence is "ababc", and the stamp is "abc", then we could return the answer [0, 2], 
corresponding to the moves "?????" -> "abc??" -> "ababc".

Also, if the sequence is possible to stamp, it is guaranteed it is possible to stamp within 10 * target.length moves. 
 Any answers specifying more than this number of moves will not be accepted.
 

Example 1:

Input: stamp = "abc", target = "ababc"
Output: [0,2]
([1,0,2] would also be accepted as an answer, as well as some other answers.)

Example 2:

Input: stamp = "abca", target = "aabcaca"
Output: [3,0,1]

 

Note:

    1 <= stamp.length <= target.length <= 1000
    stamp and target only contain lowercase letters.


 * https://leetcode.com/problems/stamping-the-sequence/
 */
public class StampingSequence {

    /**
     * Going backwards from the target. It must be one full stamp in target cause otherwise
     * it's not possible to make a final stamp.
     * 
     * If we replace that part with "***" then the rest of strign can ve replaced too where the chars are 
     * either matching stamp (including order) or are "*" (alredy replaced).
     * 
     * We count replaced chars adn do full string scans unless we replace all chars or there will be a cycle when we haven't replaced
     * anything. In case case solution is not possible.
     * 
     * Time Complexity: O(N(N−M)), where M,NM, NM,N are the lengths of stamp, target.
     * Space Complexity: O(N(N−M)).  
     * 
     * @param stamp
     * @param target
     * @return
     */
    public int[] movesToStamp(String stamp, String target) {
        char[] sArr = stamp.toCharArray(), tArr = target.toCharArray();
        List<Integer> moves = new ArrayList();
        boolean[] visited = new boolean[target.length()];
        int stars = 0;

        while (stars < target.length()) {
            boolean replaced = false;
            //do the full scan of whats left of target string
            for (int i = 0; i <= target.length() - stamp.length(); i++) {
                //if we haven't checked this one yet and can be stamped - do the stamp count greedely
                if (!visited[i] && canReplace(sArr, tArr, i)) {
                    replaced = true;
                    visited[i] = true;
                    stars += replace(tArr, i, stamp.length());
                    moves.add(i);
                }
                if (stars == target.length()) {
                    break;
                }
            }
            if (!replaced) {
                return new int[0];
            }
        }
        //count count solution backward cause previously on scan we did backward as well, negating negated
        int p = 0;
        int[] res = new int[moves.size()];
        for (int i = moves.size() - 1; i >= 0; i--) {
            res[p++] = moves.get(i);
        }
        return res;
    }

    boolean canReplace(char[] stamp, char[] target, int idx) {
        for (int i = 0; i < stamp.length; i++) {
            if (target[i + idx] != '*' && stamp[i] != target[i + idx]) {
                return false;
            }
        }
        return true;
    }

    int replace(char[] target, int idx, int sLen) {
        int count = 0;
        for (int i = 0; i < sLen; i++) {
            if (target[idx + i] != '*') {
                target[idx + i] = '*';
                ++count;
            }
        }
        return count;
    }
}
