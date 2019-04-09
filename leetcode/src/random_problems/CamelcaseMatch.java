package random_problems;

import java.util.ArrayList;
import java.util.List;

public class CamelcaseMatch {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> res = new ArrayList();

        for(int q = 0; q < queries.length; q++) {
            //for every query string
            String query = queries[q];
            int qIdx = 0;
            int qLen = query.length();
            boolean matchPattern = true;
            for(char patChar : pattern.toCharArray()) {
                //rewind to the first upper char in query if needed
                boolean isUpPat = Character.isUpperCase(patChar);
                if (isUpPat)
                    while(qIdx < qLen && !Character.isUpperCase(query.charAt(qIdx))) qIdx++;
                //iterate while we haven't met matching character (including case match)
                while(qIdx < qLen && query.charAt(qIdx) != patChar && matchPattern) {
                    //if case changed in the query - this is our break condition
                    if (Character.isUpperCase(query.charAt(qIdx++)) != isUpPat)
                        matchPattern = false;
                }
                //if we reach the end of query string - this is not a match
                if (!matchPattern || qIdx == qLen) {
                    matchPattern = false;
                    break;
                }
                qIdx++;
            }
            //check the tail of the query string - if there are still upper case letters - it's no a match
            while(qIdx < qLen && matchPattern) {
                if (Character.isUpperCase(query.charAt(qIdx++)))
                    matchPattern = false;
            }
            res.add(matchPattern);
        }

        return res;
    }

    public static void main(String[] args) {
        CamelcaseMatch obj = new CamelcaseMatch();
        List<Boolean> res = obj.camelMatch(
                new String[] {
                        "FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"
                },
                "FB"
        );
        res.forEach(b->System.out.print(b + " "));
    }
}
