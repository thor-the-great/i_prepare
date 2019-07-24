/**
 * Number of bits to swap to make 2 integers equal
 */
public class BitSwapRequired {

  //go bit by bit, masking ands comparing results, then shift 1 bit to the right
  public int bitSwapRequired(int a, int b) {
    int res = 0;
    for (int i = 0; i < 31; i++) {
      if ((a & 1) != (b & 1))
        res++;
      a>>=1;
      b>>=1;
    }
    return res;
  }
}
