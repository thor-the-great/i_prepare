package strings;

public class ConstructKPalindromeStrings {

  public boolean canConstruct(String s, int k) {
    if (s == null || s.isEmpty() || s.length() < k)
      return false;

    int[] count = new int[26];
    //count freq of every character
    for (char ch : s.toCharArray()) ++count[ch - 'a'];
    //count chars that has odd freq
    int countOfOdd = 0;
    for (int i = 0; i < 26; i++)
      if (count[i] % 2 == 1)
        ++countOfOdd;
    return countOfOdd <= k;
  }
}
