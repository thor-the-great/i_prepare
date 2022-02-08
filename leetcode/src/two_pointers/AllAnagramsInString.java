package two_pointers;

import java.util.ArrayList;
import java.util.List;

/**
 * 438. Find All Anagrams in a String
Medium

Given two strings s and p, return an array of all the start indices of p's anagrams in s. You may return the answer in any order.

An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

 

Example 1:

Input: s = "cbaebabacd", p = "abc"
Output: [0,6]
Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".
Example 2:

Input: s = "abab", p = "ab"
Output: [0,1,2]
Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".
 

Constraints:

1 <= s.length, p.length <= 3 * 104
s and p consist of lowercase English letters.

https://leetcode.com/problems/find-all-anagrams-in-a-string/
 */
public class AllAnagramsInString {
    
    /**
     * Idea - 2 pointers, similar to find pattern in string. However because it's of a fixed length, it's easy cause we know
     * how many letter to count back (left) from the current character.
     * Keep 2 arrays with char counts and number of unique letters that has same counts in both strings. 
     * When this both matches we found the anagram, save the left pointer. At the end decrement left pointer by one and adjust counters and
     * number of unique letters found
     * 
     * O(n) time, O(1) space (?)  
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList();        
        if (s.length() < p.length()) {
            return res;
        }
        //fill the counts for pattern string
        int[] pCounts = new int[26], sCounts = new int[26];
        int pLetters = 0, sLetters = 0;
        for (char ch : p.toCharArray()) {
            pCounts[ch - 'a']++;
            if (pCounts[ch - 'a'] == 1) {
                pLetters++;
            }
        }
        
        for (int i = 0; i < s.length(); i++) {
            int chIdx = s.charAt(i) - 'a';
            sCounts[chIdx]++;
            //increment count of unique same letters in both strings
            if (pCounts[chIdx] > 0 && sCounts[chIdx] == pCounts[chIdx]) {
                sLetters++;
            }  
            int leftPointer = i - p.length() + 1;
            if (pLetters == sLetters) {
                res.add(leftPointer);
            }
            //now decrement left pointer
            if (i >= p.length() - 1) {
                int leftChIdx = s.charAt(leftPointer) - 'a'; 
                sCounts[leftChIdx]--;
                //this is tricky - we decrement count of unique letters only in case left pointer
                //was pointing to the letter that is in pattern and count was the same.
                //catch - it's easy to decrement everytime
                if (pCounts[leftChIdx] > 0 && sCounts[leftChIdx] + 1 == pCounts[leftChIdx]) {
                    sLetters--;
                }
            }
        }
        return res;
    }
}
