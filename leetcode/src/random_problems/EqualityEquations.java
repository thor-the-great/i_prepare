package random_problems;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 990. Satisfiability of Equality Equations
 * Medium
 *
 * Given an array equations of strings that represent relationships between variables, each string equations[i] has
 * length 4 and takes one of two different forms: "a==b" or "a!=b".  Here, a and b are lowercase letters (not
 * necessarily different) that represent one-letter variable names.
 *
 * Return true if and only if it is possible to assign integers to variable names so as to satisfy all the given
 * equations.
 *
 *
 *
 * Example 1:
 *
 * Input: ["a==b","b!=a"]
 * Output: false
 * Explanation: If we assign say, a = 1 and b = 1, then the first equation is satisfied, but not the second.  There is
 * no way to assign the variables to satisfy both equations.
 * Example 2:
 *
 * Input: ["b==a","a==b"]
 * Output: true
 * Explanation: We could assign a = 1 and b = 1 to satisfy both equations.
 * Example 3:
 *
 * Input: ["a==b","b==c","a==c"]
 * Output: true
 * Example 4:
 *
 * Input: ["a==b","b!=c","c==a"]
 * Output: false
 * Example 5:
 *
 * Input: ["c==c","b==d","x!=z"]
 * Output: true
 *
 *
 * Note:
 *
 * 1 <= equations.length <= 500
 * equations[i].length == 4
 * equations[i][0] and equations[i][3] are lowercase letters
 * equations[i][1] is either '=' or '!'
 * equations[i][2] is '='
 *
 */
public class EqualityEquations {

    /**
     * Idea: create unweighted graph out of equations for pairs that are equals. Then "color" vertexes as per equality
     * so each transitive tuple of variables are the same color.
     * Then checking equations, if they are unequal but of the same color - satisfiabylity is not possible.
     * @param equations
     * @return
     */
    public boolean equationsPossible(String[] equations) {
        ArrayList<Integer>[] g = new ArrayList[26];
        int[] colors = new int[26];

        //create graph
        for (String eq : equations) {
            int v1 = eq.charAt(0) - 'a';
            int v2 = eq.charAt(3) - 'a';

            if (eq.charAt(1) == '=') {
                if (g[v1] == null) {
                    g[v1] = new ArrayList();
                }
                g[v1].add(v2);

                if (g[v2] == null) {
                    g[v2] = new ArrayList();
                }
                g[v2].add(v1);
            }
        }

        //color graph vertexes
        int c = 1;
        for (int v = 0; v < 26; v++) {
            if (colors[v] == 0) {
                Queue<Integer> q = new LinkedList();
                q.add(v);
                while (!q.isEmpty()) {
                    int vv = q.poll();
                    if (g[vv] != null) {
                        for (int adj : g[vv]) {
                            if (colors[adj] == 0) {
                                colors[adj] = c;
                                q.add(adj);
                            }
                        }
                    }
                }
                c++;
            }
        }

        //check equation expressions
        for (String eq : equations) {

            if (eq.charAt(1) == '!') {
                int v1 = eq.charAt(0) - 'a';
                int v2 = eq.charAt(3) - 'a';

                if (v1 == v2 || (colors[v1] != 0 && colors[v1] == colors[v2]))
                    return false;
            }
        }

        return true;
    }
}
