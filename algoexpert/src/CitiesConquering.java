import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Once upon a time, in a kingdom far, far away, there lived a King Byteasar VII. Since he
 * reigned during the Dark Times, very little is known about his reign. It is known that when he
 * ascended the throne, there were n cities in the kingdom, connected by several two-way roads.
 * But before the young king managed to start ruling, an enemy arrived at the gates: the evil
 * emperor Bugoleon invaded the kingdom and started to conquer the cities, advancing day after day.
 *
 * It is not known how long it took to capture each of the cities, but you've recently discovered
 * an ancient manuscript describing that each day, Bugoleon captured all the cities that had at
 * most 1 neighboring free city at that given moment. Knowing this fact, the number of cities n
 * and all the roads of the kingdom, determine in how many days each of the cities was conquered.
 *
 * Example
 *
 * For n = 10 and
 *
 * roads = [[1, 0], [1, 2], [8, 5], [9, 7],
 *          [5, 6], [5, 4], [4, 6], [6, 7]]
 * the output should be
 * citiesConquering(n, roads) = [1, 2, 1, 1, -1, -1, -1, 2, 1, 1].
 *
 * Here's how the cities were conquered:
 *
 *
 * Input/Output
 *
 * [execution time limit] 3 seconds (java)
 *
 * [input] integer n
 *
 * The number of cities in the kingdom.
 *
 * Guaranteed constraints:
 * 1 ≤ n ≤ 100.
 *
 * [input] array.array.integer roads
 *
 * Array of two-way roads, where each road is given in the format [city1, city2], meaning that
 * city1 and city2 are connected. It is guaranteed that there is at most one road between two
 * cities, and no road is given twice.
 *
 * Guaranteed constraints:
 * 0 ≤ roads.length ≤ n · (n - 1) / 2,
 * roads[i].length = 2,
 * roads[i][0] ≠ roads[i][1],
 * 0 ≤ roads[i][j] < n.
 *
 * [output] array.integer
 *
 * An array of length n, there the ith element is the number of days it took to conquer the ith
 * city, or -1 if the city wasn't conquered.
 */
public class CitiesConquering {

    int[] citiesConquering(int n, int[][] roads) {
        Set<Integer>[] g = new HashSet[n];
        for (int i = 0; i < n; i++)
            g[i] = new HashSet();

        for (int[] r : roads) {
            g[r[0]].add(r[1]);
            g[r[1]].add(r[0]);
        }

        int[] res = new int[n];
        Arrays.fill(res, -1);

        boolean found = true;
        int d = 0;
        while(found) {
            ++d;
            found = false;
            List<Integer> conqCities = new ArrayList();
            for (int i = 0; i < n; i++) {
                if (res[i] > 0) continue;
                if (g[i].size() < 2) {
                    conqCities.add(i);
                }
            }
            if (!conqCities.isEmpty()) {
                found = true;
                for (Integer city : conqCities) {
                    //mark the day for city
                    res[city] = d;
                    //take care of connected city
                    if (!g[city].isEmpty()) {
                        Integer a = g[city].iterator().next();
                        g[a].remove(city);
                    }
                    //mark city as taken
                    g[city].clear();
                }
            }
        }
        return res;
    }

}
