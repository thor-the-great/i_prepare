package cracking.recursion_dp;

import java.util.ArrayList;
import java.util.List;

/**
 * Compute all possible permutations of a string, no duplicates in string
 */
public class PermutationsNonDupString {

    List<String> getAllPermutations(String s) {
        List<String> res = new ArrayList<>();
        if (s.isEmpty()) {
            res.add("");
            return res;
        }
        //remove first char
        char firstCh = s.charAt(0);
        String cutString = s.substring(1);
        List<String> subPerms = getAllPermutations(cutString);
        for (String subWord : subPerms) {
            for (int i = 0; i <= subWord.length(); i++) {
                res.add(insertChar(subWord, firstCh, i));
            }
        }
        return res;
    }
    String insertChar(String s, char ch, int pos) {
        StringBuilder sb = new StringBuilder(s);
        sb.insert(pos, ch);
        return sb.toString();
    }

    public static void main(String[] args) {
        PermutationsNonDupString obj = new PermutationsNonDupString();
        List<String> perms = obj.getAllPermutations("123");
        for (String perm : perms) {
            System.out.println(perm +", ");
        }
    }
}
