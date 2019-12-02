package segment_tree;

public interface MutableArray {

    void update(int idx, int val);

    int range(int left, int right);
}
