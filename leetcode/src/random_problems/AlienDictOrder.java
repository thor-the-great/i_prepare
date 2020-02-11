package random_problems;

/**
 * 953. Verifying an Alien Dictionary
 * Difficulty: Easy
 * In an alien language, surprisingly they also use english lowercase letters, but possibly in a different order. The
 * order of the alphabet is some permutation of lowercase letters.
 *
 * Given a sequence of words written in the alien language, and the order of the alphabet, return true if and only if
 * the given words are sorted lexicographicaly in this alien language.
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
 * Output: true
 * Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.
 * Example 2:
 *
 * Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
 * Output: false
 * Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1], hence the sequence is unsorted.
 * Example 3:
 *
 * Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
 * Output: false
 * Explanation: The first three characters "app" match, and the second string is shorter (in size.) According to
 * lexicographical rules "apple" > "app", because 'l' > '∅', where '∅' is defined as the blank character which is less
 * than any other character (More info).
 *
 *
 * Note:
 *
 * 1 <= words.length <= 100
 * 1 <= words[i].length <= 20
 * order.length == 26
 * All characters in words[i] and order are english lowercase letters.
 *
 */
public class AlienDictOrder {

    public boolean isAlienSorted(String[] words, String order) {
        int N = words.length;
        if (N < 2)
            return true;
        int[] dict = new int[26];
        for (int i = 0; i < 26; i++) {
            dict[order.charAt(i) - 'a'] = i;
        }

        for (int i = 0; i < N - 1; i++) {
            String w1 = words[i], w2 = words[i + 1];
            boolean good = false;
            for (int j = 0; j < Math.min(w1.length(), w2.length()); j++) {
                char ch1 = w1.charAt(j), ch2 = w2.charAt(j);
                if (dict[ch1 - 'a'] > dict[ch2 - 'a'])
                    return false;
                else if (dict[ch1 - 'a'] < dict[ch2 - 'a']) {
                    good = true;
                    break;
                }
            }
            if (!good && w1.length() > w2.length())
                return false;
        }

        return true;
    }

    public static void main(String[] args) {
        AlienDictOrder obj = new AlienDictOrder();
        System.out.println(obj.isAlienSorted(new String[]{"word","world","row"}, "worldabcefghijkmnpqstuvxyz"));
    }
}
