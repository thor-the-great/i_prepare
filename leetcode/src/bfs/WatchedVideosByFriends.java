package bfs;

import java.util.*;

/**
 * 1311. Get Watched Videos by Your Friends
 * Medium
 *
 * There are n people, each person has a unique id between 0 and n-1. Given the arrays watchedVideos and friends,
 * where watchedVideos[i] and friends[i] contain the list of watched videos and the list of friends respectively for
 * the person with id = i.
 *
 * Level 1 of videos are all watched videos by your friends, level 2 of videos are all watched videos by the friends
 * of your friends and so on. In general, the level k of videos are all watched videos by people with the shortest
 * path equal to k with you. Given your id and the level of videos, return the list of videos ordered by their
 * frequencies (increasing). For videos with the same frequency order them alphabetically from least to greatest.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: watchedVideos = [["A","B"],["C"],["B","C"],["D"]], friends = [[1,2],[0,3],[0,3],[1,2]], id = 0, level = 1
 * Output: ["B","C"]
 * Explanation:
 * You have id = 0 (green color in the figure) and your friends are (yellow color in the figure):
 * Person with id = 1 -> watchedVideos = ["C"]
 * Person with id = 2 -> watchedVideos = ["B","C"]
 * The frequencies of watchedVideos by your friends are:
 * B -> 1
 * C -> 2
 * Example 2:
 *
 *
 *
 * Input: watchedVideos = [["A","B"],["C"],["B","C"],["D"]], friends = [[1,2],[0,3],[0,3],[1,2]], id = 0, level = 2
 * Output: ["D"]
 * Explanation:
 * You have id = 0 (green color in the figure) and the only friend of your friends is the person with id = 3 (yellow
 * color in the figure).
 *
 *
 * Constraints:
 *
 * n == watchedVideos.length == friends.length
 * 2 <= n <= 100
 * 1 <= watchedVideos[i].length <= 100
 * 1 <= watchedVideos[i][j].length <= 8
 * 0 <= friends[i].length < n
 * 0 <= friends[i][j] < n
 * 0 <= id < n
 * 1 <= level < n
 * if friends[i] contains j, then friends[j] contains i
 */
public class WatchedVideosByFriends {

    public List<String> watchedVideosByFriends(List<List<String>> watchedVideos, int[][] friends, int id, int level) {
        List<String> res = new ArrayList();
        List<Integer> q = new ArrayList();
        q.add(id);
        boolean[] seen = new boolean[friends.length];
        seen[id] = true;

        //do BFS, counting levels until we've reached 0
        while(level > 0) {
            --level;
            List<Integer> newQ = new ArrayList();
            //get all friends of current users, if we haven't seen them - add to the queue for next round of BFS
            for (int n : q) {
                int[] ff = friends[n];
                for (int fff : ff) {
                    if (!seen[fff]) {
                        seen[fff] = true;
                        newQ.add(fff);
                    }
                }
            }
            q = newQ;
        }
        //count frequencies for each video id
        Map<String, Integer> m = new HashMap();
        for (int p : q) {
            for (String v : watchedVideos.get(p)) {
                if (!m.containsKey(v)) {
                    m.put(v, 1);
                } else {
                    m.put(v, m.get(v) + 1);
                }
            }
        }
        //custom comparator accounts frequencies and alphabetical order
        Comparator<String> comp = (o1, o2) ->
                m.get(o1) == m.get(o2)
                        ? o1.compareTo(o2)
                        : m.get(o1) - m.get(o2);
        //add ids from map and sort as per requirements
        res.addAll(m.keySet());
        Collections.sort(res, comp);
        return res;
    }
}
