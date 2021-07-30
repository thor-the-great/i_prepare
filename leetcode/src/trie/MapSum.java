package trie;

/**
 * 677. Map Sum Pairs
Medium

Implement the MapSum class:

MapSum() Initializes the MapSum object.
void insert(String key, int val) Inserts the key-val pair into the map. If the key already existed, the original key-value pair will be overridden to the new one.
int sum(string prefix) Returns the sum of all the pairs' value whose key starts with the prefix.
 

Example 1:

Input
["MapSum", "insert", "sum", "insert", "sum"]
[[], ["apple", 3], ["ap"], ["app", 2], ["ap"]]
Output
[null, null, 3, null, 5]

Explanation
MapSum mapSum = new MapSum();
mapSum.insert("apple", 3);  
mapSum.sum("ap");           // return 3 (apple = 3)
mapSum.insert("app", 2);    
mapSum.sum("ap");           // return 5 (apple + app = 3 + 2 = 5)
 

Constraints:

1 <= key.length, prefix.length <= 50
key and prefix consist of only lowercase English letters.
1 <= val <= 1000
At most 50 calls will be made to insert and sum.

 * https://leetcode.com/problems/map-sum-pairs/
 * 
 * Idea for solution - use trie, on insert add the word score to each node in chain. 
 * Before that check if word exists in trie, if so decrement the scores for each node.
 * 
 */
public class MapSum {
    
    Trie root = new Trie();
    
    /** Initialize your data structure here. */
    public MapSum() {
        
    }
    
    public void insert(String key, int val) {
        Trie t = root;
        int copyVal = val;
        //check if we have this word in the trie already
        for (char ch : key.toCharArray()) {
            int idx = ch - 'a';
            t = t.next[idx];
            if (t == null) {
                break;
            }
        }
		//if word was in trie previously - update the score value to take into account score of existing word
        if (t != null && t.word) {
            val -= t.wordscore;
        }
        
        //now inserting word into trie
        t = root;
        for (char ch : key.toCharArray()) {
            int idx = ch - 'a';
            if (t.next[idx] == null) {
                t.next[idx] = new Trie();
            }
            t = t.next[idx];
            t.score += val;
        }
        t.word = true;
        //the word scope must be unadjusted, so use copy that we've made at the begining
        t.wordscore = copyVal; 
    }
    
    public int sum(String prefix) {
        Trie t = root;
        int res = 0;
        for (char ch : prefix.toCharArray()) {
            int idx = ch - 'a';
            if (t.next[idx] == null)
                return 0;
            t = t.next[idx];
        }
        return t.score;
    }
    
}

class Trie {
    Trie[] next = new Trie[26];
    int score = 0;
    boolean word = false;
    int wordscore = 0;
}