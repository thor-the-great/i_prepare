package binary_search;

import java.util.Arrays;

/**
 * 2055. Plates Between Candles
Medium

There is a long table with a line of plates and candles arranged on top of it. 
You are given a 0-indexed string s consisting of characters '*' and '|' only, 
where a '*' represents a plate and a '|' represents a candle.

You are also given a 0-indexed 2D integer array queries where queries[i] = [lefti, 
righti] denotes the substring s[lefti...righti] (inclusive). For each query, you 
need to find the number of plates between candles that are in the substring. 
A plate is considered between candles if there is at least one candle to its left 
and at least one candle to its right in the substring.

For example, s = "||**||**|*", and a query [3, 8] denotes the substring "*||**|". 
The number of plates between candles in this substring is 2, as each of the two plates 
has at least one candle in the substring to its left and right.
Return an integer array answer where answer[i] is the answer to the ith query.

Example 1:

ex-1
Input: s = "**|**|***|", queries = [[2,5],[5,9]]
Output: [2,3]
Explanation:
- queries[0] has two plates between candles.
- queries[1] has three plates between candles.
Example 2:

ex-2
Input: s = "***|**|*****|**||**|*", queries = [[1,17],[4,5],[14,17],[5,11],[15,16]]
Output: [9,0,0,0,0]
Explanation:
- queries[0] has nine plates between candles.
- The other queries have zero plates between candles.
 

Constraints:

3 <= s.length <= 105
s consists of '*' and '|' characters.
1 <= queries.length <= 105
queries[i].length == 2
0 <= lefti <= righti < s.length

 * https://leetcode.com/problems/plates-between-candles/
 */
public class PlatesBetweenCandles {
    /**
     * Idea:
     * create prefix sum array for candles between plates. This gives us ability to know how many are
     * between two indexes. First 2 will be 0, we count how many candles we saw already, so first number > 0 can be 
     * for at least index 2
     * for query use binary search on indexes. Use next to the right for left query index and next to the left
     * for right query index. Result will be prefixSum[right] - prefixSum[left]
     */
    public int[] platesBetweenCandles(String s, int[][] queries) {
        int numOfCandles = 0;
        for (char ch : s.toCharArray()) {
            if (ch == '|') {
                numOfCandles++;
            }
        }
        
        int[] indexes = new int[numOfCandles + 1], prefixSum = new int[numOfCandles + 1];
        
        int idx = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '|') {
                indexes[++idx] = i;
                if (idx <= 1) {
                    prefixSum[idx] = 0;
                } else {
                    prefixSum[idx] = prefixSum[idx - 1] + (i - indexes[idx - 1] - 1);
                }
            }
        }
        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int lIndex = Arrays.binarySearch(indexes, queries[i][0]);
            //take the right next if there is no exact match
            if (lIndex < 0) {
                lIndex = -(lIndex + 1);
            }
            
            int rIndex = Arrays.binarySearch(indexes, queries[i][1]);
            //take the right next if there is no exact match
            if (rIndex < 0) {
                rIndex = -(rIndex + 1) - 1;
            }
            if (lIndex >= rIndex) {
                result[i] = 0;
            } else {
                result[i] = prefixSum[rIndex] - prefixSum[lIndex];
            }
        }
        
        return result;
    }
}
