package random_problems;

public class MapSum {
    MapNode root;
    /** Initialize your data structure here. */
    public MapSum() {
        root = new MapNode();
    }

    public void insert(String key, int val) {
        MapNode n = root;
        int found = -1;

        for (char ch : key.toCharArray()) {
            int idx = ch - 'a';
            if (n.next[idx] == null) {
                n = null;
                break;
            }
            n = n.next[idx];
        }
        if (n != null)
            found = n.w;
        //this means it's a clear new insert
        if (found == -1) {
            n = root;
            for (char ch : key.toCharArray()) {
                int idx = ch - 'a';
                if (n.next[idx] == null) {
                    n.next[idx] = new MapNode();
                    n.next[idx].s = val;
                } else {
                    n.next[idx].s += val;
                }
                n = n.next[idx];
            }
            n.w = val;
        }
        //this is update
        else {
            n = root;
            for (char ch : key.toCharArray()) {
                int idx = ch - 'a';
                n = n.next[idx];
                n.s += (val - found);
            }
            n.w = val;
        }
    }

    public int sum(String prefix) {
        MapNode n = root;
        for (char ch : prefix.toCharArray()) {
            int idx = ch - 'a';
            if (n.next[idx] == null)
                return 0;
            n = n.next[idx];
        }
        return n.s;
    }

    public static void main(String[] args) {
        MapSum obj = new MapSum();
        obj.insert("aa", 3);
        System.out.println(obj.sum("a"));
        obj.insert("aa", 2);
        System.out.println(obj.sum("a"));
        System.out.println(obj.sum("aa"));
        obj.insert("aaa", 3);
        System.out.println(obj.sum("aaa"));
    }
}


class MapNode {
    int s = -1;
    int w = -1;
    MapNode[] next;

    MapNode() {
        next = new MapNode[26];
    }
}