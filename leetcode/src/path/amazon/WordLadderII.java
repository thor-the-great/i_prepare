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
        
        Map<String, Integer> wordPaths = new HashMap();
        Map<String, List<String>> words = new HashMap();
        List<List<String>> result = new ArrayList();
        for (String word : wordList) {
            wordPaths.put(word, Integer.MAX_VALUE);
        }
        wordPaths.put(beginWord, 0);
        
        Queue<String> q = new LinkedList();
        q.add(beginWord);
        
        wordList.add(endWord);
        
        int  min = Integer.MAX_VALUE;
        while(!q.isEmpty()) {
            String word = q.poll();
            int curSteps = wordPaths.get(word);
            if (curSteps + 1 > min) {
                break;
            }
            for (int i = 0; i < word.length(); i++) {
                StringBuilder sb = new StringBuilder(word);
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    sb.setCharAt(i, ch);
                    String oneStepWord = sb.toString();
                    if (!wordPaths.containsKey(oneStepWord)) {
                        continue;
                    }
                    
                    if (curSteps + 1 > wordPaths.get(oneStepWord)) {
                        continue;
                    } else if (curSteps + 1 < wordPaths.get(oneStepWord)) {
                        wordPaths.put(oneStepWord, curSteps + 1);
                        q.add(oneStepWord);
                    }
                    
                    if (!words.containsKey(oneStepWord)) {
                        words.put(oneStepWord, new ArrayList());
                    } 
                    words.get(oneStepWord).add(word);
                    
                    if (oneStepWord.equals(endWord)) {
                        min = curSteps + 1;
                    }
                }
            }
        }
        if (min == Integer.MAX_VALUE)
            return result;
        backtrackToStartWord(endWord, beginWord, new ArrayList(), words, result);
        return result;
    }
    
    void backtrackToStartWord(String cur, String start, List res, Map<String, List<String>> words, List<List<String>> result) {
        if (cur.equals(start)) {
            res.add(0, start);
            result.add(new ArrayList(res));
            res.remove(0);
            return;
        }
        
        res.add(0, cur);
        if (words.containsKey(cur)) {
            for (String next : words.get(cur)) {
                backtrackToStartWord(next, start, res, words, result);
            }
        }
        res.remove(0);
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
