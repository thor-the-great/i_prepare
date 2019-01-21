public class MyHashSet {

    public static void main(String[] args) {
        MyHashSet set = new MyHashSet();
        set.contains(88);
        set.remove(130);
        set.contains(3);
        set.add(952);
        set.remove(767);
        set.add(206);
        set.contains(268);
        set.add(658);
        set.add(918);
        set.add(709);
        set.remove(965);
        set.contains(558);
        set.remove(645);
        set.add(667);
        set.contains(206);
        set.remove(404);
        set.contains(918);
        set.remove(139);
        set.remove(686);

        System.out.println(set.contains(667));
    }


    double loadFactor = 0.75;
    int capacity = 32;
    Value[] values;
    int ts;

    /** Initialize your data structure here. */
    public MyHashSet() {
        values = new Value[capacity];
        ts = (int) (capacity * loadFactor);
    }

    public void add(int key) {
        int hash = keyHash(key);
        Value v = values[hash];
        if (v == null) {
            if (ts > 0) {
                values[hash] = new Value(key);
                ts--;
            } else {
                rebuild();
                add(key);
                return;
            }
        } else {
            //collision, need to rebuild
            if (v.val != key) {
                rebuild();
                add(key);
                return;
            }
        }
    }

    void rebuild() {
        capacity *= 2;
        Value[] tmp = new Value[capacity];
        int c = 0;
        for (Value v : values) {
            if (v != null) {
                tmp[keyHash(v.val)] = v;
                c++;
            }
        }
        values = tmp;
        ts = ((int) (capacity * loadFactor)) - c;
    }

    public void remove(int key) {
        int hash = keyHash(key);
        if (values[hash] != null && values[hash].val == key) {
            values[hash] = null;
            ts++;
        }
    }

    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        int hash = keyHash(key);
        return (values[hash] != null && values[hash].val == key);
    }

    private int keyHash(int val) {
        return val % capacity;
    }
}

class Value {

    int val;

    Value(int v) {
        val = v;
    }
}

