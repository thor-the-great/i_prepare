package strings;

/**
 * 917. Reverse Only Letters
Easy

Given a string s, reverse the string according to the following rules:

    All the characters that are not English letters remain in the same position.
    All the English letters (lowercase or uppercase) should be reversed.

Return s after reversing it.

 

Example 1:

Input: s = "ab-cd"
Output: "dc-ba"

Example 2:

Input: s = "a-bC-dEf-ghIj"
Output: "j-Ih-gfE-dCba"

Example 3:

Input: s = "Test1ng-Leet=code-Q!"
Output: "Qedo1ct-eeLg=ntse-T!"

 

Constraints:

    1 <= s.length <= 100
    s consists of characters with ASCII values in the range [33, 122].
    s does not contain '\"' or '\\'.

https://leetcode.com/problems/reverse-only-letters/
 */
public class ReverseOnlyLetters {
    /**
     * Two pointers - going from left to right and from right to left. Check if both are 
     * letters  do exchange. If only one is letter just move the pointer 
     */
    public String reverseOnlyLetters(String s) {
        char[] strChar = s.toCharArray();
        int l = 0, r = s.length() - 1;
        
        while (l < r) {
            if (isLet(strChar[l]) && isLet(strChar[r])) {
                char t = strChar[l];
                strChar[l] = strChar[r];
                strChar[r] = t;
                l++; r--;
            } else { 
                if (!isLet(strChar[l])) {
                    l++;
                }
                if (!isLet(strChar[r])) {
                    r--;
                }
            }
        }
        
        return new String(strChar);
    }
    
    boolean isLet(char ch ) {
        return ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z'));
    }
}
