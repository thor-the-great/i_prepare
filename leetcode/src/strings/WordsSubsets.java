package strings;

import java.util.ArrayList;
import java.util.List;

/**
 * Word Subsets

Solution
We are given two arrays A and B of words.  Each word is a string of lowercase letters.

Now, say that word b is a subset of word a if every letter in b occurs in a, including multiplicity.  For example, "wrr" is a subset of "warrior", but is not a subset of "world".

Now say a word a from A is universal if for every b in B, b is a subset of a. 

Return a list of all universal words in A.  You can return the words in any order.

 

Example 1:

Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["e","o"]
Output: ["facebook","google","leetcode"]
Example 2:

Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["l","e"]
Output: ["apple","google","leetcode"]
Example 3:

Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["e","oo"]
Output: ["facebook","google"]
Example 4:

Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["lo","eo"]
Output: ["google","leetcode"]
Example 5:

Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["ec","oc","ceo"]
Output: ["facebook","leetcode"]
 

Note:

1 <= A.length, B.length <= 10000
1 <= A[i].length, B[i].length <= 10
A[i] and B[i] consist only of lowercase letters.
All words in A[i] are unique: there isn't i != j with A[i] == A[j].

https://leetcode.com/problems/word-subsets/solution/
 */
public class WordsSubsets {

    /**
     * Count number of each char in B first, keep max of each one. Then do the same for each of
     * A and check, if any of chars is less then in B - it's not a universal word.
     * @param A
     * @param B
     * @return
     */
    public List<String> wordSubsets(String[] A, String[] B) {
        int[] bMaxCount = new int[26];
        for (String b : B) {
            int[] bOneCount = count(b);
            for (int i = 0; i < 26; i++) {
                bMaxCount[i] = Math.max(bMaxCount[i], bOneCount[i]);
            }
        }
        
        List<String> res = new ArrayList();
        
        for (String a : A) {
            int[] aOneCount = count(a);
            boolean match = true;
            for (int i = 0 ; i < 26; i++) {
                if (aOneCount[i] < bMaxCount[i]) {
                    match = false;
                    continue;
                }
            }
            if (match) {
                res.add(a);
            }
        }
        
        return res;
    }
    
    int[] count(String s) {
        int[] res = new int[26];
        for (char ch : s.toCharArray()) {
            ++res[ch-'a'];
        }
        return res;
    }
}