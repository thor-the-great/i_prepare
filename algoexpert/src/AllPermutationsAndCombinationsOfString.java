import java.util.ArrayList;
import java.util.List;

/**
 * Combinations and Permutations New
 *     Recursion
 *
 * Given a string, list all possible combinations and permutations of its characters.
 *
 * Examples:
 * getCombPerms("a") ==> {"a"}
 *
 * getCombPerms("ab") ==> {"a","ab","ba","b"}
 */
public class AllPermutationsAndCombinationsOfString {

  public static ArrayList<String> getCombPerms(String s) {
    ArrayList<String> res = new ArrayList<String>();
    if (s == null)
      return res;

    return helper(s);
  }

  static ArrayList<String> helper(String s) {
    ArrayList<String> res = new ArrayList();
    if (s == null)
      return new ArrayList();

    if (s.length() == 1) {
      res.add(s);
      return res;
    }

    String sub = s.substring(1);
    char ch = s.charAt(0);
    res.add(""+ch);
    ArrayList<String> subWords = helper(sub);

    for (String subWord : subWords) {
      for (int i = 0; i <= subWord.length(); i++) {
        res.add(subWord.substring(0, i) + ch + subWord.substring(i));
      }
    }
    res.addAll(subWords);
    return res;
  }

  public static void main(String[] args) {
    List<String> res = getCombPerms("abc");
    res.forEach(s->System.out.print(s + " "));
  }
}
