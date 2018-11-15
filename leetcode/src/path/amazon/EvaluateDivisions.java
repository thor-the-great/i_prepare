package path.amazon;

import java.util.*;

/**
 * 399. Evaluate Division
 * Medium
 *
 * Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is a real
 * number (floating point number). Given some queries, return the answers. If the answer does not exist, return -1.0.
 *
 * Example:
 * Given a / b = 2.0, b / c = 3.0.
 * queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
 * return [6.0, 0.5, -1.0, 1.0, -1.0 ].
 *
 * The input is: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries ,
 * where equations.size() == values.size(), and the values are positive. This represents the equations. Return
 * vector<double>.
 *
 * According to the example above:
 *
 * equations = [ ["a", "b"], ["b", "c"] ],
 * values = [2.0, 3.0],
 * queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].
 * The input is always valid. You may assume that evaluating the queries will result in no division by zero and there
 * is no contradiction.
 *
 */
public class EvaluateDivisions {

    Map<String, List<String>> vertexes = new HashMap();
    Map<String, List<Double>> edges = new HashMap();

    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        vertexes.clear();
        edges.clear();
        //build graph - vertexes contains links between variables and edges - result of division
        for (int i = 0; i < equations.length; i++) {
            String[] eq = equations[i];
            double val = values[i];
            vertexes.putIfAbsent(eq[0], new ArrayList());
            vertexes.get(eq[0]).add(eq[1]);
            vertexes.putIfAbsent(eq[1], new ArrayList());
            vertexes.get(eq[1]).add(eq[0]);

            edges.putIfAbsent(eq[0], new ArrayList());
            edges.get(eq[0]).add(val);
            edges.putIfAbsent(eq[1], new ArrayList());
            edges.get(eq[1]).add(1/val);
        }

        double[] res = new double[queries.length];
        Set<String> visited = new HashSet();
        for (int i = 0; i < queries.length; i++) {
            visited.clear();
            String[] q = queries[i];
            double sol = dfs(q[0], q[1], visited, 1.0);
            res[i] = sol == 0.0 ? -1.0 : sol;
        }
        return res;
    }

    double dfs(String a, String b, Set<String> visited, double valueSoFar) {
        if (visited.contains(a) || !vertexes.containsKey(a))
            return 0.0;
        if (a.equals(b)) return valueSoFar;
        visited.add(a);

        List<String> adjVertexes = vertexes.get(a);
        List<Double> adjValues = edges.get(a);
        double res = 0.0;
        for (int i =0; i < adjVertexes.size(); i++) {
            String nextV = adjVertexes.get(i);
            double nextValue = adjValues.get(i);
            res = dfs(nextV, b, visited, valueSoFar * nextValue);
            if (res != 0.0)
                return res;
        }
        visited.remove(a);
        return 0.0;
    }

    public static void main(String[] args) {
        EvaluateDivisions obj = new EvaluateDivisions();
        String[][] equations = new String[][] {
                {"a", "b"}, {"b", "c"}
        };
        double[] values = new double[] { 2.0, 3.0 };
        String[][] q = new String[][] {
                {"a", "c"}, {"b", "a"}, {"a", "e"}, {"a", "a"}, {"x", "x"}
        };
        double[] res = obj.calcEquation(equations, values, q);
        Arrays.stream(res).forEach(i->System.out.print(i + " ")); //[6.0,0.5,-1.0,1.0,-1.0]
    }
}
