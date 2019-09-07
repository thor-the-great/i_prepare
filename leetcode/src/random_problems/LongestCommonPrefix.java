package random_problems;

/**
 * 14. Longest Common Prefix
 * Easy
 *
 * Write a function to find the longest common prefix string amongst an array of strings.
 *
 * If there is no common prefix, return an empty string "".
 *
 * Example 1:
 *
 * Input: ["flower","flow","flight"]
 * Output: "fl"
 * Example 2:
 *
 * Input: ["dog","racecar","car"]
 * Output: ""
 * Explanation: There is no common prefix among the input strings.
 * Note:
 *
 * All given inputs are in lowercase letters a-z.
 */
public class LongestCommonPrefix {

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0)
            return "";

        StringBuilder sb = new StringBuilder();
        sb.append(strs[0]);
        for (int j = 1; j < strs.length; j++) {
            String s = strs[j];
            int len = Math.min(s.length(), sb.length());
            sb.setLength(len);
            for (int i = 0; i < len; i++) {
                if (s.charAt(i) != sb.charAt(i)) {
                    sb.setLength(i);
                    break;
                }
            }
            if (sb.length() == 0)
                break;
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        LongestCommonPrefix obj = new LongestCommonPrefix();
        String[] arr = new String[] {"flower", "floor", "flight", "fame"};
        System.out.println(obj.longestCommonPrefix(arr));

        System.out.println(obj.longestCommonPrefix(
            new String[] {"dog", "doggy", "do"}));

        System.out.println(obj.longestCommonPrefix(
            new String[] {"dog", "dog"}));
    }
}
