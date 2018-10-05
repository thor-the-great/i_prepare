package cracking.moderate;

import java.util.*;

public class SumSwap {

    List<Integer> sumSwap(int[] arr1, int[] arr2) {
        //expect arr1 sum to be greater
        int sum1 = getArraySum(arr1);
        int sum2 = getArraySum(arr2);
        if (sum2 > sum1)
            return sumSwap(arr2, arr1);
        int X = sum1 - sum2;
        if (X % 2 != 0)
            return Collections.EMPTY_LIST;
        X = X / 2;
        Set<Integer> lookupSet = new HashSet();
        for (int element : arr1) {
            lookupSet.add(element - X);
        }
        for (int element : arr2) {
            if (lookupSet.contains(element)) {
                ArrayList<Integer> res = new ArrayList();
                res.add(element);
                res.add(element + X);
                return res;
            }
        }
        return Collections.EMPTY_LIST;
    }

    int getArraySum(int[] arr) {
        int sum1 = 0;
        for (int el1 : arr)
            sum1 += el1;
        return sum1;
    }

    public static void main(String[] args) {
        SumSwap obj = new SumSwap();
        List<Integer> res = obj.sumSwap(
                new int[] {4, 1, 2, 1, 1, 2},
                new int[] {3, 6, 3, 3}
                );
        for (int element : res){
            System.out.print(element + ", ");
        }

        res = obj.sumSwap(
                new int[] {4, 1, 6, 3},
                new int[] {10, 5}
        );
        for (int element : res){
            System.out.print(element + ", ");
        }
    }
}
