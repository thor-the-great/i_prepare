package path.google;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreak {

    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet();
        int max = 0;
        for (String word : wordDict) {
            set.add(word);
            max = Math.max(max, word.length());
        }
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = Math.max(0, i - max); j < i; j++) {
                if (dp[j] && set.contains(s.substring(j, i))) {
                    dp[i] = true; 
                    break;
                }
            }    
        }
        return dp[s.length()];
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

