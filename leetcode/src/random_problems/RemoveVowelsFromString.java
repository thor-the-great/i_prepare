package random_problems;

import java.util.Set;

public class RemoveVowelsFromString {

  static Set<Character> vowels = Set.of(new Character[] {'a', 'e', 'i', 'o', 'u'});

  public String removeVowels(String S) {
    if (S == null || S.length() == 0)
      return S;

    StringBuilder sb = new StringBuilder();

    for (char ch : S.toCharArray()) {
      if (!vowels.contains(ch))
        sb.append(ch);
    }

    return sb.toString();
  }

  public static void main(String[] args) {
    RemoveVowelsFromString obj = new RemoveVowelsFromString();
    System.out.println(obj.removeVowels("leetcode"));
  }
}
