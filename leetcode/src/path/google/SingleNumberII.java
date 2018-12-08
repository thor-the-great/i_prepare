package path.google;

public class SingleNumberII {
    public int singleNumber(int[] nums) {
        int MAX_BITS = 32;
        int res = 0;
        for (int i = 0; i < MAX_BITS; i++) {
            int mask = 1<<i;
            int sum = 0;
            for (int num : nums) {
                if ((num&mask) == 0 )
                    sum++;
            }
            if (sum % 3 == 0)
                res |= mask;
        }
        return res;
    }

    public static void main(String[] args) {
        SingleNumberII obj = new SingleNumberII();
        System.out.println(obj.singleNumber(new int[] {2, 2, 3, 2}));

        System.out.println(obj.singleNumber(new int[] {-2,-2,1,1,-3,1,-3,-3,-4,-2}));
    }
}
