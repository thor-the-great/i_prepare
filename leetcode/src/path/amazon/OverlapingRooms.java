package path.amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

public class OverlapingRooms {

    public int minMeetingRooms(Interval[] intervals) {
        int N = intervals.length;
        int[] starts = new int[N];
        int[] ends = new int[N];
        IntStream.range(0, N).forEach(i-> {
            starts[i] = intervals[i].start;
            ends[i] = intervals[i].end;
        });

        Arrays.sort(starts);
        Arrays.sort(ends);

        int i = 0, j =0;
        int max = 0, maxSoFar = 0;
        while (i < N && j < N) {
            if (starts[i] < ends[j]) {
                maxSoFar++;
                max = Math.max(max, maxSoFar);
                i++;
            } else {
                maxSoFar--;
                j++;
            }
        }
        return max;
    }

    public static void main(String[] args) {

    }

    class Interval {
      int start;
      int end;
      Interval() { start = 0; end = 0; }
      Interval(int s, int e) { start = s; end = e; }
  }
}
