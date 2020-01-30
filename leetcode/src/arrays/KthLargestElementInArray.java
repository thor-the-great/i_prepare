package arrays;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class KthLargestElementInArray {

  Random rand = new Random();
  int[] arr;

  public int findKthLargest(int[] nums, int k) {
    this.arr = nums;
    return qSelect(0, nums.length - 1, k);
  }

  int qSelect(int l, int r, int k) {
    k = arr.length - k;
    while (l < r ) {
      int partIdx = partition(l, r, l + rand.nextInt(r - l));
      //if we checked exactly our element - we found the solution exit
      if (partIdx == k)
        return arr[partIdx];
      //if the partIdx is lower then k it means we have to work on right elements, so
      //move left pointer
      else if (partIdx < k) {
        l = partIdx + 1;
        //otherwise work on left part so move right pointer
      } else
        r = partIdx - 1;
    }
    return arr[l];
  }

  int partition(int l, int r, int partIdx) {
    //save valiue of the part element
    int partVal = arr[partIdx];
    //move part element to the right index
    swap(r, partIdx);
    int idx = l;
    for (int i = l; i < r; i++) {
      if (arr[i] < partVal) {
        swap(i, idx);
        idx++;
      }
    }
    //part the part element to the idx position (must point to the corrent location at this moment)
    swap(r, idx);
    return idx;
  }

  void swap(int i, int j) {
    int t = arr[i];
    arr[i] = arr[j];
    arr[j] = t;
  }

  public static void main(String[] args) {
    KthLargestElementInArray obj = new KthLargestElementInArray();
    Random rand =  new Random();
    int[] arr = IntStream
        .generate(
            ()-> rand.nextInt(20))
        .limit(8)
        .toArray();
    System.out.println(Arrays.toString(arr));
    System.out.println(obj.findKthLargest(arr, 3));

    arr = IntStream
        .generate(
            ()-> rand.nextInt(12))
        .limit(5)
        .toArray();
    System.out.println(Arrays.toString(arr));
    System.out.println(obj.findKthLargest(arr, 2));

    System.out.println(obj.findKthLargest(new int[]{2, 1}, 2));
  }
}
