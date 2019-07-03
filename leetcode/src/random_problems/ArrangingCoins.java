package random_problems;

public class ArrangingCoins {

  public int arrangeCoins(int n) {
    if (n <= 0 )
      return 0;

    int l  = 1, r = n;

    while (l < r) {
      int m = l + (r - l) / 2;
      int t = getSumByRow(m);
      if (t == n)
        return m;
      if ( t > n )
        r = m;
      else
        l = m + 1;
    }
    return l - 1;
  }

  int getSumByRow(int row) {
    return ((row + 1)*row)/2;
  }

  public static void main(String[] args) {
    ArrangingCoins obj = new ArrangingCoins();
    System.out.println(obj.arrangeCoins(5));
  }
}
