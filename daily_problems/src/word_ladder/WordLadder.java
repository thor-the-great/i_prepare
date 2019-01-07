package word_ladder;

import java.util.*;

public class WordLadder {

    public static void main(String[] args) {
        List<String> wordList;
        String beginWord;
        String endWord;
        wordList = Arrays.asList(new String[] {
                "hot","dot","dog","lot","log","cog"
        });
        beginWord = "hit";
        endWord = "cog";

        WordLadder obj = new WordLadder();
        System.out.println(obj.ladderLength(beginWord, endWord, wordList));
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Map<String, Integer> m = new HashMap();
        for(String word : wordList) {
            m.put(word, Integer.MAX_VALUE);
        }
        if (!m.containsKey(endWord))
            return 0;
        m.put(beginWord, 1);
        int res = Integer.MAX_VALUE;
        Queue<String> q = new LinkedList();
        q.add(beginWord);
        while (!q.isEmpty()) {
            String w = q.poll();
            int minSoFar = m.get(w) + 1;
            if (minSoFar > res)
                break;
            for (int i = 0; i < w.length(); i++) {
                StringBuilder wSB = new StringBuilder(w);
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    wSB.setCharAt(i, ch);
                    String transWord = wSB.toString();
                    if (m.containsKey(transWord)) {
                        int steps = m.get(transWord);
                        if (steps < minSoFar)
                            continue;
                        else if ( steps > minSoFar) {
                            //if we found shorter path
                            m.put(transWord, minSoFar);
                            q.add(transWord);
                        } else;
                        if (transWord.equals(endWord)) {
                            res = Math.min(res, minSoFar);
                            continue;
                        }
                    }
                }
            }
        }

        if (res == Integer.MAX_VALUE)
            return 0;
        else
            return res;
    }
}
