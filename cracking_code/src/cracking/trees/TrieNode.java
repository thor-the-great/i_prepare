package cracking.trees;

import cracking.linkedlist.PartitionAroundElement;

import java.util.Collection;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by thor on 12/11/16.
 */
public class TrieNode {
    char letter;
    TreeMap<Character, TrieNode> child;

    TrieNode(char letter) {
        this.letter = letter;
        this.child = new TreeMap<>();
    }

    void addChildForLetter(char aLetter) {
        if (!this.child.containsKey(aLetter)) {
            this.child.put(aLetter, new TrieNode(aLetter));
        }
    }

    TrieNode getChildForLetter(char aLetter) {
        return child.get(aLetter);
    }

    int totalChildren() {
        return child.size();
    }

    TrieNode childAtIndex(int index){
        Collection<TrieNode> values = child.values();
        if (values.size() - 1 < index )
            return null;
        int count = 0;
        TrieNode returnNode = null;
        for (TrieNode node: values ) {
            if (count == index) {
                returnNode = node;
                break;
            }
            count++;
        }
        return returnNode;
    }

    void addEndOfString() { child.put(Character.MIN_VALUE, new TrieNode(Character.MIN_VALUE));}

    boolean isEndOfString() { return child.get(Character.MIN_VALUE) != null;}
}
