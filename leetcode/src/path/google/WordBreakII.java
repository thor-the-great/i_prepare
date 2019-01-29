package path.google;

import java.util.*;

public class WordBreakII {
    public List<String> wordBreak(String s, List<String> wordDict) {
        int N = s.length();
        if (N == 0 || wordDict.size() == 0)
            return new ArrayList();
        Set<String> set = new HashSet();
        for (String word : wordDict)
            set.add(word);

        if (!wordBreak(s, set)) {
            return new ArrayList();
        }
        List<String>[] dp = new ArrayList[N + 1];
        dp[0] = new ArrayList();
        dp[0].add("");
        for (int i = 1; i <= N; i ++) {
            List<String> current = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                String w = s.substring(j, i);
                if (dp[j] != null && set.contains(w)) {
                    StringBuilder sb = new StringBuilder();
                    for (String sent : dp[j]) {
                        sb.setLength(0);
                        sb.append(sent);
                        if (sent.length() > 0)
                            sb.append(" ");
                        sb.append(w);
                        current.add(sb.toString());
                    }
                }
            }
            dp[i] = current;
        }
        return dp[N];
    }

    public boolean wordBreak(String s, Set<String> dict) {
        int N = s.length();
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
        WordBreakII obj = new WordBreakII();
        List<String> words = obj.wordBreak("catsanddog", Arrays.asList(new String[] {"cat","cats","and","sand","dog"}));
        for (String sent : words ) {
            System.out.print(sent +" ");
        }
    }
}
