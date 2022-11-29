package sorting;

import java.util.ArrayList;
import java.util.List;

/**
 * 2225. Find Players With Zero or One Losses
Medium

You are given an integer array matches where matches[i] = [winneri, loseri] indicates that the player winneri defeated player loseri in a match.

Return a list answer of size 2 where:

    answer[0] is a list of all players that have not lost any matches.
    answer[1] is a list of all players that have lost exactly one match.

The values in the two lists should be returned in increasing order.

Note:

    You should only consider the players that have played at least one match.
    The testcases will be generated such that no two matches will have the same outcome.

 

Example 1:

Input: matches = [[1,3],[2,3],[3,6],[5,6],[5,7],[4,5],[4,8],[4,9],[10,4],[10,9]]
Output: [[1,2,10],[4,5,7,8]]
Explanation:
Players 1, 2, and 10 have not lost any matches.
Players 4, 5, 7, and 8 each have lost one match.
Players 3, 6, and 9 each have lost two matches.
Thus, answer[0] = [1,2,10] and answer[1] = [4,5,7,8].

Example 2:

Input: matches = [[2,3],[1,3],[5,4],[6,4]]
Output: [[1,2,5,6],[]]
Explanation:
Players 1, 2, 5, and 6 have not lost any matches.
Players 3 and 4 each have lost two matches.
Thus, answer[0] = [1,2,5,6] and answer[1] = [].

 

Constraints:

    1 <= matches.length <= 105
    matches[i].length == 2
    1 <= winneri, loseri <= 105
    winneri != loseri
    All matches[i] are unique.

 */
public class FindPlayersWithZeroorOneLosses {

    /**
     * Allocate array for num of looses, from 1 to 100_000. Iterate over match results and
     * fill num of looses:
     *  0 - default, no games
     * -1 - win games, 0 looses
     * >1 - num of looses
     * 
     * thne iterate over array, so layer indexes will be sorted
     */
    public List<List<Integer>> findWinners(int[][] matches) {
        int N = 100_001, min = 100_001, max = 0;
        int[] resArr = new int[N];
        for (int[] match : matches) {
            int win = match[0], los = match[1];
            //looser case
            if (resArr[los] >= 0) {
                resArr[los]++; 
            } else if (resArr[los] == -1) {
                resArr[los] = 1;
            }
            //winner case
            if (resArr[win] == 0) {
                resArr[win] = -1;
            }
            min = Math.min(min, Math.min(win, los));
            max = Math.max(max, Math.max(win, los));
        }

        List<List<Integer>> res = new ArrayList();
        List<Integer> noLost = new ArrayList();
        List<Integer> lostOne = new ArrayList();
        for (int i = min; i <= max; i++) {
            if (resArr[i] == -1) {
                noLost.add(i);
            } else if (resArr[i] == 1) {
                lostOne.add(i);
            }
        }
        res.add(noLost); res.add(lostOne);
        return res;
    }
}
