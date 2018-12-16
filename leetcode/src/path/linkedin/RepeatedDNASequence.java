package path.linkedin;

import java.util.*;

/**
 * 187. Repeated DNA Sequences
 * Medium
 *
 * All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". When
 * studying DNA, it is sometimes useful to identify repeated sequences within the DNA.
 *
 * Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.
 *
 * Example:
 *
 * Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
 *
 * Output: ["AAAAACCCCC", "CCCCCAAAAA"]
 *
 */
public class RepeatedDNASequence {
    public List<String> findRepeatedDnaSequences(String s) {
        //return findRepeatedDnaSequencesStringSets(s);
        return findRepeatedDnaSequencesBits(s);
    }

    public List<String> findRepeatedDnaSequencesBits(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('A', 0);
        map.put('C', 1);
        map.put('G', 2);
        map.put('T', 3);
        int l = 10;
        int N = s.length();
        Map<Integer, Boolean> words = new HashMap<>();
        List<String> res = new LinkedList<>();
        for (int i = 0; i < N - l + 1; i++) {
            int mask = 0;
            for (int j =0; j < l; j++) {
                int code = map.get(s.charAt(i + j));
                mask += code;
                mask = mask << 2;
            }
            if (!words.containsKey(mask))
                words.put(mask, false);
            else {
                if (!words.get(mask)) {
                    words.put(mask, true);
                    res.add(s.substring(i, i + l));
                }
            }
        }
        return res;
    }

    /**
     * Idea - add every new string to visited, and if was already there - copy it to repeated
     * @param s
     * @return
     */
    public List<String> findRepeatedDnaSequencesStringSets(String s) {
        Set<String> repeated = new HashSet();
        Set<String> visited = new HashSet();
        int l = 10;
        int N = s.length();
        List<String> res = new LinkedList();
        if (N < l) return res;
        for (int i = 0; i < N - l + 1; i++) {
            String str = s.substring(i, i + l);
            if (visited.contains(str))
                repeated.add(str);
            else
                visited.add(str);
        }
        res.addAll(repeated);
        return res;
    }

    public static void main(String[] args) {
        RepeatedDNASequence obj = new RepeatedDNASequence();
        obj.findRepeatedDnaSequences("AAAAAAAAAAA").forEach(s->System.out.print(s +", "));
        System.out.println();
        obj.findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT").forEach(s->System.out.print(s + ", "));
        System.out.println();
    }
}
