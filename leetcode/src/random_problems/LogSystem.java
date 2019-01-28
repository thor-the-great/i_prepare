package random_problems;

import java.util.*;

public class LogSystem {

    List<long[]> list;
    HashMap< String, Integer > h = new HashMap();
    String[] resTemplate = new String[] {"1999", "00", "00", "00", "00", "00"};
    long SECS_IN_DAY = 24*60*60;
    long DAYS_IN_YEAR = 12*31;
    Comparator<long[]> comp = new Comparator<long[]>() {

        public int compare(long[] o1, long[] o2) {
            return Long.compare(o1[0], o2[0]);
        }
    };

    public LogSystem() {
        list = new ArrayList();

        h.clear();
        h.put("Year", 0);
        h.put("Month", 1);
        h.put("Day", 2);
        h.put("Hour", 3);
        h.put("Minute", 4);
        h.put("Second", 5);

    }


    public void put(int id, String timestamp) {
        long ts = convert(convertStringToIntArr(timestamp.split(":")));
        list.add(new long[]{ts, id});
    }

    public List<Integer> retrieve(String s, String e, String gra) {
        long startTs = getStringArrGran(s, gra, false);
        long endTs = getStringArrGran(e, gra, true);

        List<Integer> res = new ArrayList<>();
        int startIdx = Collections.binarySearch(list, new long[] {startTs, 0L},  comp);
        if (startIdx < 0)
            startIdx = - startIdx - 1;

        long endIdx = Collections.binarySearch(list, new long[] {endTs, 0L},  comp);
        if (endIdx < 0)
            endIdx = - endIdx - 1;

        for (; startIdx < list.size() && startIdx <= endIdx; startIdx++) {
            res.add((int)list.get(startIdx)[1]);
        }
        return res;
    }

    long getStringArrGran(String ts, String gran, boolean include) {
        String[] res = Arrays.copyOf(resTemplate, resTemplate.length);
        int lim = h.get(gran);
        String[] parsed = ts.split(":");
        for (int i = 0; i <= lim; i++) {
            res[i] = parsed[i];
        }
        int[] parsedIntArr = convertStringToIntArr(res);
        if (include) {
            parsedIntArr[lim]++;
        }
        return convert(parsedIntArr);
    }

    int[] convertStringToIntArr(String[] tsArr) {
        int[] arr = new int[tsArr.length];
        for (int i =0; i < tsArr.length; i++) {
            String tsEl = tsArr[i];
            arr[i] = Integer.parseInt(tsEl);
        }
        return arr;
    }

    long convert(int[] ts) {
        ts[1] = ts[1] - (ts[1] == 0 ? 0 : 1);
        ts[2] = ts[2] - (ts[2] == 0 ? 0 : 1);
        return (ts[0] - 1999L) * DAYS_IN_YEAR * SECS_IN_DAY + ts[1] * SECS_IN_DAY * 31 + ts[2]*SECS_IN_DAY + ts[3]*60*60 + ts[4] * 60 + ts[5];
    }
}

/**
 * Your LogSystem object will be instantiated and called as such:
 * LogSystem obj = new LogSystem();
 * obj.put(id,timestamp);
 * List<Integer> param_2 = obj.retrieve(s,e,gra);
 */
