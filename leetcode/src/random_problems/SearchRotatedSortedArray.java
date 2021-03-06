package random_problems;

public class SearchRotatedSortedArray {

  public int search(int[] nums, int target) {
    int N = nums.length;
    if (N == 0)
      return -1;
    if (N == 1)
      return nums[0] == target ? 0 : -1;
    int res = -1;
    //this is index of the second half of array. 0..pivot - 1 - array 1,
    //pivot.. N - 1 - array 2
    int pivot = -1;
    if (nums[0] < nums[N - 1]) {
      pivot = 0;
    } else {
      int l = 0, r = N - 1;

      while (l <= r) {
        int m = l + (r - l) / 2;
        if (nums[m] > nums[m + 1]) {
          pivot = m + 1;
          break;
        }
        if (nums[m] < nums[m - 1]) {
          pivot = m;
          break;
        }

        if (nums[m] > nums[0])
          l = m + 1;
        else
          r = m - 1;
      }
    }

    if (target >= nums[pivot] && target <= nums[N - 1]) {
      return doBinarySearch(nums, pivot, N - 1, target);
    } else if (pivot > 0 && target >= nums[0] && target <= nums[pivot - 1]) {
      return doBinarySearch(nums, 0, pivot - 1, target);
    }

    return res;
  }

  int doBinarySearch(int[] arr, int start, int end, int target) {
    while (start <= end) {
      int m = start + (end - start) / 2;
      if (target == arr[m])
        return m;
      if (arr[m] > target)
        end = m - 1;
      else
        start = m + 1;
    }
    return -1;
  }

  /**
   * Same idea but in one method. Difference is that l and r for last general binary search is formed only
   * once
   *
   * @param nums
   * @param target
   * @return
   */
  public int searchAllInOneMethod(int[] nums, int target) {
    int N = nums.length;

    if (N == 0)
      return -1;

    //the index where smaller part starts
    int pivotIdx;
    int l = 0, r = N - 1;
    if (nums[l] < nums[r]) {
      pivotIdx = 0;
    } else {
      while (l < r) {
        int m = l + (r - l) / 2;
        if (nums[m] < nums[0]) {
          r = m;
        } else {
          l = m + 1;
        }
      }
      pivotIdx = r;
    }
    //defining which part of the array we'll use for the general binary search
    if (pivotIdx == 0) {
      l = 0;
      r = N - 1;
    } else {
      if (target <= nums[N - 1]) {
        l = pivotIdx;
        r = N - 1;
      } else {
        l = 0;
        r = pivotIdx - 1;
      }
    }
    //do final binary search looking for exact element
    while (l <= r) {
      int m = l + (r - l) / 2;
      if (nums[m] == target)
        return m;
      if (nums[m] > target)
        r = m - 1;
      else
        l = m + 1;
    }

    return -1;
  }

  public static void main(String[] args) {
    SearchRotatedSortedArray obj = new SearchRotatedSortedArray();
    int[] arr;
    arr = new int[]{4, 5, 6, 7, 0, 1, 2};
    System.out.println(obj.search(arr, 0));
    System.out.println(obj.search(arr, 3));
  }
}
