package random_problems;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * 1244. Design A Leaderboard
 * Medium
 *
 * Design a Leaderboard class, which has 3 functions:
 *
 * addScore(playerId, score): Update the leaderboard by adding score to the given player's score. If there is no
 * player with such id in the leaderboard, add him to the leaderboard with the given score.
 * top(K): Return the score sum of the top K players.
 * reset(playerId): Reset the score of the player with the given id to 0. It is guaranteed that the player was added
 * to the leaderboard before calling this function.
 * Initially, the leaderboard is empty.
 *
 *
 *
 * Example 1:
 *
 * Input:
 * ["Leaderboard","addScore","addScore","addScore","addScore","addScore","top","reset","reset","addScore","top"]
 * [[],[1,73],[2,56],[3,39],[4,51],[5,4],[1],[1],[2],[2,51],[3]]
 * Output:
 * [null,null,null,null,null,null,73,null,null,null,141]
 *
 * Explanation:
 * Leaderboard leaderboard = new Leaderboard ();
 * leaderboard.addScore(1,73);   // leaderboard = [[1,73]];
 * leaderboard.addScore(2,56);   // leaderboard = [[1,73],[2,56]];
 * leaderboard.addScore(3,39);   // leaderboard = [[1,73],[2,56],[3,39]];
 * leaderboard.addScore(4,51);   // leaderboard = [[1,73],[2,56],[3,39],[4,51]];
 * leaderboard.addScore(5,4);    // leaderboard = [[1,73],[2,56],[3,39],[4,51],[5,4]];
 * leaderboard.top(1);           // returns 73;
 * leaderboard.reset(1);         // leaderboard = [[2,56],[3,39],[4,51],[5,4]];
 * leaderboard.reset(2);         // leaderboard = [[3,39],[4,51],[5,4]];
 * leaderboard.addScore(2,51);   // leaderboard = [[2,51],[3,39],[4,51],[5,4]];
 * leaderboard.top(3);           // returns 141 = 51 + 51 + 39;
 *
 *
 * Constraints:
 *
 * 1 <= playerId, K <= 10000
 * It's guaranteed that K is less than or equal to the current number of players.
 * 1 <= score <= 100
 * There will be at most 1000 function calls.
 *
 * User two maps - one hashmap for the lookup score by playerId, second - sorted tree to store scores and qty of each
 * score, sorted by score - to get first K max scores
 *
 */
public class Leaderboard {
    //playedId-score link
    Map<Integer, Integer> m;
    //score-number of such scores
    Map<Integer, Integer> scores;
    Comparator<Integer> comp = new Comparator<Integer>() {
        public int compare(Integer i1, Integer i2) {
            return i2 - i1;
        }
    };

    public Leaderboard() {
        m = new HashMap();
        scores = new TreeMap(comp);
    }

    public void addScore(int playerId, int score) {
        //if we have this player - update his score
        if (m.containsKey(playerId)) {
            //get old score, decrement number of this scores in sorted map
            int oldScore = m.get(playerId);
            scores.put(oldScore, scores.get(oldScore) - 1);
            if (scores.get(oldScore) == 0)
                scores.remove(oldScore);
            //get the updated score, put it to the maps
            oldScore+= score;
            scores.put(oldScore, scores.getOrDefault(oldScore, 0) + 1);
            m.put(playerId, oldScore);
        } else {
            //this is new score - create new mapping and update number of such score if needed
            m.put(playerId, score);
            scores.put(score, scores.getOrDefault(score, 0) + 1);
        }
    }

    public int top(int K) {
        //iterate key of sorted map, catch is to take into account number of times we have seen each score
        int sum = 0;
        int c = 0;
        for (int key : scores.keySet()) {
            //for each next higher score get number of times we've seen it
            int count = scores.get(key);
            while (count > 0 && c < K) {
                sum += key;
                count--;
                c++;
            }
        }
        return sum;
    }

    public void reset(int playerId) {
        //remove the mapping by player id, update sorted map by decrementing number of times we've seen this score
        int oldScore = m.get(playerId);
        scores.put(oldScore, scores.get(oldScore) - 1);
        if (scores.get(oldScore) == 0)
            scores.remove(oldScore);
        m.remove(playerId);
    }
}
