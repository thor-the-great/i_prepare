package path.google;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 482. License Key Formatting
 * Easy
 *
 *
 * You are given a license key represented as a string S which consists only alphanumeric character and dashes. The
 * string is separated into N+1 groups by N dashes.
 *
 * Given a number K, we would want to reformat the strings such that each group contains exactly K characters, except
 * for the first group which could be shorter than K, but still must contain at least one character. Furthermore, there
 * must be a dash inserted between two groups and all lowercase letters should be converted to uppercase.
 *
 * Given a non-empty string S and a number K, format the string according to the rules described above.
 *
 * Example 1:
 * Input: S = "5F3Z-2e-9-w", K = 4
 *
 * Output: "5F3Z-2E9W"
 *
 * Explanation: The string S has been split into two parts, each part has 4 characters.
 * Note that the two extra dashes are not needed and can be removed.
 * Example 2:
 * Input: S = "2-5g-3-J", K = 2
 *
 * Output: "2-5G-3J"
 *
 * Explanation: The string S has been split into three parts, each part has 2 characters except the first part as it
 * could be shorter as mentioned above.
 * Note:
 * The length of string S will not exceed 12,000, and K is a positive integer.
 * String S consists only of alphanumerical characters (a-z and/or A-Z and/or 0-9) and dashes(-).
 * String S is non-empty.
 *
 */
public class LicenseKeyFormatting {

    public String licenseKeyFormattingStackRevert(String S, int K) {
        Stack<Character> s = new Stack();
        for (int i = 0; i < S.length(); i++) {
            char ch = S.charAt(i);
            if (ch != '-') {
                s.push(Character.toUpperCase(ch));
            }
        }
        int dashCount = s.size() / K - (s.size() % K == 0 ? 1 : 0);
        dashCount = dashCount < 0 ? 0 : dashCount;
        char[] arr = new char[dashCount + s.size()];
        int i = 0;
        int c = 0;
        while (!s.isEmpty()) {
            if (c == K) {
                arr[i] = '-';
                i++;
                c = 0;
            }
            arr[i] = s.pop();
            c++;
            i++;
        }
        revert(arr);
        return new String(arr);
    }

    void revert(char[] arr) {
        int l = 0, r = arr.length - 1;
        while (l < r) {
            char t = arr[l];
            arr[l] = arr[r];
            arr[r] = t;
            r--;
            l++;
        }
    }

    public String licenseKeyFormattingQueue(String S, int K) {
        Queue<Character> q = new LinkedList();
        for (int i = 0; i < S.length(); i++) {
            char ch = S.charAt(i);
            if (ch != '-') {
                q.add(Character.toUpperCase(ch));
            }
        }

        StringBuilder sb = new StringBuilder();
        int s = q.size() % K;
        while(s > 0) {
            sb.append(q.poll());
            s--;
        }
        if (sb.length() > 0 && !q.isEmpty())
            sb.append('-');
        int c = 0;
        while(!q.isEmpty()) {
            if (c == K) {
                sb.append('-');
                c = 0;
            }
            sb.append(q.poll());
            c++;
        }
        return sb.toString();
    }
}
