import java.util.ArrayList;

/**
 * Find all permutations of the string
 */
public class PermutationsOfString {


    public static ArrayList<String> getPermutations(String s) {
        if ( s == null)
            return null;

        ArrayList<String> list = new ArrayList();
        if ("".equals(s)) {
            list.add("");
            return list;
        }

        char f = s.charAt(0);
        String subS = s.substring(1);
        ArrayList<String> subResults = getPermutations(subS);
        for (String subResult : subResults) {
            for (int i = 0; i <= subResult.length(); i++) {
                String res = subResult.substring(0, i) + f + subResult.substring(i, subResult.length());
                list.add(res);
            }
        }


        return list;
    }
}
