package strings;

/**
 * 58. Length of Last Word
Easy

Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word (last word means the last appearing word if we loop from left to right) in the string.

If the last word does not exist, return 0.

Note: A word is defined as a maximal substring consisting of non-space characters only.

Example:

Input: "Hello World"
Output: 5

https://leetcode.com/problems/length-of-last-word/
 */
public class LengthOfLastWord {

    /**
     * Go fron the end of the string, count position of firrst non-space from the end and position of next space after.
     * @param s
     * @return
     */
    public int lengthOfLastWord(String s) {
        if (s == null || s.isEmpty())
            return 0;
        
        int i = s.length() - 1;
        while (i >= 0 && s.charAt(i) == ' ')
            --i;
        //if all chars are spaces
        if (i == -1)
            return 0;
        //save the postion of the first non-space
        int lastNonSpace = i;
        for (; i >= 0; i--) {
            //met the space after some charaters - this is "trivial" case, return the length of last word
            if (s.charAt(i) == ' ') {
                return lastNonSpace - i;
            }
        }
        //here we reached the begining of the string and no spaces found, there is only one word in string
        return lastNonSpace + 1;
    }
}
