package graphs;

import java.util.*;

/**
 * 1258. Synonymous Sentences
 * Medium
 *
 * Given a list of pairs of equivalent words synonyms and a sentence text, Return all possible synonymous sentences
 * sorted lexicographically.
 *
 *
 * Example 1:
 *
 * Input:
 * synonyms = [["happy","joy"],["sad","sorrow"],["joy","cheerful"]],
 * text = "I am happy today but was sad yesterday"
 * Output:
 * ["I am cheerful today but was sad yesterday",
 * ​​​​​​​"I am cheerful today but was sorrow yesterday",
 * "I am happy today but was sad yesterday",
 * "I am happy today but was sorrow yesterday",
 * "I am joy today but was sad yesterday",
 * "I am joy today but was sorrow yesterday"]
 *
 *
 * Constraints:
 *
 * 0 <= synonyms.length <= 10
 * synonyms[i].length == 2
 * synonyms[0] != synonyms[1]
 * All words consist of at most 10 English letters only.
 * text is a single space separated sentence of at most 10 words.
 */
public class SynonymousSentences {

    /**
     * Create graph of synonyms, then iterate over text and replace every word we met from the graph by
     * it synonym
     * @param synonyms
     * @param text
     * @return
     */
    public List<String> generateSentences(List<List<String>> synonyms, String text) {
        Map<String, List<String>> m = new HashMap();
        for (List<String> syn : synonyms) {
            if (!m.containsKey(syn.get(0)))
                m.put(syn.get(0), new ArrayList());
            if (!m.containsKey(syn.get(1)))
                m.put(syn.get(1), new ArrayList());

            m.get(syn.get(0)).add(syn.get(1));
            m.get(syn.get(1)).add(syn.get(0));
        }

        Set<String> setRes = new HashSet();

        Queue<String> q = new LinkedList<>();
        q.add(text);

        while (!q.isEmpty()) {
            String nextRes = q.remove();
            setRes.add(nextRes);

            String[] setWords = nextRes.split(" ");
            for (int i = 0; i < setWords.length; i++) {
                String word = setWords[i];
                if (!m.containsKey(word))
                    continue;
                for (String syn : m.get(word)) {
                    setWords[i] = syn;
                    int count = 0;
                    StringBuilder sb = new StringBuilder();
                    for (String s : setWords) {
                        count++;
                        sb.append(s);
                        if (count < setWords.length)
                            sb.append(' ');
                    }
                    String newText = sb.toString();
                    if (!setRes.contains(newText))
                        q.add(newText);
                }
            }
        }

        List<String> res =  new ArrayList(setRes);
        Collections.sort(res);
        return res;
    }
}
