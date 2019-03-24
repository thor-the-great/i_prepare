import java.util.TreeMap;

/**
 * We have a road from 0 to N length and drops that are falling from the top. Each drop we reaches the road
 * smashes and leaves a wet circle of radius R.
 *
 * What would be the minimum amount of droplets after which road will be covered completely
 */
public class MinDropletsToCoverRoad {

    int minDroplets(int N, int[][] droplets) {
        TreeMap<Integer, Integer> m = new TreeMap();

        int res = 0;

        for (int[] droplet : droplets) {
            res++;
            int start = Math.max(0, droplet[0] - droplet[1]);
            int end = Math.min(N, droplet[0] + droplet[1]);

            if (start > N || end < 0) continue;

            int startToMerge = start;
            int endToMerge = end;

            if (startToMerge == endToMerge) {
                continue;
            }

            Integer lessStart = m.floorKey(start);
            //merge needed on start
            if (lessStart != null && m.get(lessStart) >= start) {
                startToMerge = lessStart;
                endToMerge = Math.max(end, m.get(lessStart));
                m.remove(lessStart);
            }

            //no let's find out about the end
            int nextStart = start;
            Integer moreStart = m.ceilingKey(nextStart);
            while (moreStart != null && moreStart < endToMerge) {
                startToMerge = Math.min(startToMerge, moreStart);
                endToMerge = Math.max(endToMerge, m.get(moreStart));
                nextStart = moreStart;
                m.remove(moreStart);
                moreStart = m.ceilingKey(nextStart);
            }

            m.put(startToMerge, endToMerge);

            if (m.size() == 1 && startToMerge == 0 && endToMerge == N)
                return res;
        }

        return -1;
    }


    public static void main(String[] args) {
        MinDropletsToCoverRoad obj = new MinDropletsToCoverRoad();
        int[][] drops = new int[][] {
                {9, 12},
                {2, 5},
                {7, 2},
                {30, 5},
                {5, 5},
                {24, 4},
                {15, 5},
                {20, 10},
                {33, 6},
                {35, 1}
        };

        System.out.println(obj.minDroplets(39, drops));
    }
}