package path.amazon;

import java.util.ArrayList;
import java.util.List;

public class LetterCombinationPhonePad {

    List<String> res;
    String digits;
    String[] maps = new String[] {
            "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
    };
    public List<String> letterCombinations(String digits) {
        res = new ArrayList();
        if (digits.length() == 0)
            return res;

        this.digits = digits;
        helper(0, new char[digits.length()]);
        return res;
    }

    void helper(int curIdx, char[] cur) {
        if (curIdx == cur.length) {
            res.add(new String(cur));
            return;
        }
        int digit = digits.charAt(curIdx) - '0';
        String mappedStr = maps[digit - 2];
        for (char ch : mappedStr.toCharArray()) {
            cur[curIdx] = ch;
            helper(curIdx + 1, cur);
        }
    }
}
