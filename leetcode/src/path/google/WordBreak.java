package path.google;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreak {

    public boolean wordBreak(String s, List<String> wordDict) {
        int N = s.length();
        if (N == 0 || wordDict.size() == 0)
            return false;
        Set<String> dict = new HashSet();
        for (String word : wordDict) {
            dict.add(word);
        }

        boolean[] dp = new boolean[N + 1];
        dp[0] = true;
        for (int i = 1; i <= N; i ++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && dict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[N];
    }

    public static void main(String[] args) {
        WordBreak obj = new WordBreak();
        List<String> dict;
        String s;

        dict = Arrays.asList(new String[]{"aaaa","aaa"});
        s = "aaaaaaa";
        System.out.println(obj.wordBreak(s, dict));

        dict = Arrays.asList(new String[]{"a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"});
        s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab";
        System.out.println(obj.wordBreak(s, dict));
    }
}

