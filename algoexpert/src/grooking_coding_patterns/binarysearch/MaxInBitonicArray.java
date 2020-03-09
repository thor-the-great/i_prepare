package grooking_coding_patterns.binarysearch;

public class MaxInBitonicArray {

  public static int findMax(int[] arr) {
    return findMaxTemplate3(arr);
  }

  public static int findMaxTemplate3(int[] arr) {
    if (arr == null || arr.length == 0)
      return -1;
    if (arr.length == 1)
      return arr[0];

    int l = 0, r = arr.length - 1;
    while(l + 1 < r) {
      int m = l + (r - l)/2;

      if (arr[m] > arr[m + 1] && arr[m] > arr[m - 1])
        return arr[m];

      if (arr[m] > arr[m + 1])
        r = m;
      else
        l = m;
    }

    if (arr[l] > arr[l + 1])
      return arr[l];
    if (arr[r] > arr[r - 1])
      return arr[r];
    return -1;
  }

  public static int findMaxTemplate2(int[] arr) {
    if (arr == null || arr.length == 0)
      return -1;
    if (arr.length == 1)
      return arr[0];

    int l = 0, r = arr.length - 1;
    while(l < r) {
      int m = l + (r - l)/2;

      if (arr[m] > arr[m + 1])
        r = m;
      else
        l = m + 1;
    }

    return arr[l];
  }

  public static void main(String[] args) {
    System.out.println(MaxInBitonicArray.findMax(new int[] { 1, 3, 8, 12, 4, 2 }));
    System.out.println(MaxInBitonicArray.findMax(new int[] { 3, 8, 3, 1 }));
    System.out.println(MaxInBitonicArray.findMax(new int[] { 1, 3, 8, 12 }));
    System.out.println(MaxInBitonicArray.findMax(new int[] { 10, 9, 8 }));
  }
}
