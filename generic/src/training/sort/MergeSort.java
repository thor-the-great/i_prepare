package training.sort;

import java.util.Arrays;
import utils.ArrayUtil;

public class MergeSort {

    public void sort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    void sort(int[] arr, int l, int r) {
        if (l < r) {
            int mid = (l + r)/2;
            sort(arr, l, mid);
            sort(arr, mid + 1, r);
            merge(arr, l, r);
        }
    }

    void merge(int[] arr, int l, int r) {
        int mid = (l + r)/2;
        int[] leftPart = Arrays.copyOfRange(arr, l, mid + 1), rightPart = Arrays.copyOfRange(arr, mid + 1, r + 1);
        int leftP = 0, rightP = 0, idx = l;
        while (leftP < leftPart.length && rightP < rightPart.length) {
            if (leftPart[leftP] <= rightPart[rightP]) {
                arr[idx++] = leftPart[leftP++];
            } else {
                arr[idx++] = rightPart[rightP++];
            }
        }
        while(leftP < leftPart.length) {
            arr[idx++] = leftPart[leftP++];
        }
        while(rightP < rightPart.length) {
            arr[idx++] = rightPart[rightP++];
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
