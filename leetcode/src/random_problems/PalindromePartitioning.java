package random_problems;

import java.util.ArrayList;
import java.util.List;

/**
 * 131. Palindrome Partitioning
 * Medium
 *
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 *
 * Return all possible palindrome partitioning of s.
 *
 * Example:
 *
 * Input: "aab"
 * Output:
 * [
 *   ["aa","b"],
 *   ["a","a","b"]
 * ]
 */
public class PalindromePartitioning {

    List<List<String>> res;
    String s;
    int N = -1;

    public List<List<String>> partition(String s) {
        res = new ArrayList<>();
        this.s = s;
        this.N = s.length();
        List<String> p = new ArrayList<>();
        helper(0, 0, p);
        return res;
    }

    void helper(int start, int end, List<String> path) {
        //base cases
        //if (end  >= N)
            //return;
        if (end == N - 1) {
            if (isPali(start, end)) {
                List<String> comb = new ArrayList<>(path);
                comb.add(s.substring(start, end + 1));
                res.add(comb);
            }
            return;
        }

        if (isPali(start, end)) {
            path.add(s.substring(start, end + 1));
            helper(end + 1, end + 1, path);
            path.remove(path.size() - 1);
        }
        helper(start, end + 1, path);
    }

    boolean isPali(int l, int r) {
        while (l < r) {
            if (s.charAt(l) != s.charAt(r))
                return false;
            r--;
            l++;
        }
        return true;
    }

    public static void main(String[] args) {
        PalindromePartitioning obj = new PalindromePartitioning();
        List<List<String>> res = obj.partition("aab");
        res.forEach(comb->{
            comb.forEach(w->System.out.print(w + ", "));
            System.out.print("\n");
        });
    }
}
