package map;

import java.util.HashMap;
import java.util.Map;

/**
 * 914. X of a Kind in a Deck of Cards
Easy

In a deck of cards, each card has an integer written on it.

Return true if and only if you can choose X >= 2 such that it is possible to split the entire deck into 1 or more groups of cards, where:

Each group has exactly X cards.
All the cards in each group have the same integer.
 

Example 1:

Input: deck = [1,2,3,4,4,3,2,1]
Output: true
Explanation: Possible partition [1,1],[2,2],[3,3],[4,4].
Example 2:

Input: deck = [1,1,1,2,2,2,3,3]
Output: false
Explanation: No possible partition.
 

Constraints:

1 <= deck.length <= 104
0 <= deck[i] < 104

https://leetcode.com/problems/x-of-a-kind-in-a-deck-of-cards/
 */
public class XOfKindInDeckOfCards {
    
    /**
     * if we know count of each cards then answer is the GCD of all quantities
     * we can find GCD using euclidian algo
     */
    public boolean hasGroupsSizeX(int[] deck) {
        Map<Integer, Integer> count = new HashMap();
        
        for (int d : deck) {
            if (count.containsKey(d)) {
                count.put(d, count.get(d) + 1);
            } else {
                count.put(d, 1);
            }
        }
        
        int res = 0;
        for (int key : count.keySet()) {
            int c = count.get(key);
            res = gcd(c, res);
        }
        
        return res >= 2;
    }
    
    int gcd(int a, int b) {
        return b > 0 ? gcd(b, a % b) : a;
    }
}
