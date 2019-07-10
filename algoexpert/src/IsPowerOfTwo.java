/**
 * Check if positive int is a power of 2
 */
public class IsPowerOfTwo {

  /**
   * Idea - if the num is a power of 2 it's in form - 1000...0, so if we subtract 1 it became
   * 011111.111. If we do bitwise & for both numbers it will be 0.
   * @param num
   * @return
   */
  public static boolean isPowOfTwo(int num) {
    if (num <= 0 )
      return false;

    return (num & (num - 1)) == 0;
  }
}
