/**
 * 
 * 318. Maximum Product of Word Lengths
Medium

Given a string array words, return the maximum value of length(word[i]) * length(word[j]) where the two words do not share common letters. If no such two words exist, return 0.

 

Example 1:

Input: words = ["abcw","baz","foo","bar","xtfn","abcdef"]
Output: 16
Explanation: The two words can be "abcw", "xtfn".
Example 2:

Input: words = ["a","ab","abc","d","cd","bcd","abcd"]
Output: 4
Explanation: The two words can be "ab", "cd".
Example 3:

Input: words = ["a","aa","aaa","aaaa"]
Output: 0
Explanation: No such pair of words.
 

Constraints:

2 <= words.length <= 1000
1 <= words[i].length <= 1000
words[i] consists only of lowercase English letters.
 * 
 * https://leetcode.com/problems/maximum-product-of-word-lengths
 */
public class MaximumProductofWordLengths {
    
    /**
     * As we need only max product of two strings it's easy:
     * 
     * - calculate the bit mask of characters in each string
     * - for each string pair compare the masks and if there is no overlap this means there are no same letters
     * - calcualte product for such pair, keep running max
     * 
     * O(n^2) - time
     * O(n) - space
     * @param words
     * @return
     */
    public int maxProduct(String[] words) {
        int N = words.length;
        int[] hashes = new int[N];
        
        for (int i = 0; i < N; i++) {
            String word = words[i];
            for (char ch : word.toCharArray()) {
                hashes[i] |= (1<<(ch -'a'));
            }
        }
        
        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (((hashes[i]&hashes[j]) == 0)) {
                    max = Math.max(max, words[i].length()*words[j].length());
                }
            }
        }
        return max;
    }
}
