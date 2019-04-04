package pramp;

/**
 * Smallest Substring of All Characters
 * Given an array of unique characters arr and a string str, Implement a function getShortestUniqueSubstring that
 * finds the smallest substring of str containing all the characters in arr. Return "" (empty string) if such a
 * substring doesn’t exist.
 *
 * Come up with an asymptotically optimal solution and analyze the time and space complexities.
 *
 * Example:
 *
 * input:  arr = ['x','y','z'], str = "xyyzyzyx"
 *
 * output: "zyx"
 * Constraints:
 *
 * [time limit] 5000ms
 *
 * [input] array.character arr
 *
 * 1 ≤ arr.length ≤ 30
 * [input] string str
 *
 * 1 ≤ str.length ≤ 500
 * [output] string
 */
public class SmallestSubstringAllCharacters {

    static String getShortestUniqueSubstring(char[] arr, String str) {
        // your code goes here
        int N = arr.length;
        if (N == 0)
            return "";

        int templateCount[] = new int[128];

        for (char ch : arr)
            templateCount[ch]++;

        int[] counts = new int[128];
        int[] res = new int[] {-1, -1, Integer.MAX_VALUE};
        int count = 0;
        int left = 0;
        for (int i =0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (templateCount[ch] > 0) {
                counts[ch]++;
                if (counts[ch] == 1)
                    count++;

                if (count == N) {
                    while (count == N && left <= i) {
                        int len = i - left + 1;
                        if (res[2] > len) {
                            res[0] = left;
                            res[1] = i;
                            res[2] = len;
                        }
                        char leftCh = str.charAt(left);
                        if (templateCount[leftCh] > 0) {
                            counts[leftCh]--;
                            if (counts[leftCh] < 1)
                                count--;
                        }
                        left++;
                    }
                }
            }
        }

        if (res[2] == Integer.MAX_VALUE)
            return "";

        return str.substring(res[0], res[1] + 1);
    }
}
