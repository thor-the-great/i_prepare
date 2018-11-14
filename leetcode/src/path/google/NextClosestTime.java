package path.google;

import java.util.HashSet;
import java.util.Set;

public class NextClosestTime {
    public String nextClosestTime(String time) {
        Set<Integer> allowedDigits = new HashSet();
        int hs = 0;
        int ms = 0;
        for (int i =0; i < time.length(); i++) {
            char ch = time.charAt(i);
            if (ch != ':') {
                int d = ch - '0';
                allowedDigits.add(d);
                if (i < 2) {
                    hs = hs * 10 + d;
                } else {
                    ms = ms * 10 + d;
                }
            }
        }
        if (allowedDigits.size() == 1) return time;
        int t = hs * 60 + ms;
        int maxMin = 60*24;
        while (true) {
            t += 1;
            if (t == maxMin)
                t = 0;
            int m1 = (t % 60) / 10;
            if (!allowedDigits.contains(m1))
                continue;
            int m2 = (t % 60) % 10;
            if (!allowedDigits.contains(m2))
                continue;
            int h1 = (t / 60) / 10;
            if (!allowedDigits.contains(h1))
                continue;
            int h2 = (t / 60) % 10;
            if (!allowedDigits.contains(h2))
                continue;
            return new String("" + h1 + h2 +":" + m1 + m2);
        }
    }

    public static void main(String[] args) {
        NextClosestTime obj = new NextClosestTime();
        System.out.println(obj.nextClosestTime("00:34"));
    }
}

