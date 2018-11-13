package random_problems;

import java.math.BigInteger;
import java.util.Random;

public class RepeatedStringMatch {

    public int repeatedStringMatch(String A, String B) {
        long prime = BigInteger.probablePrime(31, new Random()).longValue();
        int q = (B.length() - 1) / A.length() + 1;
        int R = 10;
        long RM = 1;
        for(int i = 1; i < B.length(); i++) {
            RM = (R*RM) % prime;
        }
        long bHash = 0;
        for (int j = 0; j < B.length(); j++)
            bHash = (R * bHash + B.charAt(j)) % prime;

        long aHash = 0;
        for (int j = 0; j < B.length(); j++)
            aHash = (R * aHash + A.charAt(j % A.length())) % prime;

        if (bHash == aHash && check(0, A, B))
            return q;

        for (int i = B.length(); i < (q + 1) * A.length(); i++) {
            aHash = (aHash + prime - RM * A.charAt((i - B.length()) % A.length()) % prime) % prime;
            aHash = (aHash*R + A.charAt(i % A.length())) % prime;
            if (aHash == bHash)
                return i < q * A.length() ? q : q + 1;
        }
        return -1;
    }

    boolean check(int index, String A, String B) {
        for (int i = 0; i < B.length(); i++) {
            if (A.charAt((i + index) % A.length()) != B.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        RepeatedStringMatch obj = new RepeatedStringMatch();
        //System.out.println(obj.repeatedStringMatch("abcd", "abcd"));
        System.out.println(obj.repeatedStringMatch("abcd", "cdabcdab"));
    }
}
