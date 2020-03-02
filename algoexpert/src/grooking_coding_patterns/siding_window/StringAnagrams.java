package grooking_coding_patterns.siding_window;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem Challenge 2
 * We'll cover the following
 * String Anagrams (hard)
 * Try it yourself
 * String Anagrams (hard) #
 * Given a string and a pattern, find all anagrams of the pattern in the given string.
 *
 * Anagram is actually a Permutation of a string. For example, “abc” has the following six anagrams:
 *
 * abc
 * acb
 * bac
 * bca
 * cab
 * cba
 * Write a function to return a list of starting indices of the anagrams of the pattern in the given string.
 *
 * Example 1:
 *
 * Input: String="ppqp", Pattern="pq"
 * Output: [1, 2]
 * Explanation: The two anagrams of the pattern in the given string are "pq" and "qp".
 * Example 2:
 *
 * Input: String="abbcabc", Pattern="abc"
 * Output: [2, 3, 4]
 * Explanation: The three anagrams of the pattern in the given string are "bca", "cab", and "abc".
 */
public class StringAnagrams {

    public static List<Integer> findStringAnagrams(String str, String pattern) {
        List<Integer> resultIndices = new ArrayList<Integer>();
        int[] count = new int[128]; int letters = 0;

        for (char ch : pattern.toCharArray()) {
            ++count[ch];
            if (count[ch] == 1)
                ++letters;
        }

        int l = 0, lletters = 0;
        int[] ccount = new int[128];

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            ++ccount[ch];
            if (ccount[ch] == count[ch]) {
                ++lletters;
            }

            while(ccount[ch] > count[ch]) {
                int ch2 = str.charAt(l);
                --ccount[ch2];
                if (ccount[ch2] < count[ch2])
                    --lletters;
                ++l;
            }

            if (lletters == letters) {
                resultIndices.add(l);
            }
        }
        return resultIndices;
    }
}