package map;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 1436. Destination City
 * Easy
 *
 * You are given the array paths, where paths[i] = [cityAi, cityBi] means there exists a direct
 * path going from cityAi to cityBi. Return the destination city, that is, the city without any
 * path outgoing to another city.
 *
 * It is guaranteed that the graph of paths forms a line without any loop, therefore, there will
 * be exactly one destination city.
 *
 *
 *
 * Example 1:
 *
 * Input: paths = [["London","New York"],["New York","Lima"],["Lima","Sao Paulo"]]
 * Output: "Sao Paulo"
 * Explanation: Starting at "London" city you will reach "Sao Paulo" city which is the
 * destination city. Your trip consist of: "London" -> "New York" -> "Lima" -> "Sao Paulo".
 * Example 2:
 *
 * Input: paths = [["B","C"],["D","B"],["C","A"]]
 * Output: "A"
 * Explanation: All possible trips are:
 * "D" -> "B" -> "C" -> "A".
 * "B" -> "C" -> "A".
 * "C" -> "A".
 * "A".
 * Clearly the destination city is "A".
 * Example 3:
 *
 * Input: paths = [["A","Z"]]
 * Output: "Z"
 *
 *
 * Constraints:
 *
 * 1 <= paths.length <= 100
 * paths[i].length == 2
 * 1 <= cityAi.length, cityBi.length <= 10
 * cityAi != cityBi
 * All strings consist of lowercase and uppercase English letters and the space character.
 */
public class DestinationCity {

  /**
   * Need to count outDegree for every city. One with 0 is our answer.
   * Use map <city, hasOutdegree>
   * @param paths
   * @return
   */
  public String destCity(List<List<String>> paths) {
    Map<String, Boolean> outDeg = new HashMap();
    for (List<String> pair : paths) {
      String from = pair.get(0), to = pair.get(1);
      //one that has outgoing edge cannot be the answer, mark it with false
      outDeg.put(from, false);
      //one that is destination in this pair is potential answer. But only if it does not have
      //out degree, meaning hasn't marked with false flag
      if (!outDeg.containsKey(to))
        outDeg.put(to, true);
    }
    //checking every value unless find one with the true flag (out degree == 0)
    for (Map.Entry<String, Boolean> entry : outDeg.entrySet()) {
      if (entry.getValue())
        return entry.getKey();
    }

    return null;
  }
}
