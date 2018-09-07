package cracking.recursion_dp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Compute all possible permutations of a string, duplicates allowed in string
 */
public class PermutationsDuplString {
    List<String> getPerms(String s) {
        Map<Character, Integer> counts = new HashMap();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (!counts.containsKey(ch)) {
                counts.put(ch, 0);
            }
            counts.put(ch, counts.get(ch) + 1);
        }
        List<String> res = new ArrayList();
        helperCounter(counts, res, "", s.length());
        return res;
    }

    void helperCounter(Map<Character, Integer> counts, List<String> perms, String s, int charCount) {
        if (charCount == 0) {
            perms.add(s);
            return;
        }
        for (char c : counts.keySet()) {
            int count = counts.get(c);
            if (count > 0) {
                counts.put(c, count - 1);
                helperCounter(counts, perms, s + c, charCount - 1);
                counts.put(c, count);
            }
        }
    }

    public static void main(String[] args) {
        PermutationsDuplString obj = new PermutationsDuplString();
        List<String> perms = obj.getPerms("aab");
        for (String perm : perms) {
            System.out.println(perm +", ");
        }
    }
}
