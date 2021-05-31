package trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1268. Search Suggestions System
 * Medium
 *
 * Given an array of strings products and a string searchWord. We want to design a system that suggests at most three
 * product names from products after each character of searchWord is typed. Suggested products should have common
 * prefix with the searchWord. If there are more than three products with a common prefix return the three
 * lexicographically minimums products.
 *
 * Return list of lists of the suggested products after each character of searchWord is typed.
 *
 *
 *
 * Example 1:
 *
 * Input: products = ["mobile","mouse","moneypot","monitor","mousepad"], searchWord = "mouse"
 * Output: [
 * ["mobile","moneypot","monitor"],
 * ["mobile","moneypot","monitor"],
 * ["mouse","mousepad"],
 * ["mouse","mousepad"],
 * ["mouse","mousepad"]
 * ]
 * Explanation: products sorted lexicographically = ["mobile","moneypot","monitor","mouse","mousepad"]
 * After typing m and mo all products match and we show user ["mobile","moneypot","monitor"]
 * After typing mou, mous and mouse the system suggests ["mouse","mousepad"]
 * Example 2:
 *
 * Input: products = ["havana"], searchWord = "havana"
 * Output: [["havana"],["havana"],["havana"],["havana"],["havana"],["havana"]]
 * Example 3:
 *
 * Input: products = ["bags","baggage","banner","box","cloths"], searchWord = "bags"
 * Output: [["baggage","bags","banner"],["baggage","bags","banner"],["baggage","bags"],["bags"]]
 * Example 4:
 *
 * Input: products = ["havana"], searchWord = "tatiana"
 * Output: [[],[],[],[],[],[],[]]
 *
 *
 * Constraints:
 *
 * 1 <= products.length <= 1000
 * There are no repeated elements in products.
 * 1 <= Σ products[i].length <= 2 * 10^4
 * All characters of products[i] are lower-case English letters.
 * 1 <= searchWord.length <= 1000
 * All characters of searchWord are lower-case English letters.
 */
public class SearchSuggestionsSystem {

    /**
     * Create trie for the dict (products), start adding word to every node in trie. If we sort dict at the beginning
     * then words will be added in lexographical order
     * @param products
     * @param searchWord
     * @return
     */
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        Arrays.sort(products);
        Trie root = buildTrie(products);
        List<List<String>> res = new ArrayList();
        
        Trie cur = root;
        for (char ch : searchWord.toCharArray()) {
            List<String> oneCharResults = new ArrayList();
            if (cur == null) {
                res.add(oneCharResults);
                continue;
            }
            cur = cur.next[ch - 'a'];
            if (cur == null) {
                res.add(oneCharResults);
                continue;
            }
            for (int idx : cur.matches) {
                oneCharResults.add(products[idx]);
            }
            res.add(oneCharResults);
        }
        return res;
    }
    
    Trie buildTrie(String[] products) {
        Trie root = new Trie();
        for (int i = 0; i < products.length; i++) {
            String word = products[i];
            Trie cur = root;
            for (char ch : word.toCharArray()) {
                if (cur.next[ch - 'a'] == null) {
                    cur.next[ch - 'a'] = new Trie();
                }
                cur = cur.next[ch - 'a'];
                //add word to the list for one node
                if (cur.matches.size() < 3) {
                    cur.matches.add(i);
                }
            }
        }
        return root;
    }
    class Trie {
        Trie[] next = new Trie[26];
        List<Integer> matches = new ArrayList();
    } 
}
