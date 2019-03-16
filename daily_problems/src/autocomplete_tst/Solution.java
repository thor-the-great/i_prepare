package autocomplete_tst;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
        Solution obj = new Solution();
        obj.doTest();
    }

    void doTest() {
        TST tst = new TST();
        tst.add("cat");
        tst.add("table");
        tst.add("key");
        tst.add("computer");
        tst.add("tablet");
        tst.add("pill");
        tst.add("tabular");


        String word = "lean";
        System.out.println("Checking word " + word + " " + tst.contains(word));
        word = "key";
        System.out.println("Checking word " + word + " " + tst.contains(word));
        word = "fire";
        System.out.println("Checking word " + word + " " + tst.contains(word));
        word = "google";
        System.out.println("Checking word " + word + " " + tst.contains(word));
        word = "table";
        System.out.println("Checking word " + word + " " + tst.contains(word));

        String pref = "tab";
        Collection<String> prefCol = tst.getStringsByPreffix(pref);
        System.out.println("For pref " + pref + " found # of words " + prefCol.size());

        pref = "table";
        prefCol = tst.getStringsByPreffix(pref);
        System.out.println("For pref " + pref + " found # of words " + prefCol.size());

        pref = "c";
        prefCol = tst.getStringsByPreffix(pref);
        System.out.println("For pref " + pref + " found # of words " + prefCol.size());

        pref = "p";
        prefCol = tst.getStringsByPreffix(pref);
        System.out.println("For pref " + pref + " found # of words " + prefCol.size());

        pref = "a";
        prefCol = tst.getStringsByPreffix(pref);
        System.out.println("For pref " + pref + " found # of words " + prefCol.size());
    }
}

class TST {
    Node[] root = new Node[26];

    void add(String value) {
        int startCharInt = value.charAt(0) - 97;
        Node node = root[startCharInt];
        if (node == null) {
            node = new Node();
            node.ch = value.charAt(0);
            root[startCharInt] = node;
        }
        addInternal(value, node, 1);
    }

    Node addInternal(String value, Node node, int pointer) {
        char character = value.charAt(pointer);
        if (node == null) {
            node = new Node();
            node.ch = character;
        }
        if (character < node.ch) {
            node.left = addInternal(value, node.left, pointer);
        } else if (character > node.ch) {
            node.right = addInternal(value, node.right, pointer);
        } else if (pointer < value.length() - 1) {
            node.mid = addInternal(value, node.mid, pointer+1);
        } else {
            node.value = value;
        }
        return node;
    }

    boolean contains(String val) {
        if (val == null || val.equals(""))
            return false;
        int startCharInt = val.charAt(0) - 97;
        return get(val, 1, root[startCharInt]) != null;
    }

    Node get(String s, int pointer, Node node) {
        char ch = s.charAt(pointer);
        if (node == null)
            return null;
        if (ch < node.ch) {
            return get(s, pointer, node.left);
        } else if (ch > node.ch) {
            return get(s, pointer, node.right);
        } else if (pointer < s.length() - 1) {
            return get(s, pointer + 1, node.mid);
        } else {
            return node;
        }
    }

    Collection<String> getStringsByPreffix(String preffix) {
        int startCharInt = preffix.charAt(0) - 97;
        Node prefNode = root[startCharInt];
        //NaryTreeNode prefNode = get(preffix, 1, root[startCharInt]);
        if (prefNode == null)
            return new ArrayList<>();
        List<String> result = new ArrayList<>();
        collect(prefNode, preffix, result);
        return result;
    }

    void collect(Node node, String pref, Collection<String> collection) {
        if (node == null)
            return;
        if (node.value != null)
            collection.add(node.value);
        collectInternal(node.left, pref, collection);
        collectInternal(node.mid, pref, collection);
        collectInternal(node.right, pref, collection);
    }

    private void collectInternal(Node node, String pref, Collection<String> collection) {
        if (node != null) {
            String newS = new StringBuilder().append(pref).append(node.ch).toString();
            collect(node, newS, collection);
        }
    }
}

class Node {
    Node left;
    Node right;
    Node mid;
    String value;
    char ch;
}