package random_problems;

/**
 * 28. Implement strStr()
 * Easy
 *
 * Implement strStr().
 *
 * Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 *
 * Example 1:
 *
 * Input: haystack = "hello", needle = "ll"
 * Output: 2
 * Example 2:
 *
 * Input: haystack = "aaaaa", needle = "bba"
 * Output: -1
 * Clarification:
 *
 * What should we return when needle is an empty string? This is a great question to ask during an interview.
 *
 * For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to C's
 * strstr() and Java's indexOf().
 *
 */
public class SubstringInString {

    /**
     * Use KMP algorithm for substring search - O(n) time
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        int hLen = haystack.length();
        int nLen = needle.length();

        if (nLen > hLen)
            return -1;

        //build KMP table
        int[] kmpTable = new int[nLen];

        int prev;
        for (int i = 1; i < nLen; i++) {
            prev = kmpTable[i - 1];
            while(prev > 0 && needle.charAt(i) != needle.charAt(prev)) {
                prev = kmpTable[prev - 1];
            }

            if (needle.charAt(i) == needle.charAt(prev))
                kmpTable[i] = prev + 1;
        }

        int idx = 0;
        boolean match;
        int dif = hLen - nLen;
        while (idx <= dif) {
            match = true;
            for (int j =0; j < nLen; j++) {
                if (haystack.charAt(j + idx) != needle.charAt(j)) {
                    match = false;
                    idx += (j > 0) ? (j - kmpTable[j - 1]) : 1;
                    break;
                }
            }
            if (match)
                return idx;
        }
        return -1;
    }

    public static void main(String[] args) {
        SubstringInString obj = new SubstringInString();
        System.out.println(obj.strStr("aabaabaababaa", "abab"));
        System.out.println(obj.strStr("hello", "ll"));
    }
}
