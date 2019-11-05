package arrays;

public class CountNumberOFNiceSubarray {

    public int numberOfSubarrays(int[] nums, int k) {
        int s = 0;
        int N = nums.length;
        for (int i = 0; i < nums.length; i++) {
            s += (nums[i] % 2 == 1 ? 1 : 0);
            nums[i] = s;
        }

        int res = 0;
        if ( s < k)
            return 0;

        int l = -1, r = 0;
        while (r < N) {
            if (nums[r] - (l == -1 ? 0 : nums[l]) == k) {
                res++;
                while (nums[r + 1] - nums[l] == k) {

                }
                l = (l == -1) ? 0 : l;
                while (nums[r] - nums[l] == k) {
                    res++;
                    l++;
                }
            }
        }

        while (nums[N - 1] - nums[++l] == k) {
            res++;
        }

        return res;
    }

    public static void main(String[] args) {
        CountNumberOFNiceSubarray obj = new CountNumberOFNiceSubarray();
        System.out.println(obj.numberOfSubarrays(new int [] {2,2,2,1,2,2,1,2,2,2}, 2));
    }
}
