package path.google;

/**
 * 6. ZigZag Conversion
 * Medium
 *
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to
 * display this pattern in a fixed font for better legibility)
 *
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * And then read line by line: "PAHNAPLSIIGYIR"
 *
 * Write the code that will take a string and make this conversion given a number of rows:
 *
 * string convert(string s, int numRows);
 * Example 1:
 *
 * Input: s = "PAYPALISHIRING", numRows = 3
 * Output: "PAHNAPLSIIGYIR"
 * Example 2:
 *
 * Input: s = "PAYPALISHIRING", numRows = 4
 * Output: "PINALSIGYAHRPI"
 * Explanation:
 *
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 *
 */
public class ZigZagConversion {
    /**
     * Idea : going row by row. We have up to 2 chars to add on each iteration for one row - one is fixed distance
     * cycLen, another is smaller internal cycle for all rows except 0 and numRows - 1
     * @param s
     * @param numRows
     * @return
     */
    public String convert(String s, int numRows) {
        StringBuilder sb = new StringBuilder();
        int N = s.length();
        int start;
        int cycLen = (numRows > 1) ? (numRows * 2 - 2) : 1;
        int internCycLen;
        for (int r = 0; r < numRows; r++) {
            start = r;
            internCycLen = (((numRows - r) * 2) - 2);
            while (start < N) {
                sb.append(s.charAt(start));
                if (r > 0 && r < numRows - 1) {
                    int ss = start + internCycLen;
                    if (ss < N)
                        sb.append(s.charAt(ss));
                    else
                        break;
                }
                start += cycLen;
            }
        }
        return sb.toString();
    }
}
