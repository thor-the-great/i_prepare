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

    public String nextClosestTimeCalculation(String time) {
        String[] times = time.split(":");
        int origTime = Integer.parseInt(times[0]) * 60 + Integer.parseInt(times[1]);
        int dif = 24*60;
        int res = origTime;
        Set<Integer> allowed = new HashSet();

        allowed.add(times[0].charAt(0) - '0');
        allowed.add(times[0].charAt(1) - '0');
        allowed.add(times[1].charAt(0) - '0');
        allowed.add(times[1].charAt(1) - '0');

        for (int h1 : allowed) {
            for (int h2 : allowed) {
                int h = 10*h1 + h2;
                if (h < 24) {
                    for (int m1 : allowed) {
                        for (int m2 : allowed) {
                            int m = 10 *m1 + m2;
                            if (m  < 60) {
                                int cur = h*60 + m;
                                int elapsedCand = Math.floorMod(cur - origTime, 24 * 60);
                                if (0 < elapsedCand && elapsedCand < dif) {
                                    dif = elapsedCand;
                                    res = cur;
                                }
                            }
                        }
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        if (res / 60 < 10)
            sb.append('0');
        sb.append(res / 60).append(':');
        if (res % 60 < 10)
            sb.append('0');
        sb.append(res % 60);
        return sb.toString();
    }

    public static void main(String[] args) {
        NextClosestTime obj = new NextClosestTime();
        System.out.println(obj.nextClosestTime("00:34"));
    }
}

