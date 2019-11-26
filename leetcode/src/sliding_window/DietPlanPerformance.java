package sliding_window;

public class DietPlanPerformance {

    /**
     * do the window of length k, count window point, then move to the right - add next element and remove
     * the left element of the window
     * @param calories
     * @param k
     * @param lower
     * @param upper
     * @return
     */
    public int dietPlanPerformance(int[] calories, int k, int lower, int upper) {
        int N = calories.length;

        int cur = 0;
        //form window of k - 1 elements
        for (int i = 0; i < k - 1; i++)
            cur+= calories[i];
        //res is final result, l is our left boundary of the window
        int res = 0, l = 0;
        for (int i = k - 1; i < N; i++) {
            //add current right element to the window
            cur += calories[i];
            //check points for current window
            if (cur > upper)
                ++res;
            else if (cur < lower)
                --res;
            //move left window pointer - decrement cur by [l] element and
            // move l to the right on 1 index
            cur-=calories[l++];
        }
        return res;
    }
}
