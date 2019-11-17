package strings;

import java.util.*;

/**
 * Task
 * You are given a string s. Every letter in s appears once.
 *
 * Consider all strings formed by rearranging the letters in s. After ordering these strings in dictionary order,
 * return the middle term. (If the sequence has a even length n, define its middle term to be the (n/2)th term.)
 *
 * Example
 * For s = "abc", the result should be "bac". The permutations in order are: "abc", "acb", "bac", "bca", "cab", "cba"
 * So, The middle term is "bac".
 *
 * Input/Output
 * [input] string s
 *
 * unique letters (2 <= length <= 26)
 *
 * [output] a string
 *
 * middle permutation.
 */
public class MiddlePermutation {

    /**
     * Idea - don't do actual permutations and then sorting - it's too long. Just sort chars and think of sequences
     * of indexes (sorted) and how to get middle there.
     * For case when there are even elements - take mid - 1, then reverse order. For odd - take mid - 1, then
     * do it again and then take reversed.
     * @param strng
     * @return
     */
    public static String findMidPerm(String strng) {
        List<Character> list = new ArrayList();
        for (char ch : strng.toCharArray())
            list.add(ch);

        Collections.sort(list, Comparator.reverseOrder());
        StringBuilder sb = new StringBuilder();

        sb.append(list.get(list.size()/2));
        list.remove(list.size()/2);
        //if we had even elements ( - 1) then add reversed ordered to result
        if (list.size() % 2 == 1) {
            for (char ch : list) {
                sb.append(ch);
            }
            //if odd - take mid one and do the same reversed order but with the rest
        } else {
            sb.append(list.get(list.size()/2));
            list.remove((int)list.size()/2);
            for (char ch : list) {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        MiddlePermutation.findMidPerm("bdca");
    }
}