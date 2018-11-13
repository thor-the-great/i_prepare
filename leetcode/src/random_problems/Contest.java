package random_problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Contest {
    public String[] reorderLogFiles(String[] logs) {
        Comparator<String> logComparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String[] split1 = o1.split(" ");
                String[] split2 = o2.split(" ");
                boolean isDigit1 = isDigit(split1[1]);
                boolean isDigit2 = isDigit(split2[1]);
                if (!isDigit1 && !isDigit2) {
                    String substr1 = o1.substring(split1[0].length());
                    String substr2 = o2.substring(split2[0].length());
                    int shortestLength = Math.min(substr1.length(), substr2.length());
                    for (int i = 0; i < shortestLength;i++) {
                        int comp = Character.compare(substr1.charAt(i), substr2.charAt(i));
                        if (comp != 0)
                            return comp;
                    }
                } else if (isDigit1 != isDigit2) {
                    return isDigit1 ? 1 : -1;
                }
                return 0;
            }
        };
        Arrays.sort(logs, logComparator);
        return logs;
    }

    boolean isDigit(String s) {
        return (Character.isDigit(s.charAt(0)));
    }
}
