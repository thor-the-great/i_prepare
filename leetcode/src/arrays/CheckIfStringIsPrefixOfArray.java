package arrays;

/**
 * 1961. Check If String Is a Prefix of Array

Given a string s and an array of strings words, determine whether s is a prefix string of words.

A string s is a prefix string of words if s can be made by concatenating the first k strings in words for some positive k no larger than words.length.

Return true if s is a prefix string of words, or false otherwise.

 

Example 1:

Input: s = "iloveleetcode", words = ["i","love","leetcode","apples"]
Output: true
Explanation:
s can be made by concatenating "i", "love", and "leetcode" together.
Example 2:

Input: s = "iloveleetcode", words = ["apples","i","love","leetcode"]
Output: false
Explanation:
It is impossible to make s using a prefix of arr.
 

Constraints:

1 <= words.length <= 100
1 <= words[i].length <= 20
1 <= s.length <= 1000
words[i] and s consist of only lowercase English letters.
 * 
 * https://leetcode.com/problems/check-if-string-is-a-prefix-of-array/submissions/
 */
public class CheckIfStringIsPrefixOfArray {
    /**
     * Keep pointer of where we are in String, check each word and for each word 
     * check characters in word and in the string by moving pointers in both. On first
     * mismatch return false
     * 
     * @param s
     * @param words
     * @return
     */
    public boolean isPrefixString(String s, String[] words) {
        int sPointer = 0;
        for (String word : words) {
            if (s.length() - sPointer < word.length()) {
                return false;
            }
            for (int i = 0; i < word.length(); i++) {
                if (s.charAt(sPointer++) != word.charAt(i)) {
                    return false;
                }
            }
            if (sPointer >= s.length()) {
                return true;
            }
        }
        return false;
    }
}