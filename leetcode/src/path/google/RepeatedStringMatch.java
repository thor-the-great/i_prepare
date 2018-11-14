package path.google;

import java.math.BigInteger;
import java.util.Random;

/**
 * Given two strings A and B, find the minimum number of times A has to be repeated such that B is a substring of it.
 * If no such solution, return -1.
 *
 * For example, with A = "abcd" and B = "cdabcdab".
 *
 * Return 3, because by repeating A three times (“abcdabcdabcd”), B is a substring of it; and B is not a substring of
 * A repeated two times ("abcdabcd").
 *
 * Note:
 * The length of A and B will be between 1 and 10000.
 *
 */
public class RepeatedStringMatch {

    /**
     * Idea is to use Rabin-Karp string matching algo. Tricky is how to build long string from As to check B against it.
     *
     * @param A
     * @param B
     * @return
     */
    public int repeatedStringMatch(String A, String B) {
        //big prime for hash function. Must be max long (before the overflow)
        long prime = BigInteger.probablePrime(31, new Random()).longValue();
        //base of calculations
        int R = 10;
        //highest power of R to use in calculation
        long RM = 1;
        //max number we need to repeat A to get B
        int q = ((B.length() - 1) / A.length()) + 1;

        for (int i = 1; i < B.length(); i++) {
            RM = (RM * R) % prime;
        }
        //calculate has of B
        long bHash = 0;
        for (int i = 0; i < B.length(); i++) {
            bHash = (bHash * 10 + B.charAt(i)) % prime;
        }
        //calculate hash of A, take into account that A might be shorter, so do rotation traversal
        long aHash = 0;
        for (int i = 0; i < B.length(); i++) {
            int j = i % A.length();
            aHash = (aHash * 10 + A.charAt(j)) % prime;
        }
        //if it's match - return q right away
        if (aHash == bHash) return q;
        //search for next possible match using R-K algo - remove first char (multiplied by RM) from hash
        //then multiply rest on R and add code of current char;
        for (int i = B.length(); i < (q + 1)*A.length(); i++) {
            aHash = (aHash - RM * A.charAt((i - B.length()) % A.length()))%prime;
            aHash = (aHash * R + A.charAt(i % A.length())) % prime;
            if (aHash == bHash)
                return i < q * A.length() ? q : q + 1;
        }

        return -1;
    }

    public static void main(String[] args) {
        RepeatedStringMatch obj = new RepeatedStringMatch();
        System.out.println(obj.repeatedStringMatch("abcd", "cdabcdab"));
    }
}
