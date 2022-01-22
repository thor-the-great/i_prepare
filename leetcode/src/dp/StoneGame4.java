package dp;

import java.util.HashMap;
import java.util.Map;

/**
 * 1510. Stone Game IV
Hard

Alice and Bob take turns playing a game, with Alice starting first.

Initially, there are n stones in a pile. On each player's turn, that player makes a move consisting of removing any non-zero square number of stones in the pile.

Also, if a player cannot make a move, he/she loses the game.

Given a positive integer n, return true if and only if Alice wins the game otherwise return false, assuming both players play optimally.

 

Example 1:

Input: n = 1
Output: true
Explanation: Alice can remove 1 stone winning the game because Bob doesn't have any moves.
Example 2:

Input: n = 2
Output: false
Explanation: Alice can only remove 1 stone, after that Bob removes the last one winning the game (2 -> 1 -> 0).
Example 3:

Input: n = 4
Output: true
Explanation: n is already a perfect square, Alice can win with one move, removing 4 stones (4 -> 0).
 

Constraints:

1 <= n <= 105

https://leetcode.com/problems/stone-game-iv/
 */
public class StoneGame4 {
    
    /**
     * DP implemented botom-up
     */
    public boolean winnerSquareGame(int n) {
        boolean[] memo = new boolean[n + 1];
        memo[1] = true;
        
        for (int i = 0; i <= n; i++) {
            for (int k = 1; k * k  <= i; k++) {
                if (!memo[i - k * k]) {
                    memo[i] = true;
                    break;
                }
            }
        }
        
        return memo[n];
    }

    /**
     * 
     * DFS based:
     * there is a state that leads to fail. Need to find if you can bring opponent to that state
     * let's do dfs(n) return if current player can win
     * base case 
     *  dfs(0) - false
     *  dfs(1) - true
     * 
     * create cache number of stones left - result
     */
    public boolean winnerSquareGameDfs(int n) {
        Map<Integer, Boolean> memo = new HashMap();
        memo.put(0, false);
        return dfs(memo, n);
    }

    boolean dfs(Map<Integer, Boolean> map, int n) {
        if (map.containsKey(n)) {
            return map.get(n);
        }
        for (int i = 1; i*i <= n; i++ ) {
            if (!dfs(map, n - (i*i))) {
                map.put(n, true);
                return true;
            }
        }
        map.put(n, false);
        return false;
    }
}
