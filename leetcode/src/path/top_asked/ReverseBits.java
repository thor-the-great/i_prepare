package path.top_asked;

public class ReverseBits {
    public int reverseBits(int n) {
        int l = 16, r = 15;
        long res = 0;
        int c = 0;
        while (r >= 0) {
            c++;
            long lMask = 1<<l;
            long rMask = 1<<r;
            long lVal = n & lMask;
            long rVal = n & rMask;
            res = res | (rVal << (2*c - 1));
            res = res | (lVal >> (2*c - 1));
            r--;
            l++;
        }
        return (int)res;
    }

    public static void main(String[] args) {
        ReverseBits obj = new ReverseBits();
        int num = 0b110100001;
        int ans = obj.reverseBits(num);
        System.out.println(Integer.toBinaryString(num));
        System.out.println(Integer.toBinaryString(ans));

        num = 2147483648;
        ans = obj.reverseBits(num);
    }
}
