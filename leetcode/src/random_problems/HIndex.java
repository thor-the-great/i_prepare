package random_problems;

/**
 * 274. H-Index
 * Medium
 *
 * Given an array of citations (each citation is a non-negative integer) of a researcher, write a function to compute
 * the researcher's h-index.
 *
 * According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at
 * least h citations each, and the other N − h papers have no more than h citations each."
 *
 * Example:
 *
 * Input: citations = [3,0,6,1,5]
 * Output: 3
 * Explanation: [3,0,6,1,5] means the researcher has 5 papers in total and each of them had
 *              received 3, 0, 6, 1, 5 citations respectively.
 *              Since the researcher has 3 papers with at least 3 citations each and the remaining
 *              two with no more than 3 citations each, her h-index is 3.
 * Note: If there are several possible values for h, the maximum one is taken as the h-index.
 */
public class HIndex {

    /**
     * Idea: need to sort citations by number. 1 way - do the key compassing sorting, but it's O(nlogn)
     * Alternatively we can do counting sorting with catch - everything that is > N we can count as N, because
     * it doesn't matter actually, h-index can't be higher than N.
     * So we do counting sort, keep number of documents in counts array.
     * Then iterate over that count array starting from n, decrementing h-index and check if index is still > than
     * sum of number of documents we already checked
     * @param citations
     * @return
     */
    public int hIndex(int[] citations) {
        int N = citations.length;
        if (N == 0)
            return 0;

        int[] counts = new int[N + 1];
        for (int cit : citations) {
            int idx = Math.min(cit, N);
            counts[idx]++;
        }

        int h = N;
        int sum = counts[N];
        while (h > sum) {
            sum += counts[--h];
        }


        return h;
    }
}
