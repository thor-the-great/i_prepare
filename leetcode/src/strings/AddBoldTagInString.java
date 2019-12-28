package strings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AddBoldTagInString {

    public String addBoldTag(String s, String[] dict) {
        List<int[]> intervals = new ArrayList();
        //create intervals based on words and it's substrings in a string
        for (String dictWord : dict) {
            int start = s.indexOf(dictWord);
            while (start != -1) {
                intervals.add(new int[] {start, start + dictWord.length()});
                //continue to go over the string from index + 1
                start = s.indexOf(dictWord, start + 1);
            }
        }
        //sort intervals for merge
        Collections.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        //merge intervals so we have unique ranges from original string
        intervals = merge(intervals);
        if (intervals.isEmpty())
            return s;
        //iterate over ranges and map it to the substrings of original string
        StringBuilder sb = new StringBuilder();
        //keep the previous index as end of the range, everything in [prev,interval[0]] is out of the tag
        //everything inside [interval[0],interval[1]] is inside the tag
        int prev = 0;
        for (int[] interval :  intervals) {
            sb.append(s, prev, interval[0]);
            sb.append("<b>");
            sb.append(s, interval[0], interval[1]);
            sb.append("</b>");
            prev = interval[1];
        }
        //in case last range ended before the end of the string - add that string as out of the tag
        if (prev < s.length()) {
            sb.append(s.substring(prev));
        }
        return sb.toString();
    }

    List<int[]> merge(List<int[]> list) {
        if (list.isEmpty() || list.size() == 1)
            return list;

        List<int[]> merged = new ArrayList();
        int start = list.get(0)[0], end = list.get(0)[1];

        for (int[] i : list) {
            if (end >= i[0]) {
                end = Math.max(end, i[1]);
            } else {
                merged.add(new int[] {start, end});
                start = i[0]; end = i[1];
            }
        }

        merged.add(new int[] {start, end});
        return merged;
    }
}
