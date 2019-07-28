import java.util.ArrayList;
import java.util.List;

/**
 * Find all permutations of the string
 */
public class PermutationsOfString {


    public static ArrayList<String> getCombPerms(String s) {

        if (s == null)
            return null;
        ArrayList<String> res = new ArrayList<String>();
        if (s.length() <= 1) {
            res.add(s);
            return res;
        }

        String start = "" + s.charAt(0);
        String right = s.substring(1);
        res.add(start);
        ArrayList<String> subWords = getCombPerms(right);
        for (String subStr : subWords) {
            for (int i = 0; i <= subStr.length(); i++) {
                res.add(subStr.substring(0, i) + start + subStr.substring(i));
            }
        }
        res.addAll(subWords);
        return res;
    }

    public static void main(String[] args) {
        List<String> perms = getCombPerms("abc");
        perms.forEach(i->System.out.print(i +" "));
    }
}
