    /**
     * 745. Prefix and Suffix Search
Hard

Design a special dictionary which has some words and allows you to search the words in it by a prefix and a suffix.

Implement the WordFilter class:

    WordFilter(string[] words) Initializes the object with the words in the dictionary.
    f(string prefix, string suffix) Returns the index of the word in the dictionary which has the prefix prefix and the suffix suffix. If there is more than one valid index, return the largest of them. If there is no such word in the dictionary, return -1.

 

Example 1:

Input
["WordFilter", "f"]
[[["apple"]], ["a", "e"]]
Output
[null, 0]

Explanation
WordFilter wordFilter = new WordFilter(["apple"]);
wordFilter.f("a", "e"); // return 0, because the word at index 0 has prefix = "a" and suffix = 'e".

 

Constraints:

    1 <= words.length <= 15000
    1 <= words[i].length <= 10
    1 <= prefix.length, suffix.length <= 10
    words[i], prefix and suffix consist of lower-case English letters only.
    At most 15000 calls will be made to the function f.


     * https://leetcode.com/problems/prefix-and-suffix-search/
     */

public class PrefixandSuffixSearch {
    
    /**
     * Idea - trie with tricky word construction: we do all chars in suffix + "#" + prefix. E.g. for bed we do
     * 
     * "#bed", "d#bed", "ed#bed", "bed#bed", then for search we start from suffix and then preffix.
     * 
     * time Complexity: O(NK^2 + QK) where N is the number of words, K is the maximum length of a word, and Q is the number of queries.
     * 
     * Space Complexity: O(NK^2), the size of the trie.
     * 
     */
    class WordFilter {
    
    Trie root = new Trie();
    
    public WordFilter(String[] words) {
        for (int w = 0; w < words.length; w++) {
            String word = words[w];
            for (int i = word.length() - 1; i >= 0; i--) {
                Trie node = root;
                //add suffix part
                for (int j = i; j < word.length(); j++) {
                    if (!node.nextChar.containsKey(word.charAt(j))) {
                        node.nextChar.put(word.charAt(j), new Trie());
                    }
                    node = node.nextChar.get(word.charAt(j));
                }
                //separator
                if (!node.nextChar.containsKey('#')) {
                    node.nextChar.put('#', new Trie());
                }
                node = node.nextChar.get('#');
                //end of the word
                for (int j = 0; j < word.length(); j++) {
                    if (!node.nextChar.containsKey(word.charAt(j))) {
                        node.nextChar.put(word.charAt(j), new Trie());
                    }
                    node = node.nextChar.get(word.charAt(j));
                    node.idx = w;
                }
            }
            //special case for empty prefix
            Trie node = root;
            if (!node.nextChar.containsKey('#')) {
                node.nextChar.put('#', new Trie());
            }
            node = node.nextChar.get('#');
            //end of the word
            for (int j = 0; j < word.length(); j++) {
                if (!node.nextChar.containsKey(word.charAt(j))) {
                    node.nextChar.put(word.charAt(j), new Trie());
                }
                node = node.nextChar.get(word.charAt(j));
                node.idx = w;
            }
        }
    }
    
    public int f(String prefix, String suffix) {
        Trie node = root;
        //use prefix
        for (int i = 0; i < suffix.length(); i++) {
            if (!node.nextChar.containsKey(suffix.charAt(i))) {
                return -1;
            }
            node = node.nextChar.get(suffix.charAt(i));        }
        //ended with pref, check separator
        if (!node.nextChar.containsKey('#')) {
            System.out.println("2");
            return -1;
        }
        node = node.nextChar.get('#');
        //use suffix
        for (int i = 0; i < prefix.length(); i++) {
            if (!node.nextChar.containsKey(prefix.charAt(i))) {
                return -1;
            }
            node = node.nextChar.get(prefix.charAt(i));
        }
        return node.idx;
    }
}

class Trie {
    Map<Character, Trie> nextChar = new HashMap();
    int idx = -1;
}
}
