import utils.ArrayUtil;

import java.util.Arrays;

public class MergeSort {

    public void sort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    void sort(int[] arr, int l, int r) {
        if (l < r) {
            //divide arrays in halves unless l >= r
            int m = (l + r) / 2;
            sort(arr, l, m);
            sort(arr, m + 1, r);
            //merge processed halves, each part is half-sorted
            merge(arr, l, r, m);
        }
    }

    void merge(int[] arr, int l, int r, int m) {
        int[] lArr = Arrays.copyOfRange(arr, l, m + 1);
        int[] rArr = Arrays.copyOfRange(arr, m + 1, r + 1);
        int lP = 0;
        int rP = 0;
        int p = l;
        while (lP < lArr.length && rP < rArr.length) {
            if (lArr[lP] < rArr[rP]) {
                arr[p] = lArr[lP];
                lP++;
            }
            else {
                arr[p] = rArr[rP];
                rP++;
            }
            p++;
        }
        while (lP < lArr.length) {
            arr[p] = lArr[lP];
            lP++;
            p++;
        }
        while (rP < rArr.length) {
            arr[p] = rArr[rP];
            rP++;
            p++;
        }
    }

    public static void main(String[] args) {
        MergeSort ms = new MergeSort();
        int[] arr = new int[] {5, 2, 7, 3, 1, 6, 0, 4, 10, 9};
        ms.sort(arr);
        Arrays.stream(arr).forEach(i->System.out.print(i +" "));

        arr = ArrayUtil.getRandomIntArray(10, 20);
        ms.sort(arr);
        System.out.print("\n");
        Arrays.stream(arr).forEach(i->System.out.print(i +" "));
    }
}
