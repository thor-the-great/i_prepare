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

    Map<String, List<Double>> edges;
    Map<String, List<String>> vertexes;

    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        int N = equations.length;
        if ( N == 0)
            return new double[0];

        edges = new HashMap();
        vertexes = new HashMap();

        for (int i = 0; i < N; i++) {
            String[] eq = equations[i];
            addVertex(eq[0], eq[1]);
            addVertex(eq[1], eq[0]);

            double val = values[i];
            addEdge(eq[0], val);
            addEdge(eq[1], 1/val);
        }

        Set<String> visited = new HashSet();
        int Q = queries.length;
        double[] res = new double[Q];
        for (int q = 0; q < Q; q++) {
            String[] query = queries[q];
            visited.clear();
            double x = dfs(query[0], query[1], visited, 1.0);
            res[q] = x == 0.0 ? -1.0 : x;
        }
        return res;
    }

    double dfs(String cur, String to, Set<String> visited, double val) {
        if (visited.contains(cur)  || !vertexes.containsKey(to))
            return 0.0;

        if (cur.equals(to))
            return val;

        visited.add(cur);
        List<String> adjVs = vertexes.get(cur);
        if (adjVs != null && !adjVs.isEmpty()) {
            Iterator<Double> edgesIt = edges.get(cur).iterator();
            for (String adjV : adjVs) {
                double valEdge = edgesIt.next();
                double res = dfs(adjV, to, visited, val * valEdge);
                if (res != 0.0)
                    return res;
            }
        }
        return 0.0;
    }

    void addVertex(String from, String to) {
        if (vertexes.containsKey(from)) {
            vertexes.get(from).add(to);
        } else {
            List<String> adj = new ArrayList();
            adj.add(to);
            vertexes.put(from, adj);
        }
    }

    void addEdge(String from, double val) {
        if (edges.containsKey(from)) {
            edges.get(from).add(val);
        } else {
            List<Double> adj = new ArrayList();
            adj.add(val);
            edges.put(from, adj);
        }
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
