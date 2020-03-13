/**
 * Copyright (c) 2019 Ariba, Inc. All rights reserved. Patents pending.
 */
package map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 599. Minimum Index Sum of Two Lists
 * Easy
 *
 * Suppose Andy and Doris want to choose a restaurant for dinner, and they both have a list of
 * favorite restaurants represented by strings.
 *
 * You need to help them find out their common interest with the least list index sum. If there
 * is a choice tie between answers, output all of them with no order requirement. You could
 * assume there always exists an answer.
 *
 * Example 1:
 * Input:
 * ["Shogun", "Tapioca Express", "Burger King", "KFC"]
 * ["Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"]
 * Output: ["Shogun"]
 * Explanation: The only restaurant they both like is "Shogun".
 * Example 2:
 * Input:
 * ["Shogun", "Tapioca Express", "Burger King", "KFC"]
 * ["KFC", "Shogun", "Burger King"]
 * Output: ["Shogun"]
 * Explanation: The restaurant they both like and have the least index sum is "Shogun" with index
 * sum 1 (0+1).
 * Note:
 * The length of both lists will be in the range of [1, 1000].
 * The length of strings in both lists will be in the range of [1, 30].
 * The index is starting from 0 to the list length minus 1.
 * No duplicates in both lists.
 */
public class MinimumIndexOfTwoLists {

  /**
   * Idea - for lookup use map. Put the first list to the map <String,index>. Then for second
   * list lookup the current string in the map, if it's there - compute the cost, compare
   * to the running min, if smaller - add to result
   * @param list1
   * @param list2
   * @return
   */
  public String[] findRestaurant(String[] list1, String[] list2) {
    Map<String, Integer> mapList1 = new HashMap();
    for (int i = 0; i < list1.length; i++) {
      mapList1.put(list1[i], i);
    }

    int min = Integer.MAX_VALUE; List<Integer> resIdxList2 = new ArrayList();
    for (int i = 0; i < list2.length; i++) {
      if (mapList1.containsKey(list2[i])) {
        int cur = mapList1.get(list2[i]) + i;
        if (cur < min) {
          min = cur;
          resIdxList2.clear();
          resIdxList2.add(i);
        }
        else if (cur == min) {
          resIdxList2.add(i);
        }
      }
    }

    String[] res = new String[resIdxList2.size()];
    int i = 0;
    for (int idx : resIdxList2)
      res[i++] = list2[idx];
    return res;
  }
}
