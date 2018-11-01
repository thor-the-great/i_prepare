package mock_sessions.amazon;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class LetterCombinationPhonePad {

    public List<String> letterCombinations(String digits) {
        List<String> res = new LinkedList();
        if (digits == null || digits.isEmpty()) return res;

        char[][] map = new char[][] {
                {},
                {},
                {'a', 'b', 'c'},
                {'d', 'e', 'f'},
                {'g', 'h', 'i'},
                {'j', 'k', 'l'},
                {'m', 'n', 'o'},
                {'p', 'q', 'r', 's'},
                {'t', 'u', 'v'},
                {'w', 'x', 'y', 'z'}
        };

        helper(digits, 0, "", res, map);
        return res;
    }

    private void helper(String s, int index, String sSoFar, List<String> res, char[][] map) {
        if (index >= s.length()) {
            res.add(sSoFar);
            return;
        }

        char nextDigit = s.charAt(index);
        char[] mapping = map[nextDigit - '0'];
        for (char nextMapping : mapping) {
            helper(s, index + 1, sSoFar + nextMapping, res, map);
        }
    }
}
