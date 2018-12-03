package sparse_array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * This problem was asked by Facebook.
 *
 * You have a large array with most of the elements as zero.
 *
 * Use a more space-efficient data structure, SparseArray, that implements the same interface:
 *
 * init(arr, size): initialize with the original large array and size.
 * set(i, val): updates index at i with val.
 * get(i): gets the value at index i.
 *
 */
public class SparseArray {

    Map<Integer, Integer> array = new HashMap<>();

    //initialize with the original large array and size.
    void init(int[] arr, int size) {
        array.clear();
        for (int i = 0; i < size; i++) {
            if (arr[i] != 0) {
                array.put(i, arr[i]);
            }
        }
    }

    //updates index at i with val.
    void set(int i, int val) {
        if (val != 0)
            array.put(i, val);
        else if (array.containsKey(i))
            array.remove(i);
    }

    //gets the value at index i.
    int get(int i) {
        if (array.containsKey(i))
            return array.get(i);
        else
            return 0;
    }

    public static void main(String[] args) {
        SparseArray array = new SparseArray();
        int[] initialArray = new int[]{3, 4, 0, 0, 0, 0, 0, 5, 0,0,0,0, 2, 0,0, 4, 0, 0,0, 5, 11, 0, 2, 0, 4, 5, 0,0,
                0,0,0,0};
        array.init(initialArray, initialArray.length);
        System.out.println(array.get(1));
        System.out.println(array.get(10));
        System.out.println(array.get(15));
        System.out.println(array.get(20));
        array.set(20, 33);
        System.out.println(array.get(20));
        array.set(20, 0);
        System.out.println(array.get(20));
    }
}
