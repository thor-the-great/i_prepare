package random_problems;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LetterTilesPossible {

  Set<String> set = new HashSet<>();

  public int numTilePossibilities(String tiles) {
    set.clear();
    helper(tiles.toCharArray(), 0);
    return set.size();
  }

  void helper(char[] strArr, int idx) {

    if (idx >= 1) {
      String s = String.valueOf(Arrays.copyOf(strArr, idx));
      set.add(s);
    }
    int N = strArr.length;
    if (idx == N)
      return;

    for (int i = idx; i < N; i++) {
      swap(strArr, i, idx);
      helper(strArr, idx + 1);
      swap(strArr, i, idx);
    }
  }

  void swap(char[] arr, int i, int j) {
    char ch = arr[i];
    arr[i] = arr[j];
    arr[j] = ch;
  }

  /*public int numTilePossibilities(String tiles) {
    set = new HashSet<>();
    backtrack(tiles.toCharArray(), 0);
    return set.size();
  }

  private void backtrack(char[] chars, int i) {
    if (i >= 1) {
      set.add(String.valueOf(Arrays.copyOf(chars, i)));
    }

    if (i == chars.length) return;

    for (int j = i; j < chars.length; j++) {
      swap(chars, i, j);
      backtrack(chars, i + 1);
      swap(chars, i, j);
    }
  }

  private void swap(char[] chars, int i, int j) {
    char temp = chars[i];
    chars[i] = chars[j];
    chars[j] = temp;
  }*/

  public static void main(String[] args) {

    LetterTilesPossible obj = new LetterTilesPossible();

    assertThat(obj.numTilePossibilities("AAB")).isEqualTo(8);

    assertThat(obj.numTilePossibilities("AAABBC")).isEqualTo(188);
  }
}
