package cracking.recursion_dp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SuperSet {

    List<List<Integer>> superSet(int[] arr) {
        //return getListsExtraMemory(arr);
        return getListsCounter(arr);
    }

    private List<List<Integer>> getListsCounter(int[] arr) {
        long counter = 1 << arr.length;
        List<List<Integer>> res = new ArrayList<>();
        for (long i = 0; i < counter; i++) {
            List<Integer> nextElement = new ArrayList<>();
            for (int j = 0; j < arr.length; j++) {
                if ((i & (1 << j)) > 0 )
                    nextElement.add(arr[j]);
            }
            res.add(nextElement);
        }
        return res;
    }

    private List<List<Integer>> getListsExtraMemory(int[] arr) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            List<List<Integer>> resCopy = new ArrayList<>();
            for (List<Integer> comb1 : res) {
                ArrayList<Integer> newComb = new ArrayList<>(comb1);
                resCopy.add(newComb);
            }
            for(List<Integer> comb1 : resCopy) {
                comb1.add(arr[i]);
            }
            List<Integer> singleList = new ArrayList<>();
            singleList.add(arr[i]);
            resCopy.add(singleList);
            res.addAll(resCopy);
        }
        res.add(Collections.emptyList());
        return res;
    }

    public static void main(String[] args) {
        SuperSet obj = new SuperSet();
        List<List<Integer>> res = obj.superSet(new int[] {1, 2, 3, 4});

        for (List<Integer> oneSet: res) {
            System.out.print("{ ");
            for (Integer el: oneSet) {
                System.out.print(el + ", ");
            }
            System.out.println(" }");
        }
    }
}
