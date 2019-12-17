package design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 1146. Snapshot Array
 * Medium
 *
 * Implement a SnapshotArray that supports the following interface:
 *
 * SnapshotArray(int length) initializes an array-like data structure with the given length.  Initially, each
 * element equals 0.
 * void set(index, val) sets the element at the given index to be equal to val.
 * int snap() takes a snapshot of the array and returns the snap_id: the total number of times we called snap() minus 1.
 * int get(index, snap_id) returns the value at the given index, at the time we took the snapshot with the given snap_id
 *
 *
 * Example 1:
 *
 * Input: ["SnapshotArray","set","snap","set","get"]
 * [[3],[0,5],[],[0,6],[0,0]]
 * Output: [null,null,0,null,5]
 * Explanation:
 * SnapshotArray snapshotArr = new SnapshotArray(3); // set the length to be 3
 * snapshotArr.set(0,5);  // Set array[0] = 5
 * snapshotArr.snap();  // Take a snapshot, return snap_id = 0
 * snapshotArr.set(0,6);
 * snapshotArr.get(0,0);  // Get the value of array[0] with snap_id = 0, return 5
 *
 *
 * Constraints:
 *
 * 1 <= length <= 50000
 * At most 50000 calls will be made to set, snap, and get.
 * 0 <= index < length
 * 0 <= snap_id < (the total number of times we call snap())
 * 0 <= val <= 10^9
 */
public class SnapshotArray {

    /**
     * Keep the history of each snapshot in a map <index,value>. Keep list of such maps for every snapshot id.
     * On get searching for the index changes starting from snapshot id going back to 0. On first change met for the index
     * return the value, if there were no changes return 0.
     */
    List<Map<Integer, Integer>> list;

    public SnapshotArray(int length) {
        list = new ArrayList();
        list.add(new HashMap());
    }

    public void set(int index, int val) {
        int snapId = list.size() - 1;
        list.get(snapId).put(index, val);
    }

    public int snap() {
        list.add(new HashMap());
        return list.size() - 2;
    }

    public int get(int index, int snap_id) {
        for (int snap = snap_id; snap >= 0; snap--) {
            if (list.get(snap).containsKey(index))
                return list.get(snap).get(index);
        }
        return 0;
    }
}
