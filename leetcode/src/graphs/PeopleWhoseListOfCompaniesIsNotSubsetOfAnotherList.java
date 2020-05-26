package graphs;

import java.util.*;

/**
 * 1452. People Whose List of Favorite Companies Is Not a Subset of Another List
 * Medium
 *
 * Given the array favoriteCompanies where favoriteCompanies[i] is the list of favorites companies for the
 * ith person (indexed from 0).
 *
 * Return the indices of people whose list of favorite companies is not a subset of any other list of
 * favorites companies. You must return the indices in increasing order.
 *
 *
 *
 * Example 1:
 *
 * Input: favoriteCompanies = [["leetcode","google","facebook"],["google","microsoft"],["google","facebook"],
 * ["google"],["amazon"]]
 * Output: [0,1,4]
 * Explanation:
 * Person with index=2 has favoriteCompanies[2]=["google","facebook"] which is a subset of favoriteCompanies[0]=["leetcode","google","facebook"] corresponding to the person with index 0.
 * Person with index=3 has favoriteCompanies[3]=["google"] which is a subset of favoriteCompanies[0]=["leetcode","google","facebook"] and favoriteCompanies[1]=["google","microsoft"].
 * Other lists of favorite companies are not a subset of another list, therefore, the answer is [0,1,4].
 * Example 2:
 *
 * Input: favoriteCompanies = [["leetcode","google","facebook"],["leetcode","amazon"],["facebook","google"]]
 * Output: [0,1]
 * Explanation: In this case favoriteCompanies[2]=["facebook","google"] is a subset of favoriteCompanies[0]=["leetcode","google","facebook"], therefore, the answer is [0,1].
 * Example 3:
 *
 * Input: favoriteCompanies = [["leetcode"],["google"],["facebook"],["amazon"]]
 * Output: [0,1,2,3]
 *
 *
 * Constraints:
 *
 * 1 <= favoriteCompanies.length <= 100
 * 1 <= favoriteCompanies[i].length <= 500
 * 1 <= favoriteCompanies[i][j].length <= 20
 * All strings in favoriteCompanies[i] are distinct.
 * All lists of favorite companies are distinct, that is, If we sort alphabetically each list then
 * favoriteCompanies[i] != favoriteCompanies[j].
 * All strings consist of lowercase English letters only.
 */
public class PeopleWhoseListOfCompaniesIsNotSubsetOfAnotherList {

  /**
   * Build graph where each person's list is the vertex, and if one list is a subset of another
   * one - we do union so the bigger list is the root. In case of UF with compressed path
   * the root will always be such list. So enumeration those roots will give answer
   * to check for subset we use Set.containsAll
   * @param favoriteCompanies
   * @return
   */
  public List<Integer> peopleIndexes(List<List<String>> favoriteCompanies) {
    List<Set<String>> companiesSets = new ArrayList();
    for (List<String> companyList : favoriteCompanies) {
      companiesSets.add(new HashSet(companyList));
    }

    int[] indexes = new int[favoriteCompanies.size()];
    for (int i = 0; i < indexes.length; i++)
      indexes[i] = i;

    for (int i = 0; i < indexes.length; i++) {
      for (int j = i + 1; j < indexes.length; j++) {
        int s1 = find(indexes, i);
        int s2 = find(indexes, j);
        if (s1 == s2)
          continue;
        if (contains(companiesSets.get(s1), companiesSets.get(s2))) {
          indexes[s2] = s1;
        } else if (contains(companiesSets.get(s2), companiesSets.get(s1))) {
          indexes[s1] = s2;
        }
      }
    }

    List<Integer> res = new LinkedList<>();
    for (int i = 0; i < indexes.length; i++) {
      if (i == indexes[i])
        res.add(i);
    }
    return res;
  }

  int find(int[] indexes, int x) {
    if (indexes[x] != x)
      indexes[x] = find(indexes, indexes[x]);
    return indexes[x];
  }

  boolean contains(Set<String> s1, Set<String> s2) {
    if (s1.size() <= s2.size())
      return false;
    return s1.containsAll(s2);
  }
}
