package random_problems;

import java.util.Map;
import java.util.TreeMap;

/**
 * 726. Number of Atoms
 * Hard
 *
 * Given a chemical formula (given as a string), return the count of each atom.
 *
 * An atomic element always starts with an uppercase character, then zero or more lowercase letters, representing
 * the name.
 *
 * 1 or more digits representing the count of that element may follow if the count is greater than 1. If the count
 * is 1, no digits will follow. For example, H2O and H2O2 are possible, but H1O2 is impossible.
 *
 * Two formulas concatenated together produce another formula. For example, H2O2He3Mg4 is also a formula.
 *
 * A formula placed in parentheses, and a count (optionally added) is also a formula. For example, (H2O2) and
 * (H2O2)3 are formulas.
 *
 * Given a formula, output the count of all elements as a string in the following form: the first name (in sorted
 * order), followed by its count (if that count is more than 1), followed by the second name (in sorted order),
 * followed by its count (if that count is more than 1), and so on.
 *
 * Example 1:
 * Input:
 * formula = "H2O"
 * Output: "H2O"
 * Explanation:
 * The count of elements are {'H': 2, 'O': 1}.
 * Example 2:
 * Input:
 * formula = "Mg(OH)2"
 * Output: "H2MgO2"
 * Explanation:
 * The count of elements are {'H': 2, 'Mg': 1, 'O': 2}.
 * Example 3:
 * Input:
 * formula = "K4(ON(SO3)2)2"
 * Output: "K4N2O14S4"
 * Explanation:
 * The count of elements are {'K': 4, 'N': 2, 'O': 14, 'S': 4}.
 * Note:
 *
 * All atom names consist of lowercase letters, except for the first character which is uppercase.
 * The length of formula will be in the range [1, 1000].
 * formula will only consist of letters, digits, and round parentheses, and is a valid formula as defined in the problem.
 *
 */
public class NumberOfAtoms {

    String formula;
    int p;

    /**
     * Idea: just iterate and parse groups of atoms. For each nested group start new recursion iteration.
     * Keep global pointer as counter
     * @param formula
     * @return
     */
    public String countOfAtoms(String formula) {
        this.formula = formula;
        StringBuilder sb = new StringBuilder();
        p = 0;
        Map<String, Integer> counts = helper();
        for (String key : counts.keySet()) {
            sb.append(key);
            int count = counts.get(key);
            if (count > 1)
                sb.append(count);
        }
        return sb.toString();
    }

    Map<String, Integer> helper() {
        Map<String, Integer> count = new TreeMap();
        int N = formula.length();

        while (p < N) {
            if (formula.charAt(p) == ')')
                break;
            if (formula.charAt(p) == '(') {
                p++;
                Map<String, Integer> nested = helper();
                for (String nestedKey : nested.keySet()) {
                    if (count.containsKey(nestedKey)) {
                        count.put(nestedKey, count.get(nestedKey) + nested.get(nestedKey));
                    } else {
                        count.put(nestedKey, nested.get(nestedKey));
                    }
                }
            } else {
                int s = p++;
                while (p < N && Character.isLowerCase(formula.charAt(p))) p++;
                String name = formula.substring(s, p);
                s = p;
                while (p < N && Character.isDigit(formula.charAt(p))) p++;
                int mult = p > s ? Integer.parseInt(formula.substring(s, p)) : 1;
                if (count.containsKey(name))
                    count.put(name, count.get(name) + mult);
                else
                    count.put(name, mult);
            }
        }
        int s = ++p;
        while (p < N && Character.isDigit(formula.charAt(p))) p++;
        if (p > s) {
            int mult = Integer.parseInt(formula.substring(s, p));
            for (String key : count.keySet()) {
                count.put(key, count.get(key) * mult);
            }
        }

        return count;
    }
}
