import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Return all possible combinations of letters from numpad
 */
public class LetterCombinationsPhoneNumPad {

    /**
     * Doing DFS, collection combinations on each step
     * @param digits
     * @return
     */
    public static  ArrayList<String> getStringsFromNums(String digits) {
        if (digits == null)
            return null;
        ArrayList<String> res = new ArrayList<>();
        if (digits.length() == 0)
            return res;

        Map<Character, String> m = new HashMap<>();
        m.put('2', "abc");
        m.put('3', "def");
        m.put('4', "ghi");
        m.put('5', "jkl");
        m.put('6', "mno");
        m.put('7', "pqrs");
        m.put('8', "tuv");
        m.put('9', "wxyz");

        return helper(m, digits);
    }

    static ArrayList<String> helper(Map<Character, String> m, String dig) {
        ArrayList<String> res = new ArrayList<>();
        if (dig.length() == 0) {
            res.add("");
            return res;
        }

        char ch = dig.charAt(0);
        ArrayList<String> subList = helper(m, dig.substring(1));
        if (!m.containsKey(ch)) {
            return subList;
        }
        else {
            String nums = m.get(ch);
            for (char chD : nums.toCharArray()) {
                for (String subStr : subList) {
                    res.add(chD + subStr);
                }
            }
        }
        return res;
    }
}
