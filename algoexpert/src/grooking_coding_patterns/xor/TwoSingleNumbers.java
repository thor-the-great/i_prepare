package grooking_coding_patterns.xor;

public class TwoSingleNumbers {

  /**
   * If we do xor of all number in the end we'll have xor of two single numbers
   * Task is - now to extract those individual numbers having their XOR.
   * Two numbers are different in at least one bit. Find any bit that is different (I choose right most), then
   * iterate over the array again and do xor, but this time check first for that right bit.
   * Our numbers can be separated by this indicator, and the rest the follows one or another will be again cancelled
   * out by XOR
   * @param nums
   * @return
   */
  public static int[] findSingleNumbers(int[] nums) {
    int xor = 0;
    for (int n : nums) {
      xor^=n;
    }
    int rightBit = 1;
    while((rightBit&xor) == 0) {
      rightBit<<=1;
    }

    int[] res = new int[2];
    for (int n : nums) {
      if ((n&rightBit) == 0) {
        res[0]^=n;
      } else {
        res[1]^=n;
      }
    }

    return res;
  }

  public static void main(String[] args) {
    int[] arr = new int[] { 1, 4, 2, 1, 3, 5, 6, 2, 3, 5 };
    int[] result = TwoSingleNumbers.findSingleNumbers(arr);
    System.out.println("Single numbers are: " + result[0] + ", " + result[1]);

    arr = new int[] { 2, 1, 3, 2 };
    result = TwoSingleNumbers.findSingleNumbers(arr);
    System.out.println("Single numbers are: " + result[0] + ", " + result[1]);
  }
}

