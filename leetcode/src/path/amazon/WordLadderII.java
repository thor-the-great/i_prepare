package path.amazon;

import java.util.*;

/**
 * 126. Word Ladder II
 * Hard
 *
 * Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s)
 * from beginWord to endWord, such that:
 *
 * Only one letter can be changed at a time
 * Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 * Note:
 *
 * Return an empty list if there is no such transformation sequence.
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 * You may assume no duplicates in the word list.
 * You may assume beginWord and endWord are non-empty and are not the same.
 * Example 1:
 *
 * Input:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 *
 * Output:
 * [
 *   ["hit","hot","dot","dog","cog"],
 *   ["hit","hot","lot","log","cog"]
 * ]
 * Example 2:
 *
 * Input:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 *
 * Output: []
 *
 * Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
 */
public class WordLadderII {

    Map<String, List<String>> map;
    List<List<String>> results;

    /**
     * Idea: instead of counts for path we collect words that lead to this word. At the end for every word we have a
     * list of words that leads to it. Then we do backtracking with these results starting from startWord to end word,
     * gathering the path
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        results = new ArrayList();
        if (wordList.size() == 0)
            return results;
        int min=Integer.MAX_VALUE;
        Queue<String> queue= new ArrayDeque<>();
        queue.add(beginWord);
        map = new HashMap<>();

        Map<String,Integer> ladder = new HashMap<>();
        for (String string: wordList)
            ladder.put(string, Integer.MAX_VALUE);
        ladder.put(beginWord, 0);

        wordList.add(endWord);

        //BFS: Dijisktra search
        char[] builder;
        while (!queue.isEmpty()) {
            String word = queue.poll();
            int step = ladder.get(word) + 1;//'step' indicates how many steps are needed to travel to one word.
            if (step>min) break;

            for (int i = 0; i < word.length(); i++){
                //StringBuilder builder = new StringBuilder(word);
                builder = word.toCharArray();
                for (char ch='a';  ch <= 'z'; ch++){
                    builder[i] = ch;
                    String new_word = new String(builder);
                    if (ladder.containsKey(new_word)) {
                        if (step > ladder.get(new_word))//Check if it is the shortest path to one word.
                            continue;
                        else if (step < ladder.get(new_word)) {
                            queue.add(new_word);
                            ladder.put(new_word, step);
                        }else;// It is a KEY line. If one word already appeared in one ladder,
                        // Do not insert the same word inside the queue twice. Otherwise it gets TLE.
                        List<String> list;
                        if (!map.containsKey(new_word)) {
                            list = new ArrayList<>();
                            map.put(new_word, list);
                        } else{
                            list = map.get(new_word);
                        }
                        list.add(word);

                        if (new_word.equals(endWord))
                            min=step;

                    }//End if dict contains new_word
                }//End:Iteration from 'a' to 'z'
            }//End:Iteration from the first to the last
        }//End While

        //BackTracking
        LinkedList<String> result = new LinkedList();
        backTrace(endWord, beginWord, result);
        return results;
    }

    private void backTrace(String word,String start,List<String> list) {
        if (word.equals(start)) {
            list.add(0, start);
            results.add(new ArrayList<>(list));
            list.remove(0);
            return;
        }
        list.add(0, word);
        if (map.containsKey(word))
            for (String s : map.get(word))
                backTrace(s, start, list);
        list.remove(0);
    }

        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Map<String, Integer> ladder = new HashMap();
        wordList.forEach(s->ladder.put(s, Integer.MAX_VALUE));
        if (!ladder.containsKey(endWord)) {
            return 0;
        }
        ladder.put(beginWord, 1);
        //build word ladder
        Queue<String> queue= new LinkedList();
        queue.add(beginWord);
        int min = Integer.MAX_VALUE;
        while(!queue.isEmpty()) {
            String word = queue.poll();
            int steps = ladder.get(word) + 1;
            if (steps > min) break;
            for (int i = 0; i < word.length(); i++) {
                StringBuilder sb = new StringBuilder(word);
                for (char ch = 'a'; ch<='z'; ch++) {
                    sb.setCharAt(i, ch);
                    String newWord = sb.toString();
                    if (ladder.containsKey(newWord)) {
                        int stepsToWord = ladder.get(newWord);
                        if (stepsToWord < steps) continue;
                        else if (steps < stepsToWord) {
                            queue.add(newWord);
                            ladder.put(newWord, steps);
                        } else;
                        if (newWord.equals(endWord))
                            min = steps;
                    }
                }
            }
        }
        int minLength = ladder.get(endWord);
        if (minLength == Integer.MAX_VALUE) minLength = 0;
        return minLength;
    }

    public static void main(String[] args) {
        WordLadderII obj = new WordLadderII();
        List<String> dict = new ArrayList<>();
        dict.add("hot");dict.add("dot");dict.add("dog");dict.add("lot");dict.add("log");dict.add("cog");
        List<List<String>> res = obj.findLadders("hit", "cog", dict);
        if (res.size() == 0)
            System.out.println("not possible");
        else {
            for (List<String> oneLadder : res) {
                oneLadder.forEach(s->System.out.print(s + " "));
                System.out.print("\n");
            }
        }

        //System.out.println(obj.ladderLength("hit", "cog", dict));

        dict = new ArrayList<>();
        dict.add("hot");dict.add("dog");

        System.out.println(obj.ladderLength("hot", "dog", dict));
    }
}
