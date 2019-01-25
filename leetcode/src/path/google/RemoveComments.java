package path.google;

import java.util.ArrayList;
import java.util.List;

/**
 * 722. Remove Comments
 * Medium
 *
 * Given a C++ program, remove comments from it. The program source is an array where source[i] is the i-th line of the
 * source code. This represents the result of splitting the original source code string by the newline character \n.
 *
 * After removing the comments from the source code, return the source code in the same format.
 */
public class RemoveComments {

    public List<String> removeComments(String[] source) {
        List<String> res = new ArrayList();
        int N = source.length;
        if (N == 0)
            return res;
        StringBuilder sb = new StringBuilder();
        boolean blockOpened = false;
        for (String s : source) {
            int i = 0;
            if (!blockOpened)
                sb.setLength(0);
            char[] sArray = s.toCharArray();
            int n = sArray.length;
            while (i < n) {
                if (!blockOpened && i + 1 < n && sArray[i] == '/' && sArray[i + 1] == '*') {
                    blockOpened = true;
                    i++;
                } else if (blockOpened && i + 1 < n && sArray[i] == '*' && sArray[i + 1] == '/') {
                    blockOpened = false;
                    i++;
                } else if (!blockOpened && i + 1 < n && sArray[i] == '/' && sArray[i + 1] == '/') {
                    break;
                } else if (!blockOpened) {
                    sb.append(sArray[i]);
                }
                i++;
            }
            if (!blockOpened && sb.length() > 0)
                res.add(sb.toString());
        }
        return res;
    }
}
